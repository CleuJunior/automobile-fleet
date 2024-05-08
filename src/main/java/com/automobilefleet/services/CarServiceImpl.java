package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.CarRequest;
import com.automobilefleet.api.dto.response.CarResponse;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.Category;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.mapper.CarMapper;
import com.automobilefleet.repositories.BrandRepository;
import com.automobilefleet.repositories.CarRepository;
import com.automobilefleet.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.automobilefleet.exceptions.ExceptionsConstants.BRAND_NOT_FOUND;
import static com.automobilefleet.exceptions.ExceptionsConstants.CAR_NOT_FOUND;
import static com.automobilefleet.exceptions.ExceptionsConstants.CATEGORY_NOT_FOUND;
import static java.util.Collections.emptyList;
import static org.springframework.data.domain.Page.empty;
import static org.springframework.data.domain.PageRequest.of;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final CarMapper mapper;

    @Override
    public List<CarResponse> listOfCars() {
        var cars = carRepository.findAll();

        if (cars.isEmpty()) {
            log.info("Empty list of cars");
            return emptyList();
        }

        log.info("Return list of cars");
        return mapper.toCarResponseList(cars);
    }

    @Override
    public List<CarResponse> findByCarBrand(String brandName) {
        var cars = carRepository.findCarsByBrand_Name(brandName);

        if (cars.isEmpty()) {
            log.info("Empty list of cars with brand name {}", brandName);
            return emptyList();
        }

        log.info("Return list of cars with brand name {}", brandName);
        return mapper.toCarResponseList(cars);
    }

    @Override
    public List<CarResponse> findByCarAvailable() {
        var cars = carRepository.findByAvailable(true);

        if (cars.isEmpty()) {
            log.info("Empty list of cars available");
            return emptyList();
        }

        log.info("Return list of cars available");
        return mapper.toCarResponseList(cars);
    }

    @Override
    public Page<CarResponse> pageCar(int page, int size) {
        var cars = carRepository.findAll(of(page, size));

        if (cars.isEmpty()) {
            log.info("Empty page of cars");
            return empty();
        }

        log.info("Return page of cars");
        return mapper.toCarResponsePage(cars, page, size);
    }

    @Override
    public CarResponse getCarById(UUID id) {
        var car = findCarOrThrow(id);

        log.info("Car id {} found successfully", id);
        return mapper.toCarResponse(car);
    }

    @Override
    public CarResponse saveCar(CarRequest request) {
        var brand = findBrandOrThrow(request.brandId());
        var category = findCategoryOrThrow(request.categoryId());

        var car = Car.builder()
                .name(request.name())
                .description(request.description())
                .dailyRate(request.dailyRate())
                .available(request.available())
                .licensePlate(request.licensePlate())
                .brand(brand)
                .category(category)
                .color(request.color())
                .build();

        log.error("Car saved successfully");
        return mapper.toCarResponse(carRepository.save(car));
    }

    @Override
    public CarResponse updateCar(UUID id, CarRequest request) {
        var car = findCarOrThrow(id);
        var brand = findBrandOrThrow(request.brandId());
        var category = findCategoryOrThrow(request.categoryId());

        updateCar(car, request, brand, category);

        log.info("Car updated successfully");
        return mapper.toCarResponse(carRepository.save(car));
    }

    private Car findCarOrThrow(UUID id) {
        var carOpt = carRepository.findById(id);

        if (carOpt.isEmpty()) {
            log.error("Car id: {} not found", id);
            throw new NotFoundException(CAR_NOT_FOUND);
        }

        return carOpt.get();
    }

    private Brand findBrandOrThrow(UUID id) {
        var brand = brandRepository.findById(id);

        if (brand.isEmpty()) {
            log.error("Brand id {} not found", id);
            throw new NotFoundException(BRAND_NOT_FOUND);
        }

        return brand.get();
    }

    private Category findCategoryOrThrow(UUID id) {
        var category = categoryRepository.findById(id);

        if (category.isEmpty()) {
            log.error("Category id {} not found", id);
            throw new NotFoundException(CATEGORY_NOT_FOUND);
        }

        return category.get();
    }

    private void updateCar(Car car, CarRequest request, Brand brand, Category category) {
        car.setName(request.name());
        car.setDescription(request.description());
        car.setDailyRate(request.dailyRate());
        car.setAvailable(request.available());
        car.setLicensePlate(car.getLicensePlate());
        car.setBrand(brand);
        car.setCategory(category);
        car.setColor(request.color());
    }
}