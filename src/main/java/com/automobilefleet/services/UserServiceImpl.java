package com.automobilefleet.services;

import com.automobilefleet.Validations;
import com.automobilefleet.api.dto.request.LoginRequest;
import com.automobilefleet.api.dto.request.UserRequest;
import com.automobilefleet.api.dto.request.UserRequestUpdate;
import com.automobilefleet.api.dto.request.UserRequestUpdatePassword;
import com.automobilefleet.api.dto.response.UserResponse;
import com.automobilefleet.entities.User;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.mapper.UserMapper;
import com.automobilefleet.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final Validations validations;
    private final UserMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> listOfUser() {
        var users = repository.findAll();

        log.info("Return list of users");
        return mapper.toUserResponseList(users);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserResponse> pageUser(int page, int size) {
        var users = repository.findAll(PageRequest.of(page, size));

        log.info("Return page of users");
        return mapper.toUserResponsePage(users, page, size);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserById(UUID id) {
        log.info("Finding user id {}", id);
        return repository.findById(id)
                .map(mapper::toUserResponse)
                .orElseThrow(() -> new NotFoundException("user.id.not.found", id));
    }

    @Override
    public UserResponse getUserByUsername(String username) {
        log.info("Finding user username {}", username);
        return repository.findByUsername(username)
                .map(mapper::toUserResponse)
                .orElseThrow(() -> new NotFoundException("username.not.found", username));
    }

    @Override
    public UserResponse saveUser(UserRequest request) {
        var user = new User(request);
        user.setPassword(passwordEncoder.encode(request.password()));

        log.info("User saved successfully");
        return mapper.toUserResponse(repository.save(user));
    }

    @Override
    public UserResponse login(LoginRequest request) {
        var user = repository.findByUsername(request.username())
                .orElseThrow(() -> new NotFoundException("username.not.found", request.username()));

        validations.passwordMatchValidation(request.password(), user.getPassword());

        return mapper.toUserResponse(user);
    }

    @Override
    public UserResponse updateUser(UUID id, UserRequestUpdate request) {
        return repository.findById(id)
                .map(current -> mapper.apply(current, request))
                .map(repository::save)
                .map(mapper::toUserResponse)
                .orElseThrow(() -> new NotFoundException("user.id.not.found", id));
    }

    @Override
    public void updateUserPassword(UUID id, UserRequestUpdatePassword request) {
        validations.passwordMatchValidation(request.password(), request.confirmPassword());

        repository.findById(id)
                .ifPresentOrElse( current -> {
                    current.setPassword(passwordEncoder.encode(request.password()));
                    repository.save(current);
                    log.info("User password updated successfully");
                },
                    () -> {
                        log.error("User id: {} not found", id);
                        throw new NotFoundException("user.id.not.found", id);
                    }
                );
    }

    @Override
    public void deleteUser(UUID id) {
        repository.findById(id)
                .ifPresentOrElse(
                        current -> {
                            repository.delete(current);
                            log.info("User id {} deleted successfully", id);
                        },
                        () -> {
                            log.error("User id: {} not found", id);
                            throw new NotFoundException("user.id.not.found", id);
                        }
                );
    }
}
