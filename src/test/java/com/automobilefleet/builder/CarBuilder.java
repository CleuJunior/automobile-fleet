package com.automobilefleet.builder;

import com.automobilefleet.api.dto.response.CarResponse;
import com.automobilefleet.entities.Car;
import com.github.javafaker.Faker;
import lombok.NoArgsConstructor;

import static com.automobilefleet.builder.BrandBuilder.brandBuilder;
import static com.automobilefleet.builder.BrandBuilder.brandRespnseBuilder;
import static com.automobilefleet.builder.CategoryBuilder.categoryBuilder;
import static com.automobilefleet.builder.CategoryBuilder.categoryResponseBuilder;
import static java.time.LocalDateTime.now;
import static java.util.UUID.randomUUID;
import static lombok.AccessLevel.PRIVATE;


@NoArgsConstructor(access = PRIVATE)
public class CarBuilder {

    private static final Faker fake = new Faker();

    public static Car carBuilder() {
        return Car.builder()
                .id(randomUUID())
                .name(fake.leagueOfLegends().champion())
                .dailyRate(fake.random().nextDouble())
                .description(fake.leagueOfLegends().quote())
                .available(fake.random().nextBoolean())
                .licensePlate(fake.random().hex())
                .brand(brandBuilder())
                .category(categoryBuilder())
                .color(fake.color().name())
                .createdAt(now())
                .build();
    }

    public static CarResponse carResponseBuilder(Car car) {
        return new CarResponse(
                car.getId(),
                car.getName(),
                car.getDescription(),
                car.getDailyRate(),
                car.isAvailable(),
                car.getLicensePlate(),
                brandRespnseBuilder(car.getBrand()),
                categoryResponseBuilder(car.getCategory()),
                car.getColor(),
                car.getCreatedAt()
        );
    }
}
