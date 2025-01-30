package com.automobilefleet.api.dto.request;

import com.automobilefleet.entities.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @Email
        String email,

        @NotBlank(message = "Username can't be blank!")
        @Size(min = 5, max = 255, message = "Username must contain between 5 to 255 characters!")
        String username,

        @NotBlank(message = "Password can't be blank!")
        @Size(min = 5, message = "Password must contain at least 5 characters!")
        String password,
        @NotNull(message = "Role can't be null!")
        Role role
) {

}