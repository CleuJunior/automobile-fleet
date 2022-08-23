package com.automobilefleet.services;

import com.automobilefleet.api.mapper.CarMapper;
import com.automobilefleet.api.reponse.CarResponse;
import com.automobilefleet.api.request.CarRequest;
import com.automobilefleet.entities.Car;
import com.automobilefleet.repositories.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository repository;

    public List<CarResponse> listOfCars() {
        List<Car> cars = repository.findAll();

        return CarMapper.toCarResponseList(cars);
    }

    public CarResponse getCar(Long id) {
        Car response = repository.findById(id).get();

        return CarMapper.toCarResponse(response);
    }

    public CarResponse saveCar(CarRequest request) {
        Car carSave = CarMapper.toCar(request);
        repository.save(carSave);

        return CarMapper.toCarResponse(carSave);
    }

    public CarResponse updateCar(Long id, CarRequest request) {
        Car response = repository.findById(id).get();

        CarMapper.updateCar(response, request);

        repository.save(response);

        return CarMapper.toCarResponse(response);
    }

    public void deleteCar(Long id) {
        repository.deleteById(id);
    }
}