package com.automobilefleet.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class CarSpecificationRequest {
    @JsonProperty("car_id")
    private UUID carId;

    @JsonProperty("specification_id")
    private UUID specificationId;
}