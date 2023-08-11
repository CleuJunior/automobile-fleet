package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.CarSpecificationRequest;
import com.automobilefleet.api.dto.response.CarSpecificationResponse;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.CarSpecification;
import com.automobilefleet.entities.Specification;
import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.CarNotFoundException;
import com.automobilefleet.exceptions.notfoundexception.CarSpecificationsNotFoundException;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.mapper.CarSpecificationMapper;
import com.automobilefleet.repositories.CarRepository;
import com.automobilefleet.repositories.CarSpecificationRepository;
import com.automobilefleet.repositories.SpecificationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CarSpecificationService {
    private final CarSpecificationRepository carSpecificationRepository;
    private final CarRepository carRepository;
    private final SpecificationRepository specificationRepository;

    public List<CarSpecificationResponse> listCarSpecification() {
         return this.carSpecificationRepository.findAll()
                 .stream()
                 .filter(Objects::nonNull)
                 .map(CarSpecificationResponse::new)
                 .toList();
    }

    public CarSpecificationResponse getCarSpecificationById(UUID id) {
        Optional<CarSpecification> response = this.carSpecificationRepository.findById(id);

        if (response.isEmpty()) {
            throw new NotFoundException(ExceptionsConstants.CAR_SPECIFICATION_NOT_FOUND);
        }

        return new CarSpecificationResponse(response.get());
    }

    public CarSpecificationResponse saveCarEspecification(CarSpecificationRequest request) {
        Optional<Car> car = this.carRepository.findById(request.carId());

        if (car.isEmpty()) {
            throw new NotFoundException(ExceptionsConstants.CAR_NOT_FOUND);
        }

        Optional<Specification> specification = this.specificationRepository.findById(request.specificationId());

        if (specification.isEmpty())
            throw new NotFoundException(ExceptionsConstants.SPECIFICATION_NOT_FOUND);

        CarSpecification response = CarSpecification.builder()
                .car(car.get())
                .specification(specification.get())
                .build();

        return new CarSpecificationResponse(this.carSpecificationRepository.save(response));
    }

    public CarSpecificationResponse updateCarSpecification(UUID id, CarSpecificationRequest request) {
        Optional<CarSpecification> response = this.carSpecificationRepository.findById(id);

        if (response.isEmpty()) {
            throw new NotFoundException(ExceptionsConstants.CAR_SPECIFICATION_NOT_FOUND);
        }

        Optional<Car> car = this.carRepository.findById(request.carId());

        if (car.isEmpty()) {
            throw new CarNotFoundException();
        }

        Optional<Specification> specification = this.specificationRepository.findById(request.specificationId());

        if (specification.isEmpty()) {
            throw new NotFoundException(ExceptionsConstants.SPECIFICATION_NOT_FOUND);
        }

        response.get().setCar(car.get());
        response.get().setSpecification(specification.get());
        return new CarSpecificationResponse(this.carSpecificationRepository.save(response.get()));
    }
}