package com.automobilefleet.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class CarImageRequest {

    @JsonProperty("car_id")
    private UUID carId;

    @JsonProperty("image")
    private String linkImage;

}
