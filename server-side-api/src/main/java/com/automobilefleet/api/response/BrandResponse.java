package com.automobilefleet.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@JsonPropertyOrder({"_id"})
public class BrandResponse {

    @JsonProperty("_id")
    private UUID id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("created_at")
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime createdAt;

}
