package com.automobilefleet.builder;

import com.automobilefleet.api.dto.response.CarImageResponse;
import com.automobilefleet.entities.CarImage;
import com.github.javafaker.Faker;
import lombok.NoArgsConstructor;

import static com.automobilefleet.builder.CarBuilder.carBuilder;
import static java.time.LocalDateTime.now;
import static java.util.UUID.randomUUID;
import static lombok.AccessLevel.PRIVATE;


@NoArgsConstructor(access = PRIVATE)
public class CarImageBuilder {

    private static final Faker fake = Faker.instance();

    public static CarImage carImageBuilder() {
        return CarImage.builder()
                .id(randomUUID())
                .car(carBuilder())
                .linkImage(fake.internet().url())
                .createdAt(now())
                .build();
    }

    public static CarImageResponse carImageResponseBuilder(CarImage carImage) {
        return new CarImageResponse(carImage.getId(), carImage.getCar().getId(), carImage.getLinkImage(), carImage.getCreatedAt());
    }
}
