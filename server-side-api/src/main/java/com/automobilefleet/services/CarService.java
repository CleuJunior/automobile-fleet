package com.automobilefleet.services;

import com.automobilefleet.api.reponse.CarResponse;
import com.automobilefleet.api.request.CarRequest;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.Category;
import com.automobilefleet.exceptions.BrandNotFoundException;
import com.automobilefleet.exceptions.CarNotFoundException;
import com.automobilefleet.exceptions.CategoryNotFoundException;
import com.automobilefleet.repositories.BrandRepository;
import com.automobilefleet.repositories.CarRepository;
import com.automobilefleet.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    public List<CarResponse> listOfCars() {
        return this.carRepository.findAll().stream()
                .map(car -> this.mapper.map(car, CarResponse.class))
                .collect(Collectors.toList());
    }

    public CarResponse getCarById(Long id) {
        Car response = this.carRepository.findById(id).
                orElseThrow(CarNotFoundException::new);

        return this.mapper.map(response, CarResponse.class);
    }

    public List<CarResponse> findByCarBrand(String brandName) {
        return this.carRepository.findCarsByBrand_Name(brandName).stream()
                .map(car -> this.mapper.map(car, CarResponse.class))
                .collect(Collectors.toList());
    }

    public List<CarResponse> findByCarAvailable() {
        return this.carRepository.findByAvailable(true).stream()
                .map(car -> this.mapper.map(car, CarResponse.class))
                .collect(Collectors.toList());
    }

    public CarResponse saveCar(CarRequest request) {
        Brand brand = this.brandRepository.findById(request.getBrandId())
                .orElseThrow(BrandNotFoundException::new);

        Category category = this.categoryRepository.findById(request.getCategoryId())
                .orElseThrow(CategoryNotFoundException::new);

        Car response = new Car(
                request.getName(),
                request.getDescription(),
                request.getDailyRate(),
                request.getAvailable(),
                request.getLicensePlate(),
                brand,
                category,
                request.getColor()
        );

        response = this.carRepository.save(response);

        return this.mapper.map(response, CarResponse.class);
    }

    public CarResponse updateCar(Long id, CarRequest request) {
        Car response = this.carRepository.findById(id).
                orElseThrow(CarNotFoundException::new);

        Brand brand = this.brandRepository.findById(request.getBrandId())
                .orElseThrow(BrandNotFoundException::new);

        Category category = this.categoryRepository.findById(request.getCategoryId())
                .orElseThrow(CategoryNotFoundException::new);

        response.setName(request.getName());
        response.setDescription(request.getDescription());
        response.setDailyRate(request.getDailyRate());
        response.setAvailable(request.getAvailable());
        response.setLicensePlate(response.getLicensePlate());
        response.setBrand(brand);
        response.setCategory(category);
        response.setColor(response.getColor());
        response = this.carRepository.save(response);

        return this.mapper.map(response, CarResponse.class);
    }

    public void deleteCar(Long id) {
        Car response = this.carRepository.findById(id).
                orElseThrow(CarNotFoundException::new);

        this.carRepository.delete(response);
    }
}