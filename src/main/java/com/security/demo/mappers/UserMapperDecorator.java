package com.security.demo.mappers;

import com.security.demo.dto.UserRegistrationDto;
import com.security.demo.model.User;

import org.springframework.beans.factory.annotation.Autowired;

public class UserMapperDecorator implements UserMapper {
    private UserMapper mapper;

    @Autowired
    public void setUserMapper(UserMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public UserRegistrationDto userToUserRegistrationDto(User user) {
        return mapper.userToUserRegistrationDto(user);
    }

    @Override
    public User userRegistrationDtoToUser(UserRegistrationDto user) {
        return mapper.userRegistrationDtoToUser(user);
    }
}