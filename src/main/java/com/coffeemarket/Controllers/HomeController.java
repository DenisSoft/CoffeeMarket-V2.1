package com.coffeemarket.Controllers;

import com.coffeemarket.entity.CoffeeOrder;
import com.coffeemarket.repository.CoffeeTypeRepository;
import com.coffeemarket.repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by Dzianis on 26.08.2017.
 */
@Controller
public class HomeController {

    @Autowired
    private CoffeeTypeRepository coffeeTypeRepository;

    @Autowired
    private ConfigurationRepository configurationRepository;

    @GetMapping(path = "/")
    public String showHomePage(HttpSession session) {
        isConfigInSession(session);
        return "home";
    }

    @GetMapping(path = "/contact")
    public String showContact() {
        return "contact";
    }

    @GetMapping(path = "/about-us")
    public String showAboutAs() {
        return "about-us";
    }

    @GetMapping(path = "/shipping")
    public String showShipping(HttpSession session) {
        isConfigInSession(session);
        return "shipping";
    }

    public void isConfigInSession(HttpSession session) {
        if (session.getAttribute("n") == null){
            session.setAttribute("n", configurationRepository.findOne("n").getValue());
            session.setAttribute("x", configurationRepository.findOne("x").getValue());
            session.setAttribute("m", configurationRepository.findOne("m").getValue());
        }
    }
}
