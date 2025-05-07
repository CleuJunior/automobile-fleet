package com.automobilefleet.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@JsonPropertyOrder({"_id", "name", "birth_date", "email", "driver_license", "address", "phone_number", "created_at", "updatedAt"})
public record CustomerResponse(
        @JsonProperty("_id") UUID id,

        String name,

        @JsonProperty("birth_date")
        @JsonFormat(shape = STRING, pattern = "dd-MM-yyyy")
        LocalDate birthdate,

        String email,

        @JsonProperty("driver_license")
        String driverLicense,

        String address,

        @JsonProperty("phone_number")
        String phone,

        @JsonFormat(shape = STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        @JsonProperty("created_at")
        LocalDateTime createdAt,

        @JsonFormat(shape = STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        @JsonProperty("updated_at")
        LocalDateTime updatedAt
) {
}
