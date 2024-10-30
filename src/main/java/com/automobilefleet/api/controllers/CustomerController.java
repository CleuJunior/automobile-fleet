package com.automobilefleet.api.controllers;

import com.automobilefleet.api.dto.request.CustomerRequest;
import com.automobilefleet.api.dto.response.CustomerResponse;
import com.automobilefleet.search.CustomerSearch;
import com.automobilefleet.services.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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

@RestController
@RequestMapping(value = "/api/v1/customer")
@AllArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable("id") UUID id) {
        log.info("Getting  customer by id {}", id);
        return ResponseEntity.status(OK).body(service.getCustomerById(id));
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getListCustomer() {
        log.info("Getting list of customers");
        return ResponseEntity.status(HttpStatus.OK).body(service.listCustomer());
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<Page<CustomerResponse>> pageCustomer(@RequestParam int page, @RequestParam int size) {
        log.info("Getting page of customers with page {} and size {}", page, size);
        return ResponseEntity.status(OK).body(service.pageCustomer(page, size));
    }

    @PostMapping("/search")
    public ResponseEntity<Page<CustomerResponse>> search(@RequestBody CustomerSearch search) {
        log.info("Searching  customer {}", search);
        var response = service.searchCustomer(search);

        return ResponseEntity.status(OK).body(response);
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> saveCustomer(@RequestBody @Valid CustomerRequest request) {
        log.info("Saving customer {}", request);
        var response = service.saveCustomer(request);

        return ResponseEntity.status(CREATED).body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable UUID id, @RequestBody CustomerRequest request) {
        log.info("Updating customer id {} with request {}", id, request);
        var response = service.updateCustomer(id, request);

        return ResponseEntity.status(ACCEPTED).body(response);
    }

}
