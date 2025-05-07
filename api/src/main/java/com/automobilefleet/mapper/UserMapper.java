package com.automobilefleet.mapper;

import com.automobilefleet.api.dto.request.UserRequestUpdate;
import com.automobilefleet.api.dto.response.UserResponse;
import com.automobilefleet.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static org.springframework.data.domain.PageRequest.of;

@Component
public class UserMapper {

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

    public Page<UserResponse> toUserResponsePage(Page<User> users, int page, int size) {
        var total = users.getTotalElements();
        var response = toUserResponseList(users.getContent());

        return new PageImpl<>(response, of(page, size), total);
    }

    public User apply(User current, UserRequestUpdate request) {
        current.setEmail(request.email());
        current.setUsername(request.username());
        current.setRole(request.role());

        return current;
    }
}
