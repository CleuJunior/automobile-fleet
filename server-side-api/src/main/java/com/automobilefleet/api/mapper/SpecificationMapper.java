package com.automobilefleet.api.mapper;

import com.automobilefleet.api.reponse.SpecificationResponse;
import com.automobilefleet.api.request.SpecificationRequest;
import com.automobilefleet.entities.Specification;

import java.util.ArrayList;
import java.util.List;


public class SpecificationMapper {

    private SpecificationMapper() { }

    public static Specification toSpecification(SpecificationRequest request) {
        Specification specification = new Specification();
        specification.setName(request.getName());
        specification.setDescription(request.getDescription());

        return specification;
    }

    public static SpecificationResponse toSpecificationResponse(Specification specification) {
        SpecificationResponse response = new SpecificationResponse();
        response.setId(specification.getId());
        response.setName(specification.getName());
        response.setDescription(specification.getDescription());
        response.setCreatedAt(specification.getCreatedAt());

        return response;
    }

    public static List<SpecificationResponse> toSpecificationResponseList(List<Specification> specifications) {
        List<SpecificationResponse> response = new ArrayList<>();
        specifications.forEach(category -> response.add(toSpecificationResponse(category)));

        return response;
    }

    public static void updateSpecification(Specification category, SpecificationRequest request) {
        category.setName(request.getName());
        category.setDescription(request.getDescription());
    }
}
