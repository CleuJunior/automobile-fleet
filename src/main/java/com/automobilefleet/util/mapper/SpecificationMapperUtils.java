package com.automobilefleet.util.mapper;

import com.automobilefleet.api.dto.request.SpecificationRequest;
import com.automobilefleet.api.dto.response.SpecificationResponse;
import com.automobilefleet.entities.Specification;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class SpecificationMapperUtils {

    public static Specification toSpecification(SpecificationRequest req) {
        return Specification
                .builder()
                .name(req.getName())
                .description(req.getDescription())
                .build();
    }

    public static SpecificationResponse toSpecificationReponse(Specification specification) {
        return new SpecificationResponse(
                specification.getId(), specification.getName(), specification.getDescription(), specification.getCreatedAt()
        );
    }
}
