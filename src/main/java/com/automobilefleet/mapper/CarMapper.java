package com.automobilefleet.mapper;

import com.automobilefleet.api.dto.response.BrandResponse;
import com.automobilefleet.api.dto.response.CarResponse;
import com.automobilefleet.api.dto.response.CategoryResponse;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.Category;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CarMapper implements Function<Car, CarResponse> {

    @Override
    public CarResponse apply(Car car) {
        return new CarResponse(
                car.getId(),
                car.getName(),
                car.getDescription(),
                car.getDailyRate(),
                car.isAvailable(),
                car.getLicensePlate(),
                this.toBrandResponse(car.getBrand()),
                this.toCategoryResponse(car.getCategory()),
                car.getColor()
        );
    }

    private BrandResponse toBrandResponse(Brand brand) {
        return new BrandResponse(brand.getId(), brand.getName());
    }

    private CategoryResponse toCategoryResponse(Category category) {
        return new CategoryResponse(category.getId(), category.getName(), category.getDescription());
    }
}