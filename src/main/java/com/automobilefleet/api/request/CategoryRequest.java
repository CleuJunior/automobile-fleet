package com.automobilefleet.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryRequest {
    @JsonProperty("name")
    @NotBlank(message = "Name can't be blank!")
    @Size(min = 2, max = 255, message = "Name must contain between 2 to 255 characters!")
    private String name;

    @JsonProperty("description")
    @NotBlank(message = "Description can't be blank!")
    @Size(min = 2, max = 255, message = "Description must contain between 10 to 255 characters!")
    private String description;
}
