package com.automobilefleet.api.controllers;

import com.automobilefleet.api.dto.request.RentalRequest;
import com.automobilefleet.api.dto.response.RentalResponse;
import com.automobilefleet.services.RentalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import java.util.UUID;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping(value = "/api/v1/rental")
@RequiredArgsConstructor
@Slf4j
public class RentalController {
    private final RentalService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<RentalResponse> getRentalById(@PathVariable UUID id) {
        log.info("Getting rental by id {}", id);
        return status(OK).body(service.getRentalById(id));
    }

    @GetMapping
    public ResponseEntity<List<RentalResponse>> listOfRental() {
        log.info("Getting list of rentals");
        return status(OK).body(service.listOfRental());
    }

    @PostMapping
    public ResponseEntity<RentalResponse> saveRental(@RequestBody RentalRequest request) {
        log.info("Saving rental {}", request);
        var response = service.saveRental(request);

        return status(CREATED).body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RentalResponse> updateRental(@PathVariable UUID id, @RequestBody RentalRequest request) {
        log.info("Updating rental id {} with request {}", id, request);
        var response = service.updateRental(id, request);

        return status(ACCEPTED).body(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteRental(@PathVariable UUID id) {
        log.info("Deleting rental id {}", id);
        service.deleteRental(id);

        return status(NO_CONTENT).build();
    }
}