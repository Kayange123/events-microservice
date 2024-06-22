package com.codinghints.eventsmicroservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequest {
    @NotEmpty(message = "FirstName can not be empty or null")
    private String firstName;
    @NotEmpty(message = "LastName can not be empty or null")
    private String lastName;
    @NotEmpty(message = "Email can not be empty")
    @Email(message = "Invalid email address")
    private String email;
    @NotEmpty(message = "Password can not be empty")
    private String password;
    private String bio;
    private String phone;
}
