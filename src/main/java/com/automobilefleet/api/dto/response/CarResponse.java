package com.automobilefleet.api.dto.response;

import com.automobilefleet.entities.Car;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@JsonPropertyOrder({"_id", "name", "description", "daily_rate", "available", "license_plate"})
public record CarResponse(
        @JsonProperty("_id")
        UUID id,
        String name,
        String description,

        @JsonProperty("daily_rate")
        Double dailyRate,

        boolean available,
        @JsonProperty("license_plate")
        String licensePlate,
        BrandResponse brand,
        CategoryResponse category,
        String color,
        @JsonProperty("created_at")
        @JsonFormat(shape = STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime createdAt

) {

    public CarResponse(Car car) {
        this(
                car.getId(),
                car.getName(),
                car.getDescription(),
                car.getDailyRate(),
                car.isAvailable(),
                car.getLicensePlate(),
                new BrandResponse(car.getBrand()),
                new CategoryResponse(car.getCategory()),
                car.getColor(),
                car.getCreatedAt()
        );
    }
}