package com.automobilefleet.api.controllers;

import com.automobilefleet.api.response.CarImageResponse;
import com.automobilefleet.api.request.CarImageRequest;
import com.automobilefleet.services.CarImageService;
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
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/car-images")
@RequiredArgsConstructor
public class CarImageController {

    private final CarImageService service;

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<CarImageResponse> getCarImageById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.getImageById(id));
    }

    @GetMapping(value = "/list")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<CarImageResponse>> listOfCarImages() {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.listAllImage());
    }

    @PostMapping(value = "/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CarImageResponse> saveImage(@RequestBody CarImageRequest request) {
        CarImageResponse response = this.service.saveCarImage(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(value = "/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CarImageResponse> updateCarImage(@PathVariable UUID id, @RequestBody CarImageRequest request) {
        CarImageResponse response = this.service.updateCarImage(id, request);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deletCarImageById(@PathVariable UUID id) {
        this.service.deleteCarImage(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}