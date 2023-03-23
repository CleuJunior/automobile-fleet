package com.automobilefleet.api.controllers;

import com.automobilefleet.api.reponse.CarResponse;
import com.automobilefleet.api.request.CarRequest;
import com.automobilefleet.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(value = "/api/v1/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CarResponse> getBrandById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.getCar(id));
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<CarResponse>> listOfCars() {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.listOfCars());
    }

    @PostMapping(value = "/save")
    public ResponseEntity<CarResponse> saveCar(@RequestBody CarRequest request) {
        CarResponse response = this.service.saveCar(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<CarResponse> updateCar(@PathVariable Long id, @RequestBody CarRequest request) {
        CarResponse response = this.service.updateCar(id, request);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        service.deleteCar(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
