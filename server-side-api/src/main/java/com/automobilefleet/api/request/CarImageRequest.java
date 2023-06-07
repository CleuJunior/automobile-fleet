package com.automobilefleet.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CarImageRequest {

    @JsonProperty("car_id")
    private Long carId;

    @JsonProperty("image")
    private byte[] image;

}
