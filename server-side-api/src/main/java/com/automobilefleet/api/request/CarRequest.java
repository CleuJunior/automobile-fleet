package com.automobilefleet.api.request;

import com.automobilefleet.entities.Brand;
import com.automobilefleet.entities.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarRequest {

    @JsonProperty("car_name")
    private String name;

    @JsonProperty("car_description")
    private String description;


    @JsonProperty("daily_rate")
    private Double dailyRate;

    @JsonProperty("car_avaliable")
    private Boolean avaliable;

    @JsonProperty("license_plate")
    private String licensePlate;

    @JsonProperty("brand_id")
    private Brand brand;

    @JsonProperty("category_id")
    private Category category;

    @JsonProperty("car_color")
    private String color;

    @JsonProperty("created_at")
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime createdAt;
}
