package com.automobilefleet.api.controllers;

import com.automobilefleet.api.response.CostumerResponse;
import com.automobilefleet.api.request.CostumerRequest;
import com.automobilefleet.services.CostumerService;
import lombok.AllArgsConstructor;
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

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/costumer")
@AllArgsConstructor
public class CostumerController {
    private final CostumerService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CostumerResponse> getCostumerById(@PathVariable("id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.service.getCostumerById(id));
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<CostumerResponse>> getListCostumer() {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.listCostumer());
    }

    @PostMapping(value = "/save")
    public ResponseEntity<CostumerResponse> saveCostumer(@RequestBody @Valid CostumerRequest request) {
        CostumerResponse response = this.service.saveCostumer(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<CostumerResponse> updateCostumer(@PathVariable UUID id, @RequestBody CostumerRequest request) {
        CostumerResponse response = this.service.updateCostumer(id, request);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

}
