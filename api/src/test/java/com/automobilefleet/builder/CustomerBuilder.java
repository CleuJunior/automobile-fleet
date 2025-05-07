package com.automobilefleet.builder;

import com.automobilefleet.api.dto.response.CustomerResponse;
import com.automobilefleet.entities.Customer;
import com.github.javafaker.Faker;
import lombok.NoArgsConstructor;

import static com.automobilefleet.util.DateUtils.localDateFromDate;
import static java.time.LocalDateTime.now;
import static java.util.UUID.randomUUID;
import static lombok.AccessLevel.PRIVATE;


@NoArgsConstructor(access = PRIVATE)
public class CustomerBuilder {

    private static final Faker fake = Faker.instance();

    public static Customer customerBuilder() {
        return Customer.builder()
                .id(randomUUID())
                .name(fake.funnyName().name())
                .birthdate(localDateFromDate(fake.date().birthday()))
                .email(fake.internet().safeEmailAddress())
                .driverLicense(fake.random().hex())
                .address(fake.address().fullAddress())
                .phone(fake.phoneNumber().phoneNumber())
                .createdAt(now())
                .updatedAt(now())
                .build();
    }

    public static CustomerResponse customerRespnseBuilder(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getBirthdate(),
                customer.getEmail(),
                customer.getDriverLicense(),
                customer.getAddress(),
                customer.getPhone(),
                customer.getCreatedAt(),
                customer.getUpdatedAt()
        );
    }


}
