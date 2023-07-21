package com.automobilefleet.api.controllers;

import com.automobilefleet.api.response.SpecificationResponse;
import com.automobilefleet.api.request.SpecificationRequest;
import com.automobilefleet.services.SpecificationService;
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

@RestController
@RequestMapping(value = "/api/v1/specification")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequiredArgsConstructor
public class SpecificationController {
    private final SpecificationService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<SpecificationResponse> getSpecificationById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.getSpecification(id));
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<SpecificationResponse>> listOfSpecifications() {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.listSpecifications());
    }

    @PostMapping(value = "/save")
    public ResponseEntity<SpecificationResponse> saveSpecification(@RequestBody SpecificationRequest request) {
        SpecificationResponse response = this.service.saveSpecification(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<SpecificationResponse> updateSpecification(@PathVariable Long id, @RequestBody SpecificationRequest request) {
        SpecificationResponse response = this.service.updateSpecification(id, request);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

}
