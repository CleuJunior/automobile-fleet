package com.automobilefleet.api.reponse;

import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.Specification;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@JsonPropertyOrder({"_id"})
public class CarSpecificationResponse {

    @JsonProperty("_id")
    private Long id;

    @JsonProperty("car_id")
    private Car car;

    @JsonProperty("specification_id")
    private Specification specification;
}
