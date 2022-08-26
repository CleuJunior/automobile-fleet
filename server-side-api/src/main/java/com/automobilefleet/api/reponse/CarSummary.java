package com.automobilefleet.api.reponse;

import com.automobilefleet.entities.Brand;
import com.automobilefleet.entities.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarSummary {

    @JsonProperty("car_name")
    private String name;

    @JsonProperty("license_plate")
    private String licensePlate;

    @JsonProperty("car_color")
    private String color;

}