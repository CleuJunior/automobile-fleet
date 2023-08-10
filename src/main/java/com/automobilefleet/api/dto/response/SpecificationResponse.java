package com.automobilefleet.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.UUID;

@JsonPropertyOrder({"_id"})
public record SpecificationResponse(
        @JsonProperty("_id")
         UUID id,

        @JsonProperty("specification_name")
        String name,

        @JsonProperty("specification_description")
        String description
) {}