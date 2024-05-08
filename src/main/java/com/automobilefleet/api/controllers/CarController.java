package com.automobilefleet.api.controllers;

import com.automobilefleet.api.dto.request.CarRequest;
import com.automobilefleet.api.dto.response.BrandResponse;
import com.automobilefleet.api.dto.response.CarResponse;
import com.automobilefleet.services.CarService;
import com.automobilefleet.services.CarServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping(value = "/api/v1/cars")
@RequiredArgsConstructor
@Slf4j
public class CarController {
    private final CarService service;

    @GetMapping(value = "/{id}")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<CarResponse> getCardById(@PathVariable UUID id) {
        log.info("Getting car by id {}", id);
        return status(OK).body(service.getCarById(id));
    }

    @GetMapping(value = "/available")
//    @PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<CarResponse>> getCarAvailable() {
        log.info("Getting list of cars available");
        return status(OK).body(service.findByCarAvailable());
    }

    @GetMapping(params = {"brand_name"})
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<CarResponse>> getListCarByBrand(
            @RequestParam(name = "brand_name", required = false)
            String brandName) {

        log.info("Getting list of cars wih brand: {}", brandName);
        return status(OK).body(service.findByCarBrand(brandName));
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<Page<CarResponse>> pageBrand(@RequestParam int page, @RequestParam int size) {
        log.info("Getting page of cars with page {} and size {}", page, size);
        return status(OK).body(service.pageCar(page, size));
    }

    @GetMapping
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<CarResponse>> listOfCars() {
        log.info("Getting list of all cars");
        return status(OK).body(service.listOfCars());
    }

    @PostMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CarResponse> saveCar(@RequestBody @Valid CarRequest request) {
        log.info("Saving car {}", request);
        var response = service.saveCar(request);

        return status(CREATED).body(response);
    }

    @PutMapping(value = "/{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CarResponse> updateCar(@PathVariable UUID id, @RequestBody @Valid CarRequest request) {
        log.info("Updating car id {} with request {}", id, request);
        var response = service.updateCar(id, request);

        return status(ACCEPTED).body(response);
    }

}
