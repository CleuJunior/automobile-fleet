package com.automobilefleet.services;

import com.automobilefleet.api.reponse.CarSpecificationResponse;
import com.automobilefleet.entities.CarSpecification;
import com.automobilefleet.exceptions.CarSpecificationsNotFoundException;
import com.automobilefleet.repositories.CarSpecificationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarSpecificationService {

    private final CarSpecificationRepository carSpecificationRepository;
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
}
