package com.automobilefleet.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CarImageRequest(
        @JsonProperty("car_id")
        @NotBlank(message = "Car ID can't be blank")
        @NotNull(message = "Car ID can't be null")
        UUID carId,
        @JsonProperty("image")
        @NotBlank(message = "Image can't be blank")
        @NotNull(message = "Image can't be null")
        String linkImage
) { }