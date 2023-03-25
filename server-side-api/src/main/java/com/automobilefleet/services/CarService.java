package com.automobilefleet.services;

import com.automobilefleet.api.reponse.CarResponse;
import com.automobilefleet.api.request.CarRequest;
import com.automobilefleet.entities.Car;
import com.automobilefleet.exceptions.CarNotFoundException;
import com.automobilefleet.repositories.CarRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository repository;
    private final ModelMapper mapper;

    public List<CarResponse> listOfCars() {
        return this.repository.findAll().stream()
                .map(car -> this.mapper.map(car, CarResponse.class))
                .collect(Collectors.toList());
    }

    public CarResponse getCar(Long id) {
        Car response = this.repository.findById(id).
                orElseThrow(CarNotFoundException::new);

        System.out.println(this.repository.findBrandNameByCarId(id));

        return this.mapper.map(response, CarResponse.class);
    }

    public List<CarResponse> findByCarBrand(String brandName) {
        return this.repository.findCarsByBrand_Name(brandName).stream()
                .map(car -> this.mapper.map(car, CarResponse.class))
                .collect(Collectors.toList());
    }


    public List<CarResponse> findByCarAvailable() {
        return this.repository.findByAvailable(true).stream()
                .map(car -> this.mapper.map(car, CarResponse.class))
                .collect(Collectors.toList());
    }

    public CarResponse saveCar(CarRequest request) {
        Car response = this.mapper.map(request, Car.class);
        response = this.repository.save(response);

        return this.mapper.map(response, CarResponse.class);
    }

    public CarResponse updateCar(Long id, CarRequest request) {
        Car response = this.repository.findById(id).
                orElseThrow(CarNotFoundException::new);

        response.setName(request.getName());
        response.setDescription(request.getDescription());
        response.setDailyRate(request.getDailyRate());
        response.setAvailable(request.getAvailable());
        response.setLicensePlate(response.getLicensePlate());
        response.setBrand(request.getBrand());
        response.setCategory(request.getCategory());
        response.setColor(response.getColor());
        response = this.repository.save(response);

        return this.mapper.map(response, CarResponse.class);
    }

    public void deleteCar(Long id) {
        Car response = this.repository.findById(id).
                orElseThrow(CarNotFoundException::new);

        this.repository.delete(response);
    }
}