package com.automobilefleet.mapper;

import com.automobilefleet.api.dto.response.CarSpecificationResponse;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.CarSpecification;
import com.automobilefleet.entities.Specification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class CarSpecificationMapper {

    private final CarMapper carMapper;
    private final SpecificationMapper specificationMapper;

    public CarSpecificationResponse toCarSpecificationResponse(CarSpecification carSpecification) {
        var car = carMapper.toCarResponse(carSpecification.getCar());
        var specification = specificationMapper.toSpecificationResponse(carSpecification.getSpecification());

        return new CarSpecificationResponse(carSpecification.getId(), car, specification);
    }

    public List<CarSpecificationResponse> toCarSpecificationResponseList(List<CarSpecification> carSpecification) {
        return carSpecification.stream()
                .filter(Objects::nonNull)
                .map(this::toCarSpecificationResponse)
                .toList();
    }

    public CarSpecification apply(CarSpecification current, Car car, Specification specification) {
        current.setCar(car);
        current.setSpecification(specification);

        return current;
    }
}
