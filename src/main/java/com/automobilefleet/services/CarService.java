package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.CarRequest;
import com.automobilefleet.api.dto.response.CarResponse;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.Category;
import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.CarNotFoundException;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.mapper.CarMapper;
import com.automobilefleet.repositories.BrandRepository;
import com.automobilefleet.repositories.CarRepository;
import com.automobilefleet.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final CarMapper mapper;

    public List<CarResponse> listOfCars() {
        return this.carRepository.findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(this.mapper)
                .toList();
    }

    public CarResponse getCarById(UUID id) {
        Car response = this.carRepository.findById(id).
                orElseThrow(CarNotFoundException::new);

        return this.mapper.apply(response);
    }

    public List<CarResponse> findByCarBrand(String brandName) {
        return this.carRepository.findCarsByBrand_Name(brandName)
                .stream()
                .filter(Objects::nonNull)
                .map(this.mapper)
                .toList();
    }

    public List<CarResponse> findByCarAvailable() {
        return this.carRepository.findByAvailable(true)
                .stream()
                .filter(Objects::nonNull)
                .map(this.mapper)
                .toList();
    }

    public CarResponse saveCar(CarRequest request) {
        Brand brand = this.brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new NotFoundException(ExceptionsConstants.BRAND_NOT_FOUND));

        Category category = this.categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new NotFoundException(ExceptionsConstants.CATEGORY_NOT_FOUND));

        Car response = Car.builder()
                .name(request.getName())
                .description(request.getDescription())
                .dailyRate(request.getDailyRate())
                .available(request.isAvailable())
                .licensePlate(request.getLicensePlate())
                .brand(brand)
                .category(category)
                .color(request.getColor())
                .build();

        return this.mapper.apply(this.carRepository.save(response));
    }

    public CarResponse updateCar(UUID id, CarRequest request) {
        Car response = this.carRepository.findById(id).
                orElseThrow(CarNotFoundException::new);

        Brand brand = this.brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new NotFoundException(ExceptionsConstants.BRAND_NOT_FOUND));

        Category category = this.categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new NotFoundException(ExceptionsConstants.CATEGORY_NOT_FOUND));

        this.updateCar(response, request, brand, category);
        return this.mapper.apply(this.carRepository.save(response));
    }

    private void updateCar(Car car, CarRequest request, Brand brand, Category category) {
        car.setName(request.getName());
        car.setDescription(request.getDescription());
        car.setDailyRate(request.getDailyRate());
        car.setAvailable(request.isAvailable());
        car.setLicensePlate(car.getLicensePlate());
        car.setBrand(brand);
        car.setCategory(category);
        car.setColor(car.getColor());
    }
}