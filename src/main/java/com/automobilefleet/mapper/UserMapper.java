package com.automobilefleet.mapper;

import com.automobilefleet.api.dto.request.UserRequest;
import com.automobilefleet.api.dto.response.UserResponse;
import com.automobilefleet.api.dto.response.UserTokenResponse;
import com.automobilefleet.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class UserMapper {

    public User toUser(UserRequest request) {
        return User.builder()
                .username(request.username())
                .email(request.email())
                .role(request.role())
                .build();
    }

    public UserResponse toUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public List<UserResponse> toUserResponseList(List<User> users) {
        return users.stream()
                .filter(Objects::nonNull)
                .map(this::toUserResponse)
                .toList();
    }
}
