package com.automobilefleet.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CarRequest(
        @NotBlank(message = "Name can't be blank")
        @Size(min = 2, max = 255, message = "Name must contain between 2 to 255 characters!")
        String name,
        @NotBlank(message = "Description can't be blank!")
        @Size(min = 5, max = 255, message = "Description must contain between 5 to 255 characters!")
        String description,
        @JsonProperty("daily_rate")
        @PositiveOrZero(message = "Daily rate can't be negative or zero!")
        @NotNull(message = "Daily rate can't be null!")
        Double dailyRate,
        boolean available,
        @JsonProperty("license_plate")
        @NotBlank(message = "License plate can't be blank")
        @NotNull(message = "License plate can't be null")
        String licensePlate,
        @JsonProperty("brand_id")
        UUID brandId,
        @JsonProperty("category_id")
        UUID categoryId,
        @NotBlank(message = "Color can't be blank")
        @NotNull(message = "Color can't be null")
        String color

) { }
