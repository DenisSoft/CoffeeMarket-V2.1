package com.coffeemarket.Controllers;

import com.coffeemarket.repository.CoffeeTypeRepository;
import com.coffeemarket.repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by Dzianis on 13.09.2017.
 */
@Controller
@RequestMapping("/admin")
public class BlockQuoteController {

    @GetMapping(path = "/quote/all")
    public String showQuote() {
        return "quote-all";
    }

    @GetMapping(path = "/quote/delete")
    public String deleteQuote() {
        return "quote-all";
    }

    @GetMapping(path = "/quote/update")
    public String updateQuote() {
        return "quote-new";
    }

    @GetMapping(path = "/quote/new")
    public String newQuote() {
        return "quote-new";
    }
}