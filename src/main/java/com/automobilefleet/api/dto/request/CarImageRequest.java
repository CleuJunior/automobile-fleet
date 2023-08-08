package com.automobilefleet.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class CarImageRequest {
    @JsonProperty("car_id")
    @NotBlank(message = "Car ID can't be blank")
    @NotNull(message = "Car ID can't be null")
    private UUID carId;

    @JsonProperty("image")
    @NotBlank(message = "Image can't be blank")
    @NotNull(message = "Image can't be null")
    private String linkImage;
}