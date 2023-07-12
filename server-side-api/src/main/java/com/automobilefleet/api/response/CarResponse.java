package com.automobilefleet.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@JsonPropertyOrder({"_id"})
public class CarResponse {

    @JsonProperty("_id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("daily_rate")
    private Double dailyRate;

    @JsonProperty("avaliable")
    private Boolean available;

    @JsonProperty("license_plate")
    private String licensePlate;

    @JsonProperty("brand")
    private BrandResponse brand;

    @JsonProperty("category")
    private CategoryResponse category;

    @JsonProperty("color")
    private String color;

    @JsonProperty("created_at")
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime createdAt;
}
