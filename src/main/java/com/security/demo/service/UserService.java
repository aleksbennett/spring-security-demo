package com.security.demo.service;

import com.security.demo.dto.UserRegistrationDto;
import com.security.demo.model.User;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByEmail(String email);
    User save(UserRegistrationDto registration);
    long count();
}