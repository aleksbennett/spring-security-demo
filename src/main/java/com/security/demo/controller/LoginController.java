package com.security.demo.controller;

import com.security.demo.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    
    @GetMapping("/login")
	public String login(Model model) {
		return "login";
    }
    
    @GetMapping("/user")
    public String userIndex() {
        return "user";
    }
    
    @GetMapping("/users")
    public String users(Model model){

        model.addAttribute("users", userService.findAllUsers());

        return "users";
    }
}