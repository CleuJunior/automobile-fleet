package com.automobilefleet.api.dto.response;

import com.automobilefleet.entities.CarSpecification;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@JsonPropertyOrder({"_id"})
public record CarSpecificationResponse(
        @JsonProperty("_id")
        UUID id,
        CarResponse car,
        SpecificationResponse specification
) {
        public CarSpecificationResponse(CarSpecification carSpecification) {
                this(
                        carSpecification.getId(),
                        new CarResponse(carSpecification.getCar()),
                        new SpecificationResponse(carSpecification.getSpecification())
                );
        }
}
