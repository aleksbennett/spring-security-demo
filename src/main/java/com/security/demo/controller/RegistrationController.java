package com.security.demo.controller;

import javax.validation.Valid;

import com.security.demo.dto.UserRegistrationDto;
import com.security.demo.model.User;
import com.security.demo.service.UserService;
import com.security.demo.validation.UserAlreadyExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
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
    public String registerUserAccount(Model model, @ModelAttribute("user") @Valid UserRegistrationDto userDto, 
            BindingResult result) {

        System.out.println("Result errors: " + result.getErrorCount());
        
        if( !result.hasErrors() ){
            try {
                //User registered = userService.registerNewUser(userDto);
                userService.registerNewUser(userDto);
                return "redirect:/login?registered";
            } catch (UserAlreadyExistsException e) {
                //model.addAttribute("error", "An account for that username/email already exists.");
                //model.addAttribute("errors", result.getAllErrors());
            }

            //java.util.List<org.springframework.validation.ObjectError> testList = result.getAllErrors();
            //testList.get(1).
        }

        return "register";


        /*
        User existing = userService.findByEmail(userDto.getEmail());
        if( existing != null ){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if( result.hasErrors() ){
            return "registration";
        }

        userService.save(userDto);
        
        return "redirect:/login?registered";
        */
    }
}