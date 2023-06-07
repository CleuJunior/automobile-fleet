package com.automobilefleet.api.controllers;

import com.automobilefleet.api.reponse.CarSpecificationResponse;
import com.automobilefleet.services.CarSpecificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/car-specification")
@RequiredArgsConstructor
public class CarSpecificationController {
    private final CarSpecificationService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CarSpecificationResponse> getCarSpecificationId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.getCarSpecificationById(id));
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<CarSpecificationResponse>> listOfCarSpecification() {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.listCarSpecification());
    }

}
