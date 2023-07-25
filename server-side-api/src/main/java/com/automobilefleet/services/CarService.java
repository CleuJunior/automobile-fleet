package com.automobilefleet.services;

import com.automobilefleet.api.request.CarRequest;
import com.automobilefleet.api.response.CarResponse;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.Category;
import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.BrandNotFoundException;
import com.automobilefleet.exceptions.notfoundexception.CarNotFoundException;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.repositories.BrandRepository;
import com.automobilefleet.repositories.CarRepository;
import com.automobilefleet.repositories.CategoryRepository;
import com.automobilefleet.util.mapper.CarMapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    public List<CarResponse> listOfCars() {
        return this.carRepository.findAll().stream()
                .map(CarMapperUtils::toCarResponse)
                .collect(Collectors.toList());
    }

    public CarResponse getCarById(UUID id) {
        Car response = this.carRepository.findById(id).
                orElseThrow(CarNotFoundException::new);

        return CarMapperUtils.toCarResponse(response);
    }

    public List<CarResponse> findByCarBrand(String brandName) {
        return this.carRepository.findCarsByBrand_Name(brandName).stream()
                .map(CarMapperUtils::toCarResponse)
                .collect(Collectors.toList());
    }

    public List<CarResponse> findByCarAvailable() {
        return this.carRepository.findByAvailable(true).stream()
                .map(CarMapperUtils::toCarResponse)
                .collect(Collectors.toList());
    }

    public CarResponse saveCar(CarRequest request) {
        Brand brand = this.brandRepository.findById(request.getBrandId())
                .orElseThrow(BrandNotFoundException::new);

        Category category = this.categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new NotFoundException(ExceptionsConstants.CATEGORY_NOT_FOUND));

        Car response = CarMapperUtils.toCar(request, brand, category);
        return CarMapperUtils.toCarResponse(this.carRepository.save(response));
    }

    public CarResponse updateCar(UUID id, CarRequest request) {
        Car response = this.carRepository.findById(id).
                orElseThrow(CarNotFoundException::new);

        Brand brand = this.brandRepository.findById(request.getBrandId())
                .orElseThrow(BrandNotFoundException::new);

        Category category = this.categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new NotFoundException(ExceptionsConstants.CATEGORY_NOT_FOUND));

        CarMapperUtils.updateCar(response, request, brand, category);
        return CarMapperUtils.toCarResponse(this.carRepository.save(response));
    }
}