package com.automobilefleet.api.controllers;

import com.automobilefleet.api.request.UserRequest;
import com.automobilefleet.api.response.UserResponse;
import com.automobilefleet.entities.User;
import com.automobilefleet.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping(value = "/register")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.saveUser(request));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.getById(id));
    }
}