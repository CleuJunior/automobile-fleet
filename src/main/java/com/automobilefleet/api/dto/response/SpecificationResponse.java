package com.automobilefleet.api.dto.response;

import com.automobilefleet.entities.Specification;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.UUID;

@JsonPropertyOrder({"_id"})
public record SpecificationResponse(
        @JsonProperty("_id")
         UUID id,
        String name,
        String description
) {
    public SpecificationResponse(Specification specification) {
        this(specification.getId(), specification.getName(), specification.getDescription());
    }
}