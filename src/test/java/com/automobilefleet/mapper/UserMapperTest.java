package com.automobilefleet.mapper;

import com.automobilefleet.api.dto.response.UserResponse;
import com.automobilefleet.builder.UserBuilder;
import com.automobilefleet.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(SpringExtension.class)
class UserMapperTest {

    private User user;
    private UserResponse response;

    @InjectMocks
    private UserMapper mapper;

    @BeforeEach
    void setUp() {
        user = UserBuilder.userBuilder();
        response = UserBuilder.userResponseBuilder(user);
    }

    @Test
    void shouldMapperUserToUserResponse() {
        var result = mapper.toUserResponse(user);

        then(result).isEqualTo(response);
    }

    @Test
    void shouldMapUserListToUserResponseList() {
        var result = mapper.toUserResponseList(singletonList(user));

        then(result).isNotEmpty();
        then(result).contains(response);
    }

}