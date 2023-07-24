package com.automobilefleet.api.request;

import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.Specification;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class CarSpecificationRequest {

    @JsonProperty("car_id")
    private UUID carId;

    @JsonProperty("specification_id")
    private UUID specificationId;
}
