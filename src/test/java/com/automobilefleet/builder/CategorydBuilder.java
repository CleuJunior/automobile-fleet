package com.automobilefleet.builder;

import com.automobilefleet.api.dto.response.CategoryResponse;
import com.automobilefleet.entities.Category;
import com.github.javafaker.Faker;
import lombok.NoArgsConstructor;

import static java.time.LocalDateTime.now;
import static java.util.UUID.randomUUID;
import static lombok.AccessLevel.PRIVATE;


@NoArgsConstructor(access = PRIVATE)
public class CategorydBuilder {

    private static final Faker fake = new Faker();

    public static Category categorytBuilder() {
        return Category.builder()
                .id(randomUUID())
                .name(fake.leagueOfLegends().champion())
                .description(fake.leagueOfLegends().quote())
                .createdAt(now())
                .build();
    }

    public static CategoryResponse categoryResponsetBuilder(Category category) {
        return new CategoryResponse(
          category.getId(),
          category.getName(),
          category.getDescription(),
          category.getCreatedAt()
        );
    }
}
