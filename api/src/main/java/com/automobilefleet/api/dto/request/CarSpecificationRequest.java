package com.automobilefleet.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record CarSpecificationRequest(
        @JsonProperty("car_id")
        UUID carId,

        @JsonProperty("specification_id")
        UUID specificationId
) {}