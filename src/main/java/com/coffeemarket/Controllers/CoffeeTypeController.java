package com.coffeemarket.Controllers;

import com.coffeemarket.entity.CoffeeOrderItem;
import com.coffeemarket.repository.BlockQuoteRepository;
import com.coffeemarket.repository.CoffeeOrderItemRepository;
import com.coffeemarket.repository.CoffeeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Dzianis on 29.08.2017.
 */
@Controller
public class CoffeeTypeController {

    private static final char DISABLED = 'N';

    @Autowired
    private CoffeeTypeRepository coffeeTypeRepository;

    @Autowired
    private BlockQuoteRepository blockQuoteRepository;

    @Autowired
    private CoffeeOrderItemRepository coffeeOrderItemRepository;

    @ModelAttribute("coffeeOrderItem")
    public CoffeeOrderItem coffeeOrderItem() {
        return new CoffeeOrderItem();
    }

    @GetMapping(path = "/coffee/{sort}")
    public String showHomePage(@PathVariable("sort") String sort, Model model) {
        switch (sort) {
            case "without_sorting":
                model.addAttribute("coffee_types", coffeeTypeRepository.findAllByDisabled(DISABLED));
                break;
            case "cheaper":
                model.addAttribute("coffee_types", coffeeTypeRepository.findByDisabledOrderByPriceAsc(DISABLED));
                break;
            case "expensive":
                model.addAttribute("coffee_types", coffeeTypeRepository.findByDisabledOrderByPriceDesc(DISABLED));
                break;
            case "popularity":
                model.addAttribute("coffee_types", coffeeOrderItemRepository.findAllHit(DISABLED));
                break;
        }
        addQuoteInAttribute(model);
        return "coffee";
    }

    @GetMapping(path = "/coffee/hit")
    public String showHit(Model model) {
        model.addAttribute("coffee_types", coffeeOrderItemRepository.findAllHit(DISABLED));
        addQuoteInAttribute(model);
        return "hit";
    }

    @GetMapping(path = "/coffee/new")
    public String showNew(Model model) {
        model.addAttribute("coffee_types", coffeeTypeRepository.findTop2ByDisabledOrderByIdDesc(DISABLED));
        addQuoteInAttribute(model);
        return "new";
    }


    @GetMapping(path = "/coffee/search")
    public String search(Model model) {
        addQuoteInAttribute(model);
        return "search";
    }

    @PostMapping(path = "/coffee/search")
    public String showSearchResults(Model model, @RequestParam String name) {
        model.addAttribute("coffee_types",
                coffeeTypeRepository.findByDisabledAndNameEnContainsOrNameRuContains(DISABLED, name, name));
        addQuoteInAttribute(model);
        return "search";
    }

    @GetMapping(path = "/admin/coffee/new")
    public String showCoffeeAdd() {
        return "coffee-new";
    }

    @GetMapping(path = "/admin/coffee/delete/{id}")
    public String showCoffeeDelete(@PathVariable("id") Long id, final RedirectAttributes redirectAttributes) {
        try {
            coffeeTypeRepository.delete(id);
        }catch (Exception ex){
            redirectAttributes.addFlashAttribute("report", "Ошибка удаления! Кофе присутствует в заказах");
        }
        return "redirect:/admin/coffee/all";
    }

    @GetMapping(path = "/admin/coffee/all")
    public String showCoffeeAll( @ModelAttribute("report") String report, Model model) {
        if(!report.equals("")){
            model.addAttribute("report", report);
        }
        model.addAttribute("coffee_types", coffeeTypeRepository.findAll());
        return "coffee-all";
    }

    @GetMapping(path = "/admin/coffee/update")
    public String showCoffeeUpdate() {
        return "coffee-new";
    }

    private void addQuoteInAttribute(Model model){
        long id = 1 + new Random().nextInt((int) blockQuoteRepository.count());
        model.addAttribute("quote", blockQuoteRepository.findOne(id));
    }
}
