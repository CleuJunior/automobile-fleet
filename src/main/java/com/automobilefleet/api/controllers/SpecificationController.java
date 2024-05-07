package com.automobilefleet.api.controllers;

import com.automobilefleet.api.dto.request.SpecificationRequest;
import com.automobilefleet.api.dto.response.SpecificationResponse;
import com.automobilefleet.services.SpecificationServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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
@RequestMapping(value = "/api/v1/specification")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequiredArgsConstructor
@Slf4j
public class SpecificationController {

    private final SpecificationServiceImpl service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<SpecificationResponse> getSpecificationById(@PathVariable UUID id) {
        log.info("Getting  specification by id {}", id);
        return status(OK).body(service.getSpecificationById(id));
    }

    @GetMapping
    public ResponseEntity<List<SpecificationResponse>> listOfSpecifications() {
        log.info("Getting list of specification");
        return status(OK).body(service.listSpecifications());
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<Page<SpecificationResponse>> pageSpecification(@RequestParam int page, @RequestParam int size) {
        log.info("Getting page of specifications with page {} and size {}", page, size);

        return status(OK).body(service.pageSpecification(page, size));
    }

    @PostMapping
    public ResponseEntity<SpecificationResponse> saveSpecification(@RequestBody SpecificationRequest request) {
        log.info("Saving specification {}", request);
        var response = service.saveSpecification(request);

        return status(CREATED).body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<SpecificationResponse> updateSpecification(@PathVariable UUID id, @RequestBody SpecificationRequest request) {
        log.info("Updating specification id {} with request {}", id, request);
        var response = service.updateSpecification(id, request);

        return status(ACCEPTED).body(response);
    }
}
