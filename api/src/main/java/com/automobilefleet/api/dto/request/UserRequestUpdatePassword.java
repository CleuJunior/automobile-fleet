package com.automobilefleet.api.dto.request;

import jakarta.validation.constraints.Size;

public record UserRequestUpdatePassword(
        @Size(min = 5, message = "Password must contain at least 5 characters!")
        String password,
        @Size(min = 5, message = "Password must contain at least 5 characters!")
        String confirmPassword
) { }