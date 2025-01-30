package com.automobilefleet.api.dto.projections;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.UUID;

@JsonPropertyOrder({"id", "carName", "specificationName", "specificationDescription"})
public interface CarSpecificationInfo {

    UUID getId();

    String getCarName();

    String getSpecificationName();

    String getSpecificationDescription();

}
