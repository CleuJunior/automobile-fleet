package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.LoginRequest;
import com.automobilefleet.api.dto.request.UserRequest;
import com.automobilefleet.api.dto.response.UserResponse;
import com.automobilefleet.api.dto.response.UserTokenResponse;


public interface UserService {

    UserResponse saveUser(UserRequest request);

    UserResponse login(LoginRequest request);

}