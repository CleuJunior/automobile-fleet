package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.CarSpecificationRequest;
import com.automobilefleet.api.dto.response.CarSpecificationResponse;

import java.util.List;
import java.util.UUID;


public interface CarSpecificationService {

    List<CarSpecificationResponse> listCarSpecification();

    CarSpecificationResponse getCarSpecificationById(UUID id);

    CarSpecificationResponse saveCarEspecification(CarSpecificationRequest request);

    CarSpecificationResponse updateCarSpecification(UUID id, CarSpecificationRequest request);
}