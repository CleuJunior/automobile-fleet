package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.CarSpecificationRequest;
import com.automobilefleet.api.dto.response.CarSpecificationResponse;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.CarSpecification;
import com.automobilefleet.entities.Specification;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.mapper.CarSpecificationMapper;
import com.automobilefleet.repositories.CarRepository;
import com.automobilefleet.repositories.CarSpecificationRepository;
import com.automobilefleet.repositories.SpecificationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.Collections.emptyList;

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
            return emptyList();
        }

        log.info("Return list of car specifications");
        return mapper.toCarSpecificationResponseList(carSpecifications);
    }

    @Override
    public CarSpecificationResponse getCarSpecificationById(UUID id) {
        var carSpecification = findCarSpecificationOrThrow(id);

        log.info("Car specification id {} found successfully", id);
        return mapper.toCarSpecificationResponse(carSpecification);
    }

    @Override
    public CarSpecificationResponse saveCarEspecification(CarSpecificationRequest request) {
        var car = findCarOrThrow(request.carId());
        var specification = findSpecificationOrThrow(request.specificationId());

        var carSpecification = CarSpecification.builder()
                .car(car)
                .specification(specification)
                .build();

        log.info("Car especification saved successfully");
        return mapper.toCarSpecificationResponse(carSpecificationRepository.save(carSpecification));
    }

    @Override
    public CarSpecificationResponse updateCarSpecification(UUID id, CarSpecificationRequest request) {
        var carSpecification = findCarSpecificationOrThrow(id);
        var car = findCarOrThrow(request.carId());
        var specification = findSpecificationOrThrow(request.specificationId());

        carSpecification.setCar(car);
        carSpecification.setSpecification(specification);

        log.info("Car specification {} updated successfully", id);
        return mapper.toCarSpecificationResponse(carSpecificationRepository.save(carSpecification));
    }

    private CarSpecification findCarSpecificationOrThrow(UUID id) {
        var carSpecification = carSpecificationRepository.findById(id);

        if (carSpecification.isEmpty()) {
            log.error("Car specification id {} not found", id);
            throw new NotFoundException("car.specification.not.found", id);
        }

        return carSpecification.get();
    }

    private Car findCarOrThrow(UUID id) {
        var car = carRepository.findById(id);

        if (car.isEmpty()) {
            log.error("Car id {} not found", id);
            throw new NotFoundException("car.not.found", id);
        }

        return car.get();
    }

    private Specification findSpecificationOrThrow(UUID id) {
        var specification = specificationRepository.findById(id);

        if (specification.isEmpty()) {
            log.error("Specification id {} not found", id);
            throw new NotFoundException("specification.not.found", id);
        }

        return specification.get();
    }
}