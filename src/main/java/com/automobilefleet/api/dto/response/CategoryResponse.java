package com.automobilefleet.api.dto.response;

import com.automobilefleet.entities.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.UUID;

@JsonPropertyOrder({"_id"})
public record CategoryResponse(
        @JsonProperty("_id")
        UUID id,
        String name,
        String description
) {
        public CategoryResponse(Category category) {
                this(category.getId(), category.getName(), category.getDescription());
        }
}