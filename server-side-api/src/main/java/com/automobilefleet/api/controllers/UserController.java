package com.automobilefleet.api.controllers;

import com.automobilefleet.api.request.BrandRequest;
import com.automobilefleet.api.request.UserRequest;
import com.automobilefleet.api.response.BrandResponse;
import com.automobilefleet.entities.User;
import com.automobilefleet.services.BrandService;
import com.automobilefleet.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

//    @GetMapping(value = "/{id}")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
//    public ResponseEntity<BrandResponse> getBrandById(@PathVariable Long id) {
//        return ResponseEntity.status(HttpStatus.OK).body(this.service.getBrand(id));
//    }
//
//    @GetMapping(value = "/list")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
//    public ResponseEntity<List<BrandResponse>> listOfBrand() {
//        return ResponseEntity.status(HttpStatus.OK).body(this.service.listBrand());
//    }

    @PostMapping(value = "/register")
    public ResponseEntity<User> register(@RequestBody UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.saveUser(request));
    }
//
//    @PutMapping(value = "/update/{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ResponseEntity<BrandResponse> updateBrand(@PathVariable Long id, @RequestBody BrandRequest request) {
//        BrandResponse response = this.service.updateBrand(id, request);
//
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
//    }
//
//    @DeleteMapping(value = "/delete/{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
//        this.service.deleteBrand(id);
//
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    }
}
