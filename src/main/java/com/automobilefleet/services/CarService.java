package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.CarRequest;
import com.automobilefleet.api.dto.response.CarResponse;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.Category;
import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.repositories.BrandRepository;
import com.automobilefleet.repositories.CarRepository;
import com.automobilefleet.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    public List<CarResponse> listOfCars() {
        return this.carRepository.findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(CarResponse::new)
                .toList();
    }

    public CarResponse getCarById(UUID id) {
        Optional<Car> response = this.carRepository.findById(id);

        if (response.isEmpty()) {
            throw new NotFoundException(ExceptionsConstants.CAR_NOT_FOUND);
        }

        return new CarResponse(response.get());
    }

    public List<CarResponse> findByCarBrand(String brandName) {
        return this.carRepository.findCarsByBrand_Name(brandName)
                .stream()
                .filter(Objects::nonNull)
                .map(CarResponse::new)
                .toList();
    }

    public List<CarResponse> findByCarAvailable() {
        return this.carRepository.findByAvailable(true)
                .stream()
                .filter(Objects::nonNull)
                .map(CarResponse::new)
                .toList();
    }

    public CarResponse saveCar(CarRequest request) {
        Brand brand = this.brandRepository.findById(request.brandId())
                .orElseThrow(() -> new NotFoundException(ExceptionsConstants.BRAND_NOT_FOUND));

        Category category = this.categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new NotFoundException(ExceptionsConstants.CATEGORY_NOT_FOUND));

        Car response = Car.builder()
                .name(request.name())
                .description(request.description())
                .dailyRate(request.dailyRate())
                .available(request.available())
                .licensePlate(request.licensePlate())
                .brand(brand)
                .category(category)
                .color(request.color())
                .build();

        return new CarResponse(this.carRepository.save(response));
    }

    public CarResponse updateCar(UUID id, CarRequest request) {
        Optional<Car> response = this.carRepository.findById(id);

        if (response.isEmpty()) {
            throw new NotFoundException(ExceptionsConstants.CAR_NOT_FOUND);
        }

        Brand brand = this.brandRepository.findById(request.brandId())
                .orElseThrow(() -> new NotFoundException(ExceptionsConstants.BRAND_NOT_FOUND));

        Category category = this.categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new NotFoundException(ExceptionsConstants.CATEGORY_NOT_FOUND));

        this.updateCar(response.get(), request, brand, category);
        return new CarResponse(this.carRepository.save(response.get()));
    }

    private void updateCar(Car car, CarRequest request, Brand brand, Category category) {
        car.setName(request.name());
        car.setDescription(request.description());
        car.setDailyRate(request.dailyRate());
        car.setAvailable(request.available());
        car.setLicensePlate(car.getLicensePlate());
        car.setBrand(brand);
        car.setCategory(category);
        car.setColor(car.getColor());
    }
}