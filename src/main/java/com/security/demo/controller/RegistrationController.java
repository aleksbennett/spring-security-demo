package com.security.demo.controller;

import com.security.demo.dto.UserRegistrationDto;
import com.security.demo.model.User;
import com.security.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "register";
    }

    @PostMapping
    public String registerUserAccount(
            @ModelAttribute("user") @Validated UserRegistrationDto userDto, 
            BindingResult result){

        System.out.println("Attempting registration");
        
        User existing = userService.findByEmail(userDto.getEmail());
        if( existing != null ){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        System.out.println("user found: " + existing == null);

        if( result.hasErrors() ){
            return "registration";
        }

        System.out.println("Saving user");

        userService.save(userDto);
        return "redirect:/login?registered";
    }
}