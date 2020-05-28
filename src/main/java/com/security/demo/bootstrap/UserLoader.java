package com.security.demo.bootstrap;

import com.security.demo.dto.UserRegistrationDto;
import com.security.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserLoader implements CommandLineRunner {
    
    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        if( userService.count() == 0 ){
            loadUserObjects();
        }
        
        System.out.println("User count: " + userService.count());
    }

    public void loadUserObjects(){
        userService.save(UserRegistrationDto.builder()
            .email("alekstest1@test.com")
            .confirmEmail("alekstest1@test.com")
            .firstName("Aleks")
            .lastName("Test")
            .password("testing123")
            .confirmPassword("testing123")
            .build()
        );

        userService.save(UserRegistrationDto.builder()
            .email("alekstest2@test.com")
            .confirmEmail("alekstest2@test.com")
            .firstName("Aleks2")
            .lastName("Test2")
            .password("testing123")
            .confirmPassword("testing123")
            .build()
        );
    }
}