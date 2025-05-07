package com.automobilefleet.api.dto.projections;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.UUID;

@JsonPropertyOrder({"id", "name", "description",  "category", "brand", "licensePlate", "color"})
public interface CarInfo {

    UUID getId();

    String getName();

    String getDescription();

    String getCategory();

    String getBrand();

    String getLicensePlate();

    String getColor();

}