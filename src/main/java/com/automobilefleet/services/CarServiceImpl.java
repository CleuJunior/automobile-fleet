package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.CarRequest;
import com.automobilefleet.api.dto.response.CarResponse;
import com.automobilefleet.entities.Car;
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

import java.util.Collections;
import java.util.List;
import java.util.UUID;

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
            return Collections.emptyList();
        }

        log.info("Return list of cars");
        return mapper.toCarResponseList(cars);
    }

    @Override
    public List<CarResponse> findByCarBrand(String brandName) {
        var cars = carRepository.findCarsByBrand_Name(brandName);

        if (cars.isEmpty()) {
            log.info("Empty list of cars with brand name {}", brandName);
            return Collections.emptyList();
        }

        log.info("Return list of cars with brand name {}", brandName);
        return mapper.toCarResponseList(cars);
    }

    @Override
    public List<CarResponse> findByCarAvailable() {
        var cars = carRepository.findByAvailable(true);

        if (cars.isEmpty()) {
            log.info("Empty list of cars available");
            return Collections.emptyList();
        }

        log.info("Return list of cars available");
        return mapper.toCarResponseList(cars);
    }

    @Override
    public Page<CarResponse> pageCar(int page, int size) {
        var cars = carRepository.findAll(of(page, size));

        if (cars.isEmpty()) {
            log.info("Empty page of cars");
            return Page.empty();
        }

        log.info("Return page of cars");
        return mapper.toCarResponsePage(cars, page, size);
    }

    @Override
    public CarResponse getCarById(UUID id) {
        log.info("Finding car id {}", id);
        return carRepository.findById(id)
                .map(mapper::toCarResponse)
                .orElseThrow(() -> new NotFoundException("car.not.found", id));
    }

    @Override
    public CarResponse saveCar(CarRequest request) {
        var brand = brandRepository.findById(request.brandId())
                .orElseThrow(() -> new NotFoundException("brand.not.found", request.brandId()));

        var category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new NotFoundException("category.not.found", request.categoryId()));

        log.error("Car saved successfully");
        return mapper.toCarResponse(carRepository.save(new Car(request, brand, category)));
    }

    @Override
    public CarResponse updateCar(UUID id, CarRequest request) {
        var brand = brandRepository.findById(request.brandId())
                .orElseThrow(() -> new NotFoundException("brand.not.found", request.brandId()));

        var category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new NotFoundException("category.not.found", request.categoryId()));

        return carRepository.findById(id)
                .map(current -> mapper.apply(current, request, brand, category))
                .map(carRepository::save)
                .map(mapper::toCarResponse)
                .orElseThrow(() -> new NotFoundException("car.not.found", id));
    }
}