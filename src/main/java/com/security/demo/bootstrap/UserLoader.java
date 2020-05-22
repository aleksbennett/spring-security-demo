package com.security.demo.bootstrap;

import com.security.demo.model.User;
import com.security.demo.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserLoader implements CommandLineRunner {
    
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if( userRepository.count() == 0 ){
            loadUserObjects();
        }
        
        System.out.println("User count: " + userRepository.count());
    }

    public void loadUserObjects(){
        userRepository.save(
            User.builder()
            .email("alekstest1@test.com")
            .firstName("Aleks")
            .lastName("Test")
            .password("Test 1")
            .build()
        );
    }
}