package com.automobilefleet.api.dto.response;

import com.automobilefleet.entities.Specification;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@JsonPropertyOrder({"_id"})
public record SpecificationResponse(
        @JsonProperty("_id")
        UUID id,
        String name,
        String description,
        @JsonProperty("created_at")
        @JsonFormat(shape = STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime createdAt

) {

    public SpecificationResponse(Specification specification) {
        this(specification.getId(), specification.getName(), specification.getDescription(), specification.getCreatedAt());
    }
}