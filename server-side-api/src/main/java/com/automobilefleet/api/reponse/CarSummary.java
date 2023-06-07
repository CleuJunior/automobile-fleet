package com.automobilefleet.api.reponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CarSummary {

    @JsonProperty("car_name")
    private String name;

    @JsonProperty("car_description")
    private String description;

    @JsonProperty("daily_rate")
    private Double dailyRate;

    @JsonProperty("car_available")
    private Boolean available;

    @JsonProperty("license_plate")
    private String licensePlate;

    @JsonProperty("brand_id")
    private String brand;

    @JsonProperty("category_id")
    private String category;

    @JsonProperty("car_color")
    private String color;

}