package com.automobilefleet.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarRequest {

    @JsonProperty("name")
    @NotBlank(message = "Name can't be blank")
    @Size(min = 2, max = 255, message = "Name must contain between 2 to 255 characters!")
    private String name;

    @JsonProperty("description")
    @NotBlank(message = "Description can't be blank!")
    @Size(min = 5, max = 255, message = "Description must contain between 5 to 255 characters!")
    private String description;

    @JsonProperty("daily_rate")
    @PositiveOrZero(message = "Daily rate can't be negative or zero!")
    @NotNull(message = "Daily rate can't be null!")
    private Double dailyRate;

    @JsonProperty("available")
    private boolean isAvailable;

    @JsonProperty("license_plate")
    @NotBlank(message = "License plate can't be blank")
    @NotNull(message = "License plate can't be null")
    private String licensePlate;

    @JsonProperty("brand_id")
    private UUID brandId;

    @JsonProperty("category_id")
    private UUID categoryId;

    @JsonProperty("color")
    @NotBlank(message = "Color can't be blank")
    @NotNull(message = "Color can't be null")
    private String color;
}
