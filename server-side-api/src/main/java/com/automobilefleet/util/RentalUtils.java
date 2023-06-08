package com.automobilefleet.util;

import com.automobilefleet.api.request.RentalRequest;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.Costumer;
import com.automobilefleet.entities.Rental;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.temporal.ChronoUnit;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RentalUtils {

    public static Rental ofRental(Car car, Costumer costumer, RentalRequest request) {
        Double total = totalRental(request, car);

        return new Rental(
                car,
                costumer,
                request.getStartDate(),
                request.getEndDate(),
                total
        );
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
