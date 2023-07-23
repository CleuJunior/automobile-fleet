package com.automobilefleet.services;

import com.automobilefleet.api.request.UserRequest;
import com.automobilefleet.api.response.UserResponse;
import com.automobilefleet.entities.User;
import com.automobilefleet.repositories.UserRepository;
import com.automobilefleet.util.mapper.UserMapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public UserResponse saveUser(UserRequest request) {
        User user = UserMapperUtils.toUser(request);
        this.repository.save(user);
        return UserMapperUtils.toUserResponse(user);
    }

    public User getById(UUID id) {
        return this.repository.findById(id).orElse(null);
    }
}
