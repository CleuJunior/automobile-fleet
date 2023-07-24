package com.automobilefleet.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@JsonPropertyOrder({"_id"})
public class CarImageResponse {

    @JsonProperty("_id")
    private UUID id;

    @JsonProperty("car")
    private CarResponse car;

    @JsonProperty("image")
    private String linkImage;

    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime createdAt;
}
