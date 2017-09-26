package com.coffeemarket.Controllers;

import com.coffeemarket.entity.CoffeeOrder;
import com.coffeemarket.entity.CoffeeOrderItem;
import com.coffeemarket.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * Created by Dzianis on 04.09.2017.
 */

@Controller
public class CoffeeOrderController {

    @Autowired
    private CoffeeTypeRepository coffeeTypeRepository;

    @Autowired
    private BlockQuoteRepository blockQuoteRepository;

    @Autowired
    private CoffeeOrderItemRepository coffeeOrderItemRepository;

    @Autowired
    private CoffeeOrderRepository coffeeOrderRepository;

    @Autowired
    private ConfigurationRepository configurationRepository;

    @Autowired
    private HomeController homeController;

    @GetMapping(path = "/admin")
    public String showOrders(Model model) {
        model.addAttribute("orders", coffeeOrderRepository.findAll());
        return "admin";
    }

    @GetMapping(path = "/admin/delete-order/{id}")
    public String deleteOrder(Model model, @PathVariable("id") long id) {
        coffeeOrderRepository.delete(id);
        model.addAttribute("orders", coffeeOrderRepository.findAll());
        return "admin";
    }


    @PostMapping(path = "/cart/checkout")
    public String checkout(CoffeeOrder coffeeOrder, HttpSession session, Model model) {
        if (buyerValidation(coffeeOrder, model)) {
            coffeeOrder.setOrderDate(LocalDateTime.now());
            coffeeOrderRepository.save(coffeeOrder);
            CoffeeOrder cart = (CoffeeOrder) session.getAttribute("cart");
            coffeeOrder.setCost(cart.getCost() + (double) session.getAttribute("delivery"));
            coffeeOrder.setCoffeeOrderItemList(cart.getCoffeeOrderItemList());
            coffeeOrder.getCoffeeOrderItemList().forEach((p) -> p.setCoffeeOrder(coffeeOrder));
            coffeeOrderRepository.save(coffeeOrder);
            model.addAttribute("success", "success");
            session.removeAttribute("cart");
        }
        return "cart";
    }

    @GetMapping(path = "")
    public String showCart() {
        return "cart";
    }

    @GetMapping(path = "/cart/removing/{id}")
    public String removeFromCart(HttpSession session, @PathVariable("id") long id) {
        CoffeeOrder cart = (CoffeeOrder) session.getAttribute("cart");
        cart.removeFromCart(id);
        amountShopping(cart, (Integer) session.getAttribute("n"),
                (Integer) session.getAttribute("x"),
                (Integer) session.getAttribute("m"));
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

    @PostMapping(path = "/cart/add")
    public String cartAdd(CoffeeOrderItem coffeeOrderItem, Model model, HttpSession session, HttpServletRequest request) {
        CoffeeOrder cart = (CoffeeOrder) session.getAttribute("cart");
        if (cart == null) {
            cart = new CoffeeOrder();
        }
        homeController.isConfigInSession(session);
        cart.addToCart(coffeeOrderItem);
        double delivery = amountShopping(cart, (Integer) session.getAttribute("n"),
                (Integer) session.getAttribute("x"),
                (Integer) session.getAttribute("m"));
        session.setAttribute("delivery", delivery);
        session.setAttribute("cart", cart);
        return "redirect:" + request.getHeader("referer");

    }

    private static double amountShopping(CoffeeOrder coffeeOrder, int n, int x, int m) {
        Double cost = coffeeOrder.getCoffeeOrderItemList()
                .stream()
                .mapToDouble(o -> (o.getQuantity() - (o.getQuantity() / (n + 1))) * o.getCoffeeType().getPrice())
                .sum();
        coffeeOrder.setCost(cost);
        return cost > x ? 0 : (long) m;
    }

    private static boolean buyerValidation(CoffeeOrder coffeeOrder, Model model) {
        if (coffeeOrder.getName().matches("^[ a-zA-Zа-яА-Я]+$")) {
            if (coffeeOrder.getEmail().matches("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")) {
                if (coffeeOrder.getPhone().matches("\\+375\\(\\d{2}\\)\\d{7}")) {
                    if (coffeeOrder.getDeliveryAddress().matches("^[ .,a-zA-Zа-яА-Я0-9]+$")) {
                        return true;
                    } else {
                        model.addAttribute("error_address", "Error address!");
                    }
                } else {
                    model.addAttribute("error_phone", "Error phone!");
                }
            } else {
                model.addAttribute("error_email", "Error Email!");
            }
        } else {
            model.addAttribute("error_name", "Error name!");
        }
        return false;
    }
}
