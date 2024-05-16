package com.automobilefleet.api.controllers;

import com.automobilefleet.api.dto.request.LoginRequest;
import com.automobilefleet.api.dto.request.UserRequest;
import com.automobilefleet.api.dto.response.UserResponse;
import com.automobilefleet.api.dto.response.UserTokenResponse;
import com.automobilefleet.config.security.TokenService;
import com.automobilefleet.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService service;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<UserTokenResponse> login(@RequestBody LoginRequest request) {
        var userResponse = service.login(request);
        var token = tokenService.generateToken(userResponse);
        var header = new HttpHeaders();

        header.setBearerAuth(token);

        var response = new UserTokenResponse(userResponse.username(), token);
        return status(OK).headers(header).body(response);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid UserRequest request) {
        var response = service.saveUser(request);
        var token = tokenService.generateToken(response);

        var header = new HttpHeaders();
        header.setBearerAuth(token);

        return status(CREATED).headers(header).body(response);
    }
}
