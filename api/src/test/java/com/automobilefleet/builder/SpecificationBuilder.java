package com.automobilefleet.builder;

import com.automobilefleet.api.dto.response.SpecificationResponse;
import com.automobilefleet.entities.Specification;
import com.github.javafaker.Faker;
import lombok.NoArgsConstructor;

import static java.time.LocalDateTime.now;
import static java.util.UUID.randomUUID;
import static lombok.AccessLevel.PRIVATE;


@NoArgsConstructor(access = PRIVATE)
public class SpecificationBuilder {

    private static final Faker fake = Faker.instance();

    public static Specification specificationdBuilder() {
        return Specification.builder()
                .id(randomUUID())
                .name(fake.harryPotter().spell())
                .description(fake.harryPotter().quote())
                .createdAt(now())
                .build();
    }

    public static SpecificationResponse specificationRespnseBuilder(Specification specification) {
        return new SpecificationResponse(specification.getId(), specification.getName(), specification.getDescription(), specification.getCreatedAt());
    }
}
