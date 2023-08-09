package com.automobilefleet.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.UUID;

@JsonPropertyOrder({"_id"})
public record CarResponse(
        @JsonProperty("_id")
        UUID id,
        String name,
        String description,

        @JsonProperty("daily_rate")
        Double dailyRate,

        boolean available,
        @JsonProperty("license_plate")
        String licensePlate,
        BrandResponse brand,
        CategoryResponse category,
        String color

) {}

