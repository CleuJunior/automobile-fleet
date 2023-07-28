package com.automobilefleet.util.mapper;

import com.automobilefleet.api.request.SpecificationRequest;
import com.automobilefleet.api.response.SpecificationResponse;
import com.automobilefleet.entities.Specification;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class SpecificationMapperUtils {

    public static Specification toSpecification(SpecificationRequest req) {
        return new Specification(req.getName(), req.getDescription());
    }

    public static SpecificationResponse toSpecificationReponse(Specification specification) {
        return new SpecificationResponse(
                specification.getId(), specification.getName(), specification.getDescription(), specification.getCreatedAt()
        );
    }
}
