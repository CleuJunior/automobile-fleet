package com.automobilefleet.services;

import com.automobilefleet.Validations;
import com.automobilefleet.api.dto.request.BrandRequest;
import com.automobilefleet.api.dto.request.LoginRequest;
import com.automobilefleet.api.dto.request.UserRequest;
import com.automobilefleet.api.dto.request.UserRequestUpdate;
import com.automobilefleet.api.dto.response.BrandResponse;
import com.automobilefleet.api.dto.response.UserResponse;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.entities.CarImage;
import com.automobilefleet.entities.User;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.mapper.BrandMapper;
import com.automobilefleet.mapper.UserMapper;
import com.automobilefleet.repositories.BrandRepository;
import com.automobilefleet.repositories.UserRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.Optional.of;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository repository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private Validations validations;
    @Mock
    private User user;
    @Mock
    private UserResponse response;
    @Mock
    private UserRequest request;
    @Mock
    private UserMapper mapper;

    @InjectMocks
    private UserServiceImpl service;

    private static final UUID ID = UUID.randomUUID();

    @Test
    @DisplayName("Should return a list with a single user")
    void shouldReturnSingleListOfImages() {
        given(repository.findAll()).willReturn(singletonList(user));
        given(mapper.toUserResponseList(singletonList(user))).willReturn(singletonList(response));

        var actual = service.listOfUser();

        // Assertions
        then(actual).isNotEmpty();
        then(actual).contains(response);

        // Check mock interactions
        verify(repository).findAll();
        verify(mapper).toUserResponseList(singletonList(user));
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    @DisplayName("Should return a page with user")
    void shouldReturnPageUsers() {
        var pageRequest = PageRequest.of(0, 1);
        var userPage = new PageImpl<>(singletonList(user), pageRequest, 1);
        var userResponsePage = new PageImpl<>(singletonList(response), pageRequest, 1);

        given(repository.findAll(pageRequest)).willReturn(userPage);
        given(mapper.toUserResponsePage(userPage, 0, 1)).willReturn(userResponsePage);

        var actual = service.pageUser(0, 1);

        then(actual).hasSize(1);
        then(actual).contains(response);

        verify(repository).findAll(pageRequest);
        verify(mapper).toUserResponsePage(userPage, 0, 1);
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    @DisplayName("Should return a user when finding by existing id")
    void shouldReturnUserById() {
        given(repository.findById(ID)).willReturn(Optional.of(user));
        given(mapper.toUserResponse(user)).willReturn(response);

        var actual = service.getUserById(ID);

        // Assertions
        then(actual).isNotNull();
        then(actual).isEqualTo(response);

        // Check mock interactions
        verify(repository).findById(ID);
        verify(mapper).toUserResponse(user);
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    @DisplayName("Should throw error when trying to find user by non existing id")
    void shouldThrowErrorWhenReturnUserByIdNonExiting() {
        given(repository.findById(ID)).willReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.getUserById(ID));

        verify(repository).findById(ID);
        verifyNoMoreInteractions(repository);
        verifyNoInteractions(mapper);
    }

    @Test
    @DisplayName("Should find user by username")
    void shouldReturnUserByUsername() {
        var username = Faker.instance().name().username();

        given(repository.findByUsername(username)).willReturn(Optional.of(user));
        given(mapper.toUserResponse(user)).willReturn(response);

        var actual = service.getUserByUsername(username);

        // Assertions
        then(actual).isNotNull();
        then(actual).isEqualTo(response);

        // Check mock interactions
        verify(repository).findByUsername(username);
        verify(mapper).toUserResponse(user);
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    @DisplayName("Should throw error when trying to find user by non existing username")
    void shouldThrowErrorWhenReturnUserByUsernameNonExiting() {
        var username = Faker.instance().name().username();

        given(repository.findByUsername(username)).willReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.getUserByUsername(username));

        verify(repository).findByUsername(username);
        verifyNoMoreInteractions(repository);
        verifyNoInteractions(mapper);
    }

    @Test
    @DisplayName("Should save and return user")
    void shouldSaveImage() {
        given(repository.save(any(User.class))).willReturn(user);
        given(mapper.toUserResponse(user)).willReturn(response);

        var actual = service.saveUser(request);

        // Assertions
        then(actual).isNotNull();
        then(actual).isEqualTo(response);

        verify(repository).save(any(User.class));
        verify(mapper).toUserResponse(user);
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    @DisplayName("Should login with user email and password")
    void shouldLogin() {
        var username = Faker.instance().name().username();
        var password = Faker.instance().internet().password();

        given(repository.findByUsername(username)).willReturn(Optional.of(user));
        given(mapper.toUserResponse(user)).willReturn(response);

        var actual = service.login(new LoginRequest(username, password));

        // Assertions
        then(actual).isNotNull();
        then(actual).isEqualTo(response);

        verify(repository).findByUsername(username);
        verify(mapper).toUserResponse(user);
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(mapper);
    }
}