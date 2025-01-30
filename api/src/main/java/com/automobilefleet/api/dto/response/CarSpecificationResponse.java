package com.automobilefleet.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.UUID;

@JsonPropertyOrder({"_id"})
public record CarSpecificationResponse(
        @JsonProperty("_id")
        UUID id,
        CarResponse car,
        SpecificationResponse specification
) {
}
