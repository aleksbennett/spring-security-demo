package com.security.demo.validation;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = -2832764066471944507L;
    private String message;

    @Override
    public String toString(){
        return message + " :: " + super.toString();
    }
}