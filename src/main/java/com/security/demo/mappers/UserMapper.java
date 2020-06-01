package com.security.demo.mappers;

import com.security.demo.dto.UserRegistrationDto;
import com.security.demo.model.User;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(uses = { DateMapper.class })
@DecoratedWith(UserMapperDecorator.class)
public interface UserMapper {
    UserRegistrationDto userToUserRegistrationDto(User user);
    User userRegistrationDtoToUser(UserRegistrationDto user);
}