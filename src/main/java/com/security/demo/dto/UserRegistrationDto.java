package com.security.demo.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;

import com.security.demo.validation.FieldMatch;
import com.security.demo.validation.ValidEmail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldMatch.List({
    @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
    @FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match")
})
public class UserRegistrationDto {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String password;
    private String confirmPassword;

    @ValidEmail
    @NotBlank
    private String email;
    private String confirmEmail;

    @AssertTrue(message = "You must accept the terms and conditions.")
    private Boolean terms;

    private String title;
    private String houseName;
    private String street;
    private String town;
    private String county;
    private String country;
    private String postcode;

    private String companyRole;
    private String companyName;
}