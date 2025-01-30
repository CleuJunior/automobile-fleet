package com.automobilefleet.builder;

import com.automobilefleet.api.dto.response.CarSpecificationResponse;
import com.automobilefleet.api.dto.response.SpecificationResponse;
import com.automobilefleet.entities.CarSpecification;
import com.automobilefleet.entities.Specification;
import com.github.javafaker.Faker;
import lombok.NoArgsConstructor;

import static com.automobilefleet.builder.CarBuilder.carBuilder;
import static com.automobilefleet.builder.CarBuilder.carResponseBuilder;
import static com.automobilefleet.builder.SpecificationBuilder.specificationRespnseBuilder;
import static com.automobilefleet.builder.SpecificationBuilder.specificationdBuilder;
import static java.time.LocalDateTime.now;
import static java.util.UUID.randomUUID;
import static lombok.AccessLevel.PRIVATE;


@NoArgsConstructor(access = PRIVATE)
public class CarSpecificationBuilder {

    private static final Faker fake = Faker.instance();

    public static CarSpecification carSpecificationBuilder() {
        return CarSpecification.builder()
                .id(randomUUID())
                .car(carBuilder())
                .specification(specificationdBuilder())
                .build();
    }

    public static CarSpecificationResponse carSpecificationRespnseBuilder(CarSpecification carSpecification) {
        return new CarSpecificationResponse(carSpecification.getId(), carResponseBuilder(carSpecification.getCar()), specificationRespnseBuilder(carSpecification.getSpecification()));
    }
}
