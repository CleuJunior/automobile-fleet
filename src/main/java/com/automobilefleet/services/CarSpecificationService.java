package com.automobilefleet.services;

import com.automobilefleet.api.request.CarSpecificationRequest;
import com.automobilefleet.api.response.CarSpecificationResponse;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.CarSpecification;
import com.automobilefleet.entities.Specification;
import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.CarNotFoundException;
import com.automobilefleet.exceptions.notfoundexception.CarSpecificationsNotFoundException;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.exceptions.notfoundexception.SpecificationNotFoundException;
import com.automobilefleet.repositories.CarRepository;
import com.automobilefleet.repositories.CarSpecificationRepository;
import com.automobilefleet.repositories.SpecificationRepository;
import com.automobilefleet.util.mapper.CarSpecificationServiceMapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CarSpecificationService {
    private final CarSpecificationRepository carSpecificationRepository;
    private final CarRepository carRepository;
    private final SpecificationRepository specificationRepository;

    public List<CarSpecificationResponse> listCarSpecification() {
         return this.carSpecificationRepository.findAll().stream()
                .map(CarSpecificationServiceMapperUtils::toCarSpecificationResponse)
                .collect(Collectors.toList());
    }

    public CarSpecificationResponse getCarSpecificationById(UUID id) {
        CarSpecification response = this.carSpecificationRepository.findById(id)
                .orElseThrow(CarSpecificationsNotFoundException::new);

        return CarSpecificationServiceMapperUtils.toCarSpecificationResponse(response);
    }

    public CarSpecificationResponse saveCarEspecification(CarSpecificationRequest request) {
        Car car = this.carRepository.findById(request.getCarId()).
                orElseThrow(CarNotFoundException::new);

        Specification specification = this.specificationRepository.findById(request.getSpecificationId()).
                orElseThrow(SpecificationNotFoundException::new);

        CarSpecification response = new CarSpecification(car, specification);

        return CarSpecificationServiceMapperUtils
                .toCarSpecificationResponse(this.carSpecificationRepository.save(response));
    }

    public CarSpecificationResponse updateCarSpecification(UUID id, CarSpecificationRequest request) {
        CarSpecification response = this.carSpecificationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionsConstants.CATEGORY_NOT_FOUND));

        Car car = this.carRepository.findById(request.getCarId()).
                orElseThrow(CarNotFoundException::new);

        Specification specification = this.specificationRepository.findById(request.getSpecificationId()).
                orElseThrow(SpecificationNotFoundException::new);

        response.setCar(car);
        response.setSpecification(specification);
        return CarSpecificationServiceMapperUtils
                .toCarSpecificationResponse(this.carSpecificationRepository.save(response));
    }
}