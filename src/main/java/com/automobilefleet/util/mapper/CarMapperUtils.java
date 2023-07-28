package com.automobilefleet.util.mapper;

import com.automobilefleet.api.request.CarRequest;
import com.automobilefleet.api.response.BrandResponse;
import com.automobilefleet.api.response.CarResponse;
import com.automobilefleet.api.response.CategoryResponse;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class CarMapperUtils {

    public static Car toCar(CarRequest request, Brand brand, Category category) {
        return Car.builder()
                .name(request.getName())
                .description(request.getDescription())
                .dailyRate(request.getDailyRate())
                .available(request.getAvailable())
                .licensePlate(request.getLicensePlate())
                .brand(brand)
                .category(category)
                .color(request.getColor())
                .build();
    }

    public static CarResponse toCarResponse(Car car) {
        BrandResponse brandReponse = BrandMapperUtils.toBrandReponse(car.getBrand());
        CategoryResponse categoryResponse = CategoryMapperUtils.toCategorResponse(car.getCategory());

        return CarResponse.builder()
                .id(car.getId())
                .name(car.getName())
                .description(car.getDescription())
                .dailyRate(car.getDailyRate())
                .available(car.getAvailable())
                .licensePlate(car.getLicensePlate())
                .brand(brandReponse)
                .category(categoryResponse)
                .color(car.getColor())
                .createdAt(car.getCreatedAt())
                .build();
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
