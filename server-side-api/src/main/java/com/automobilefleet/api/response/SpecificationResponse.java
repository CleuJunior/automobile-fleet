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
public class SpecificationResponse {

    @JsonProperty("_id")
    private Long id;

    @JsonProperty("specification_name")
    private String name;

    @JsonProperty("specification_description")
    private String description;

    @JsonProperty("created_at")
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;
}
