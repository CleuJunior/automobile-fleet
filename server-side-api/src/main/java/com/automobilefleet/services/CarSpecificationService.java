package com.automobilefleet.services;

import com.automobilefleet.api.response.CarSpecificationResponse;
import com.automobilefleet.api.request.CarSpecificationRequest;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.CarSpecification;
import com.automobilefleet.entities.Specification;
import com.automobilefleet.exceptions.CarNotFoundException;
import com.automobilefleet.exceptions.CarSpecificationsNotFoundException;
import com.automobilefleet.exceptions.CategoryNotFoundException;
import com.automobilefleet.exceptions.SpecificationNotFoundException;
import com.automobilefleet.repositories.CarRepository;
import com.automobilefleet.repositories.CarSpecificationRepository;
import com.automobilefleet.repositories.SpecificationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CarSpecificationService {

    private final CarSpecificationRepository carSpecificationRepository;
    private final CarRepository carRepository;
    private final SpecificationRepository specificationRepository;
    private final ModelMapper mapper;

    public List<CarSpecificationResponse> listCarSpecification() {
         return this.carSpecificationRepository.findAll().stream()
                .map(carSpecification -> this.mapper.map(carSpecification, CarSpecificationResponse.class))
                .collect(Collectors.toList());

    }

    public CarSpecificationResponse getCarSpecificationById(Long id) {
        CarSpecification response = this.carSpecificationRepository.findById(id)
                .orElseThrow(CarSpecificationsNotFoundException::new);

        return this.mapper.map(response, CarSpecificationResponse.class);
    }

    public CarSpecificationResponse saveCarEspecification(CarSpecificationRequest request) {
        Car car = this.carRepository.findById(request.getCarId()).
                orElseThrow(CarNotFoundException::new);

        Specification specification = this.specificationRepository.findById(request.getSpecificationId()).
                orElseThrow(SpecificationNotFoundException::new);

        CarSpecification response = new CarSpecification(car, specification);
        response = this.carSpecificationRepository.save(response);

        return this.mapper.map(response, CarSpecificationResponse.class);
    }

    public CarSpecificationResponse updateCarSpecification(Long id, CarSpecificationRequest request) {
        CarSpecification response = this.carSpecificationRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        Car car = this.carRepository.findById(request.getCarId()).
                orElseThrow(CarNotFoundException::new);

        Specification specification = this.specificationRepository.findById(request.getSpecificationId()).
                orElseThrow(SpecificationNotFoundException::new);

        response.setCar(car);
        response.setSpecification(specification);
        response = this.carSpecificationRepository.save(response);

        return this.mapper.map(response, CarSpecificationResponse.class);
    }
}
