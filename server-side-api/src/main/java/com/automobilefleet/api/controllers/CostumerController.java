package com.automobilefleet.api.controllers;

import com.automobilefleet.api.reponse.CostumerResponse;
import com.automobilefleet.api.request.CostumerRequest;
import com.automobilefleet.entities.Costumer;
import com.automobilefleet.repositories.CostumerRepository;
import com.automobilefleet.services.CostumerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/api/v1/costumer")
@AllArgsConstructor
public class CostumerController {
    private final CostumerService service;
    private final CostumerRepository repository;


    @GetMapping(value = "/{id}")
    public Costumer getCostumerById(@PathVariable Long id) {
        Costumer newCostumer = new Costumer(
                "Cleonildo Jr",
                LocalDate.of(1990, 11, 9),
                "cleonildo@gmail.com",
                "CJ-12345",
                "Rua dois",
                "11 99999-9999"
        );

        return this.repository.save(newCostumer);

//        return service.getCostumerById(id);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<CostumerResponse> saveCostumer(@RequestBody CostumerRequest request) {
        CostumerResponse response = service.saveCostumer(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
