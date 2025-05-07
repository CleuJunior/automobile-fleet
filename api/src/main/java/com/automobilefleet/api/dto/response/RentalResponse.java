package com.automobilefleet.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@JsonPropertyOrder({"_id"})
public record RentalResponse(
        @JsonProperty("_id")
        UUID id,
        CarResponse car,
        CustomerResponse customer,
        @JsonFormat(shape = STRING, pattern = "dd-MM-yyyy")
        @JsonProperty("start_date")
        LocalDate startDate,
        @JsonFormat(shape = STRING, pattern = "dd-MM-yyyy")
        @JsonProperty("end_date")
        LocalDate endDate,
        Double total,
        @JsonProperty("created_at")
        @JsonFormat(shape = STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime createdAt,
        @JsonProperty("updated_at")
        @JsonFormat(shape = STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime updatedAt
) {
}