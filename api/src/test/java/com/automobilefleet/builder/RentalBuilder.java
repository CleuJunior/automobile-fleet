package com.automobilefleet.builder;

import com.automobilefleet.api.dto.response.RentalResponse;
import com.automobilefleet.entities.Rental;
import com.github.javafaker.Faker;
import lombok.NoArgsConstructor;

import static com.automobilefleet.builder.CarBuilder.carBuilder;
import static com.automobilefleet.builder.CarBuilder.carResponseBuilder;
import static com.automobilefleet.builder.CustomerBuilder.customerBuilder;
import static com.automobilefleet.builder.CustomerBuilder.customerRespnseBuilder;
import static com.automobilefleet.util.DateUtils.localDateFromDate;
import static com.github.javafaker.Faker.instance;
import static java.time.LocalDateTime.now;
import static java.util.UUID.randomUUID;
import static java.util.concurrent.TimeUnit.DAYS;
import static lombok.AccessLevel.PRIVATE;


@NoArgsConstructor(access = PRIVATE)
public class RentalBuilder {

    private static final Faker fake = Faker.instance();

    public static Rental rentalBuilder() {
        var startDate = fake.date().future(3, 2, DAYS);
        var endDate = fake.date().future(5, 4, DAYS);

        return Rental.builder()
                .id(randomUUID())
                .car(carBuilder())
                .customer(customerBuilder())
                .startDate(localDateFromDate(startDate))
                .endDate(localDateFromDate(endDate))
                .total(fake.random().nextDouble())
                .createdAt(now())
                .updatedAt(now())
                .build();
    }

    public static RentalResponse rentalRespnseBuilder(Rental rental) {
        return new RentalResponse(
                rental.getId(),
                carResponseBuilder(rental.getCar()),
                customerRespnseBuilder(rental.getCustomer()),
                rental.getStartDate(),
                rental.getEndDate(),
                rental.getTotal(),
                rental.getCreatedAt(),
                rental.getUpdatedAt()
        );
    }
}
