package com.automobilefleet.api.controllers;

import com.automobilefleet.api.dto.projections.CarSpecificationInfo;
import com.automobilefleet.api.dto.request.CarSpecificationRequest;
import com.automobilefleet.api.dto.response.CarSpecificationResponse;
import com.automobilefleet.repositories.CarSpecificationRepository;
import com.automobilefleet.services.CarSpecificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping(value = "/api/v1/car_specification")
@RequiredArgsConstructor
@Slf4j
public class CarSpecificationController {
    private final CarSpecificationService service;
    private final CarSpecificationRepository r;

    @GetMapping(value = "/{id}/info")
    public ResponseEntity<CarSpecificationInfo> getCarSpecificationInfoById(@PathVariable UUID id) {
        log.info("Getting car specification by id {}", id);
        return ResponseEntity.status(OK).body(r.carSpecificationInfo(id).get());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CarSpecificationResponse> getCarSpecificationId(@PathVariable UUID id) {
        log.info("Getting car specification by id {}", id);
        return status(OK).body(service.getCarSpecificationById(id));
    }

    @GetMapping
    public ResponseEntity<List<CarSpecificationResponse>> listOfCarSpecification() {
        log.info("Getting list of car specifications");
        return status(OK).body(service.listCarSpecification());
    }

    @PostMapping
    public ResponseEntity<CarSpecificationResponse> saveCarSpecification(@RequestBody CarSpecificationRequest request) {
        log.info("Saving car specification {}", request);
        var response = service.saveCarEspecification(request);

        return status(CREATED).body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CarSpecificationResponse> updateCarSpecification(@PathVariable UUID id, @RequestBody CarSpecificationRequest request) {
        log.info("Updating car specification id {} with request {}", id, request);
        var response = service.updateCarSpecification(id, request);

        return status(ACCEPTED).body(response);
    }

}
