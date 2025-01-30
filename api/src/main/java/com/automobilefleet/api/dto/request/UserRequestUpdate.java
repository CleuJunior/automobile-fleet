package com.automobilefleet.api.dto.request;

import com.automobilefleet.entities.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequestUpdate(
        @Email
        String email,

        @NotBlank(message = "Username can't be blank!")
        @Size(min = 5, max = 255, message = "Username must contain between 5 to 255 characters!")
        String username,

        @NotNull(message = "Role can't be null!")
        Role role
) { }