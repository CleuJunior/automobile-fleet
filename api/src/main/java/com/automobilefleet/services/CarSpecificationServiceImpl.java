package com.automobilefleet.services;

import com.automobilefleet.api.dto.projections.CarSpecificationInfo;
import com.automobilefleet.api.dto.request.CarSpecificationRequest;
import com.automobilefleet.api.dto.response.CarSpecificationResponse;
import com.automobilefleet.entities.CarSpecification;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.mapper.CarSpecificationMapper;
import com.automobilefleet.repositories.CarRepository;
import com.automobilefleet.repositories.CarSpecificationRepository;
import com.automobilefleet.repositories.SpecificationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CarSpecificationServiceImpl implements CarSpecificationService {
    private final CarSpecificationRepository carSpecificationRepository;
    private final CarRepository carRepository;
    private final SpecificationRepository specificationRepository;
    private final CarSpecificationMapper mapper;

    @Override
    public List<CarSpecificationResponse> listCarSpecification() {
        var carSpecifications = carSpecificationRepository.findAll();

        if (carSpecifications.isEmpty()) {
            log.info("Empty list of car specifications");
            return Collections.emptyList();
        }

        log.info("Return list of car specifications");
        return mapper.toCarSpecificationResponseList(carSpecifications);
    }

    @Override
    public CarSpecificationResponse getCarSpecificationById(UUID id) {
        log.info("Car specification id {}", id);
        return carSpecificationRepository.findById(id)
                .map(mapper::toCarSpecificationResponse)
                .orElseThrow(() -> new NotFoundException("car.specification.not.found", id));
    }

    @Override
    public CarSpecificationInfo getCarSpecification(UUID id) {
        log.info("Car specification id {}", id);
        return carSpecificationRepository.carSpecificationInfo(id)
                .orElseThrow(() -> new NotFoundException("car.specification.not.found", id));
    }

    @Override
    public CarSpecificationResponse saveCarSpecification(CarSpecificationRequest request) {
        var car = carRepository.findById(request.carId())
                .orElseThrow(() -> new NotFoundException("car.not.found", request.carId()));

        var specification = specificationRepository.findById(request.specificationId())
                .orElseThrow(() -> new NotFoundException("specification.not.found", request.specificationId()));

        var carSpecification = CarSpecification.builder()
                .car(car)
                .specification(specification)
                .build();

        log.info("Car specification saved successfully");
        return mapper.toCarSpecificationResponse(carSpecificationRepository.save(carSpecification));
    }

    @Override
    public CarSpecificationResponse updateCarSpecification(UUID id, CarSpecificationRequest request) {
        var car = carRepository.findById(request.carId())
                .orElseThrow(() -> new NotFoundException("car.not.found", request.carId()));

        var specification = specificationRepository.findById(request.specificationId())
                .orElseThrow(() -> new NotFoundException("specification.not.found", request.specificationId()));

        log.info("Trying to find specification id: {}", id);
        return carSpecificationRepository.findById(id)
                .map(current -> mapper.apply(current, car, specification))
                .map(carSpecificationRepository::save)
                .map(mapper::toCarSpecificationResponse)
                .orElseThrow(() -> new NotFoundException("car.specification.not.found", id));
    }
}