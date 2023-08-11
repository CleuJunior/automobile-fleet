package com.automobilefleet.api.dto.response;

import com.automobilefleet.entities.Rental;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDate;
import java.util.UUID;

@JsonPropertyOrder({"_id"})
public record RentalResponse(
        @JsonProperty("_id")
        UUID id,
        CarResponse car,
        CostumerResponse costumer,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        @JsonProperty("start_date")
        LocalDate startDate,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        @JsonProperty("end_date")
        LocalDate endDate,
        Double total
) {

    public RentalResponse(Rental rental){
        this(
                rental.getId(),
                new CarResponse(rental.getCar()),
                new CostumerResponse(rental.getCostumer()),
                rental.getStartDate(),
                rental.getEndDate(),
                rental.getTotal()
        );
    }
}