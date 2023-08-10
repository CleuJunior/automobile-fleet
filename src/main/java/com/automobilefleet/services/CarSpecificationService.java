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
    private final CarSpecificationMapper mapper;

    public List<CarSpecificationResponse> listCarSpecification() {
         return this.carSpecificationRepository.findAll()
                 .stream()
                 .filter(Objects::nonNull)
                 .map(this.mapper)
                 .toList();
    }

    public CarSpecificationResponse getCarSpecificationById(UUID id) {
        CarSpecification response = this.carSpecificationRepository.findById(id)
                .orElseThrow(CarSpecificationsNotFoundException::new);

        return this.mapper.apply(response);
    }

    public CarSpecificationResponse saveCarEspecification(CarSpecificationRequest request) {
        Car car = this.carRepository.findById(request.carId()).
                orElseThrow(CarNotFoundException::new);

        Optional<Specification> specification = this.specificationRepository.findById(request.specificationId());

        if (specification.isEmpty())
            throw new NotFoundException(ExceptionsConstants.SPECIFICATION_NOT_FOUND);

        CarSpecification response = CarSpecification.builder()
                .car(car)
                .specification(specification.get())
                .build();

        return this.mapper.apply(this.carSpecificationRepository.save(response));
    }

    public CarSpecificationResponse updateCarSpecification(UUID id, CarSpecificationRequest request) {
        CarSpecification response = this.carSpecificationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionsConstants.CATEGORY_NOT_FOUND));

        Car car = this.carRepository.findById(request.carId()).
                orElseThrow(CarNotFoundException::new);

        Optional<Specification> specification = this.specificationRepository.findById(request.specificationId());

        if (specification.isEmpty())
            throw new NotFoundException(ExceptionsConstants.SPECIFICATION_NOT_FOUND);

        response.setCar(car);
        response.setSpecification(specification.get());
        return this.mapper.apply(this.carSpecificationRepository.save(response));
    }
}