package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.CarRequest;
import com.automobilefleet.api.dto.response.CarResponse;
import com.automobilefleet.api.dto.projections.CarInfo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface CarService {

    List<CarResponse> listOfCars();

    List<CarResponse> findByCarBrand(String brandName);

    List<CarResponse> findByCarAvailable();

    Page<CarResponse> pageCar(int page, int size);

    CarResponse getCarById(UUID id);

    CarInfo getCarInfoById(UUID id);

    CarResponse saveCar(CarRequest request);

    CarResponse updateCar(UUID id, CarRequest request);

}