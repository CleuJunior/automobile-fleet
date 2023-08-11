package com.automobilefleet.api.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@JsonPropertyOrder({"_id"})
public record RentalRequest(
        @JsonProperty("car_id")
        UUID carId,
        @JsonProperty("costumer_id")
        UUID costumerId,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        @JsonProperty("start_date")
        LocalDate startDate,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        @JsonProperty("end_date")
        LocalDate endDate
) { }