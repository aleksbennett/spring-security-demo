package com.security.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebRootController {
    
    @Value("${spring.application.name}")
    String appName;
    
    @GetMapping("/greeting")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }
    
    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }
}