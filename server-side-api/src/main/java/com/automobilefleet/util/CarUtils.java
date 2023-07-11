package com.automobilefleet.util;

import com.automobilefleet.api.request.CarRequest;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.Category;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CarUtils {

    public static Car ofCar(CarRequest request, Brand brand, Category category) {
        return new Car(
                request.getName(),
                request.getDescription(),
                request.getDailyRate(),
                request.getAvailable(),
                request.getLicensePlate(),
                brand,
                category,
                request.getColor()
        );
    }

    public static void updateCar(Car car, CarRequest request, Brand brand, Category category) {
        car.setName(request.getName());
        car.setDescription(request.getDescription());
        car.setDailyRate(request.getDailyRate());
        car.setAvailable(request.getAvailable());
        car.setLicensePlate(car.getLicensePlate());
        car.setBrand(brand);
        car.setCategory(category);
        car.setColor(car.getColor());
    }
}
