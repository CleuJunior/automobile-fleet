package com.automobilefleet.builder;

import com.automobilefleet.api.dto.request.BrandRequest;
import com.automobilefleet.api.dto.response.BrandResponse;
import com.automobilefleet.entities.Brand;
import com.github.javafaker.Faker;
import lombok.NoArgsConstructor;

import static java.time.LocalDateTime.now;
import static java.util.UUID.randomUUID;
import static lombok.AccessLevel.PRIVATE;


@NoArgsConstructor(access = PRIVATE)
public class BrandBuilder {

    private static final Faker fake = Faker.instance();

    public static Brand brandBuilder() {
        return Brand.builder()
                .id(randomUUID())
                .name(fake.leagueOfLegends().champion())
                .createdAt(now())
                .updatedAt(now())
                .build();
    }

    public static BrandResponse brandRespnseBuilder(Brand brand) {
        return new BrandResponse(brand.getId(), brand.getName(), brand.getCreatedAt(), brand.getUpdatedAt());
    }

    public static BrandRequest brandRequestBuilder() {
        return new BrandRequest(fake.leagueOfLegends().champion());
    }
}
