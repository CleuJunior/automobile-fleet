package com.automobilefleet.builder;

import com.automobilefleet.api.dto.request.UserRequest;
import com.automobilefleet.api.dto.response.UserResponse;
import com.automobilefleet.entities.User;
import com.github.javafaker.Faker;
import lombok.NoArgsConstructor;

import static com.automobilefleet.entities.Role.ADMIN;
import static java.time.LocalDateTime.now;
import static java.util.UUID.randomUUID;
import static lombok.AccessLevel.PRIVATE;


@NoArgsConstructor(access = PRIVATE)
public class UserBuilder {

    private static final Faker fake = Faker.instance();

    public static User userBuilder() {
        return User.builder()
                .id(randomUUID())
                .email(fake.internet().emailAddress())
                .username(fake.internet().userAgentAny())
                .password(fake.internet().password())
                .role(ADMIN)
                .createdAt(now().minusMonths(3))
                .updatedAt(now())
                .build();
    }

    public static UserResponse userResponseBuilder(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public static UserRequest userRequestBuilder(User user) {
        return new UserRequest(
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                user.getRole()
        );
    }
}
