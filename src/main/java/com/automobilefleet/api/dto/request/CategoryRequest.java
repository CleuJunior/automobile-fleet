package com.automobilefleet.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public record CategoryRequest(
        @NotBlank(message = "Name can't be blank!")
        @Size(min = 2, max = 255, message = "Name must contain between 2 to 255 characters!")
        String name,

        @NotBlank(message = "Description can't be blank!")
        @Size(min = 10, max = 255, message = "Description must contain between 10 to 255 characters!")
        String description
) { }
