package com.automobilefleet.builder;

import com.automobilefleet.api.dto.response.CategoryResponse;
import com.automobilefleet.entities.Category;
import com.github.javafaker.Faker;
import lombok.NoArgsConstructor;

import static com.github.javafaker.Faker.instance;
import static java.time.LocalDateTime.now;
import static java.util.UUID.randomUUID;
import static lombok.AccessLevel.PRIVATE;


@NoArgsConstructor(access = PRIVATE)
public class CategoryBuilder {

    private static final Faker fake = instance();

    public static Category categoryBuilder() {
        return Category.builder()
                .id(randomUUID())
                .name(fake.name().nameWithMiddle())
                .description(fake.lorem().sentence())
                .createdAt(now())
                .build();
    }

    public static CategoryResponse categoryResponseBuilder(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getCreatedAt()
        );
    }
}
