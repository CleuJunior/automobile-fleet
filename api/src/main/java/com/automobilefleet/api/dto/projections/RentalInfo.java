package com.automobilefleet.api.dto.projections;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDate;
import java.util.UUID;
@JsonPropertyOrder(
        {"id", "customer", "cellPhone", "car",  "licensePlate", "color", "brand", "color", "startDate", "endDate", "totalDays", "total"}
)
public interface RentalInfo {
    UUID getId();

    String getCustomer();

    String getCellPhone();

    String getCar();

    String getLicensePlate();

    String getColor();

    String getBrand();

    LocalDate getStartDate();

    LocalDate getEndDate();

    Integer getTotalDays();

    Double getTotal();
}