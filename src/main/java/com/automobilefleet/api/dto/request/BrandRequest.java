package com.automobilefleet.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BrandRequest(
        @NotBlank(message = "Name can't be blank")
        @Size(min = 2, max = 255, message = "Name must contain between 2 to 255 characters!")
        String name
) {}