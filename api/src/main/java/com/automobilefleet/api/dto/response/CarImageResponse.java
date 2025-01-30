package com.automobilefleet.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@JsonPropertyOrder({"_id"})
public record CarImageResponse(
        @JsonProperty("_id")
        UUID id,
        @JsonProperty("car_id")
        UUID carId,
        @JsonProperty("image")
        String linkImage,
        @JsonProperty("created_at")
        @JsonFormat(shape = STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime createdAt

) {
}