package com.security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebRootController {
    
    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/terms")
    public String terms() {
        return "terms";
    }
}