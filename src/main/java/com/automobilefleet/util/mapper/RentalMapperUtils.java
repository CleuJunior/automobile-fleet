package com.automobilefleet.util.mapper;

import com.automobilefleet.api.dto.request.RentalRequest;
import com.automobilefleet.api.dto.response.CarResponse;
import com.automobilefleet.api.dto.response.CostumerResponse;
import com.automobilefleet.api.dto.response.RentalResponse;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.Costumer;
import com.automobilefleet.entities.Rental;
import lombok.experimental.UtilityClass;

import java.time.temporal.ChronoUnit;

@UtilityClass
public class RentalMapperUtils {

    public static Rental toRental(Car car, Costumer costumer, RentalRequest request) {
        Double total = totalRental(request, car);

        return new Rental(car, costumer, request.getStartDate(), request.getEndDate(), total);
    }

    public static RentalResponse toRentalReponse(Rental rental) {
        CarResponse carResponse = CarMapperUtils.toCarResponse(rental.getCar());
        CostumerResponse costumerResponse = CostumerMapperUtils.toCostumerReponse(rental.getCostumer());

        return RentalResponse.builder()
                .id(rental.getId())
                .car(carResponse)
                .costumer(costumerResponse)
                .startDate(rental.getStartDate())
                .endDate(rental.getEndDate())
                .total(rental.getTotal())
                .createdAt(rental.getCreatedAt())
                .updatedAt(rental.getUpdatedAt())
                .build();
    }

    public static void updateRental(Rental rental, RentalRequest request, Car car, Costumer costumer) {
        Double total = totalRental(request, car);

        rental.setCar(car);
        rental.setCostumer(costumer);
        rental.setStartDate(request.getStartDate());
        rental.setEndDate(request.getEndDate());
        rental.setTotal(total);
    }

    private static Double totalRental(RentalRequest request, Car car) {
        Long days = ChronoUnit.DAYS.between(request.getStartDate(), request.getEndDate());
        return car.getDailyRate() * days;
    }
}