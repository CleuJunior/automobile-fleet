package com.automobilefleet.api.controllers;

import com.automobilefleet.api.response.CarResponse;
import com.automobilefleet.api.request.CarRequest;
import com.automobilefleet.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService service;

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<CarResponse> getCardById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.getCarById(id));
    }

    @GetMapping(value = "/available")
    @PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<CarResponse>> getCarAvailable() {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.findByCarAvailable());
    }


    @GetMapping(value = "/brand/{brandName}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<CarResponse>> getListCarByBrand(@PathVariable String brandName) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.findByCarBrand(brandName));
    }

    @GetMapping(value = "/list")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<CarResponse>> listOfCars() {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.listOfCars());
    }

    @PostMapping(value = "/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CarResponse> saveCar(@RequestBody CarRequest request) {
        CarResponse response = this.service.saveCar(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(value = "/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CarResponse> updateCar(@PathVariable UUID id, @RequestBody CarRequest request) {
        CarResponse response = this.service.updateCar(id, request);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

}
