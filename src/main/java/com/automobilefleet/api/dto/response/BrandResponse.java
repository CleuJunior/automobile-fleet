package com.automobilefleet.api.dto.response;

import com.automobilefleet.entities.Brand;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@JsonPropertyOrder({"_id", "name", "created_at"})
public record BrandResponse(
        @JsonProperty("_id") UUID id,
        String name,

        @JsonProperty("created_at")
        @JsonFormat(shape = STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime createdAt) {

    public BrandResponse(Brand brand) {
        this(brand.getId(), brand.getName(), brand.getCreatedAt());
    }
}