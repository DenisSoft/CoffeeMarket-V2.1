package com.coffeemarket.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Dzianis on 13.09.2017.
 */
@Controller
@RequestMapping("/admin")
public class ConfigurationController {

    @GetMapping(path = "/configuration")
    public String showConfiguration() {
        return "configuration";
    }

    @GetMapping(path = "/configuration/update")
    public String updateConfiguration() {
        return "configuration";
    }
}