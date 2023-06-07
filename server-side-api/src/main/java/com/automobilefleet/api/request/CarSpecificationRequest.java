package com.automobilefleet.api.request;

import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.Specification;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CarSpecificationRequest {

    @JsonProperty("car_id")
    private Car car;

    @JsonProperty("specification_id")
    private Specification specification;
}
