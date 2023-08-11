package com.automobilefleet.mapper;

import com.automobilefleet.api.dto.response.SpecificationResponse;
import com.automobilefleet.entities.Specification;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class SpecificationMapper implements Function<Specification, SpecificationResponse> {

    @Override
    public SpecificationResponse apply(Specification specification) {
        return new SpecificationResponse(
                specification.getId(),
                specification.getName(),
                specification.getDescription()
        );
    }
}