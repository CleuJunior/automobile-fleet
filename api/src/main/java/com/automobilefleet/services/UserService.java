package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.LoginRequest;
import com.automobilefleet.api.dto.request.UserRequest;
import com.automobilefleet.api.dto.request.UserRequestUpdate;
import com.automobilefleet.api.dto.request.UserRequestUpdatePassword;
import com.automobilefleet.api.dto.response.UserResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;


public interface UserService {

    List<UserResponse> listOfUser();

    Page<UserResponse> pageUser(int page, int size);

    UserResponse getUserById(UUID id);

    UserResponse getUserByUsername(String username);

    UserResponse saveUser(UserRequest request);

    UserResponse login(LoginRequest request);

    UserResponse updateUser(UUID id, UserRequestUpdate request);

    void updateUserPassword(UUID id, UserRequestUpdatePassword request);

    void deleteUser(UUID id);

}