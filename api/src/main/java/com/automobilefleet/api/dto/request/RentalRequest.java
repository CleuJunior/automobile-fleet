package com.automobilefleet.api.dto.request;

import com.automobilefleet.entities.Car;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDate;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@JsonPropertyOrder({"_id"})
public record RentalRequest(
        @JsonProperty("car_id")
        UUID carId,
        @JsonProperty("costumer_id")
        UUID customerId,
        @JsonFormat(shape = STRING, pattern = "dd-MM-yyyy")
        @JsonProperty("start_date")
        LocalDate startDate,
        @JsonFormat(shape = STRING, pattern = "dd-MM-yyyy")
        @JsonProperty("end_date")
        LocalDate endDate
) { }