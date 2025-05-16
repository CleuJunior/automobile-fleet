package com.automobilefleet.api.dto.request;

import com.automobilefleet.entities.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequestUpdate(
        @JsonProperty("first_name")
        @NotBlank(message = "First name can't be blank!")
        @Size(min = 3, max = 255, message = "First name must contain between 3 to 255 characters!")
        String firstName,

        @JsonProperty("last_name")
        @NotBlank(message = "Last name can't be blank!")
        @Size(min = 3, max = 255, message = "Last name must contain between 3 to 255 characters!")
        String lastName,

        @Email
        String email,

        @NotBlank(message = "Username can't be blank!")
        @Size(min = 5, max = 255, message = "Username must contain between 5 to 255 characters!")
        String username,

        @NotNull(message = "Role can't be null!")
        Role role
) { }