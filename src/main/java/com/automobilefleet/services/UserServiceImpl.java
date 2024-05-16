package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.LoginRequest;
import com.automobilefleet.api.dto.request.UserRequest;
import com.automobilefleet.api.dto.response.UserResponse;
import com.automobilefleet.mapper.UserMapper;
import com.automobilefleet.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;

    @Override
    public UserResponse saveUser(UserRequest request) {
        var user = mapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.password()));

        log.info("User saved successfully");
        return mapper.toUserResponse(repository.save(user));
    }

    @Override
    public UserResponse login(LoginRequest request) {
        var user = repository.findByUsername(request.username()).orElseThrow(() -> new RuntimeException(format("Username %s not found!", request.username())));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Password does not match");
        }

        return mapper.toUserResponse(user);
    }
}
