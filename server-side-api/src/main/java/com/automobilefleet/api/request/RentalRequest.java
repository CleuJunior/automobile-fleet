package com.automobilefleet.api.request;

import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.Costumer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@NoArgsConstructor
@Getter
@Setter
@JsonPropertyOrder({"_id"})
public class RentalRequest {

    @JsonProperty("car_id")
    private Car car;

    @JsonProperty("costumer_id")
    private Costumer costumer;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy")
    @JsonProperty("start_date")
    private LocalDate startDate;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy")
    @JsonProperty("end_date")
    private LocalDate endDate;

    @JsonProperty("total")
    private Double total;
}