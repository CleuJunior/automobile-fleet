package com.automobilefleet.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SpecificationRequest(
        @JsonProperty("specification_name")
        String name,

        @JsonProperty("specification_description")
        String description
) {}