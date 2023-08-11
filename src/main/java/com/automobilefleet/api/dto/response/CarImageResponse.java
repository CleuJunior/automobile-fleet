package com.automobilefleet.api.dto.response;

import com.automobilefleet.entities.CarImage;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.UUID;

@JsonPropertyOrder({"_id"})
public record CarImageResponse(
        @JsonProperty("_id")
        UUID id,
        CarResponse car,
        @JsonProperty("image")
        String linkImage

) {

    public CarImageResponse(CarImage carImage) {
        this(carImage.getId(), new CarResponse(carImage.getCar()), carImage.getLinkImage());
    }

}