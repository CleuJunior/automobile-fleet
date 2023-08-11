package com.automobilefleet.api.dto.response;

import com.automobilefleet.entities.Brand;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.UUID;

@JsonPropertyOrder({"_id"})
public record BrandResponse(@JsonProperty("_id") UUID id, String name) {

    public BrandResponse(Brand brand) {
        this(brand.getId(), brand.getName());
    }
}