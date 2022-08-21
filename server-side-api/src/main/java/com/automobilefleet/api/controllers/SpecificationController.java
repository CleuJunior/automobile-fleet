package com.automobilefleet.api.controllers;

import com.automobilefleet.api.reponse.SpecificationResponse;
import com.automobilefleet.api.request.SpecificationRequest;
import com.automobilefleet.services.SpecificationService;
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
@RequestMapping(value = "/api/v1/specification")
@RequiredArgsConstructor
public class SpecificationController {
    private final SpecificationService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<SpecificationResponse> getSpecificationById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getSpecification(id));
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<SpecificationResponse>> listOfSpecifications() {
        return ResponseEntity.status(HttpStatus.OK).body(service.listSpecifications());
    }

    @PostMapping(value = "/save")
    public ResponseEntity<SpecificationResponse> saveSpecification(@RequestBody SpecificationRequest request) {
        SpecificationResponse response = service.saveSpecification(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<SpecificationResponse> updateSpecification(@PathVariable Long id, @RequestBody SpecificationRequest request) {
        SpecificationResponse response = service.updateSpecification(id, request);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<SpecificationResponse> deleteSpecification(@PathVariable Long id) {
        service.deleteSpecification(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
