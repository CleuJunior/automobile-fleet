package com.automobilefleet.api.controllers;

import com.automobilefleet.api.dto.request.UserRequestUpdate;
import com.automobilefleet.api.dto.request.UserRequestUpdatePassword;
import com.automobilefleet.api.dto.response.UserResponse;
import com.automobilefleet.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable UUID id) {
        log.info("Getting user by id {}", id);
        return ResponseEntity.status(OK).body(service.getUserById(id));
    }

    @GetMapping(params = {"username"})
    public ResponseEntity<UserResponse> getUserByUsername(@RequestParam String username) {
        log.info("Getting user by username: {}", username);
        return ResponseEntity.status(OK).body(service.getUserByUsername(username));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> listOfUser() {
        log.info("Getting list of users");
        return ResponseEntity.status(OK).body(service.listOfUser());
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<Page<UserResponse>> pageUser(@RequestParam int page, @RequestParam int size) {
        log.info("Getting page of users with page {} and size {}", page, size);
        return ResponseEntity.status(OK).body(service.pageUser(page, size));
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable UUID id, @RequestBody UserRequestUpdate request) {
        log.info("Updating user id {} with request {}", id, request);
        var response = service.updateUser(id, request);

        return ResponseEntity.status(ACCEPTED).body(response);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Void> updateUserPassword(@PathVariable UUID id, @RequestBody UserRequestUpdatePassword request) {
        log.info("Updating user id password");
        service.updateUserPassword(id, request);

        return ResponseEntity.status(NO_CONTENT).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        log.info("Deleting user id {}", id);
        service.deleteUser(id);

        return ResponseEntity.status(NO_CONTENT).build();
    }
}
