package com.automobilefleet.api.controllers;

import com.automobilefleet.api.reponse.CostumerResponse;
import com.automobilefleet.api.request.CostumerRequest;
import com.automobilefleet.services.CostumerService;
import lombok.AllArgsConstructor;
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
@RequestMapping(value = "/api/v1/costumer")
@AllArgsConstructor
public class CostumerController {
    private final CostumerService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CostumerResponse> getCostumerById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.service.getCostumerById(id));
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<CostumerResponse> getCostumerByName(@PathVariable("name") String name) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.service.getCostumerByName(name));
    }

    @GetMapping(value = "/names/{name}")
    public ResponseEntity<List<CostumerResponse>> findListNameLike(@PathVariable("name") String name) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.service.findListNameLike(name));
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<CostumerResponse>> getListCostumer() {
        return ResponseEntity.status(HttpStatus.OK).body(service.listCostumer());
    }

    @PostMapping(value = "/save")
    public ResponseEntity<CostumerResponse> saveCostumer(@RequestBody CostumerRequest request) {
        CostumerResponse response = service.saveCostumer(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<CostumerResponse> updateCostumer(@PathVariable Long id, @RequestBody CostumerRequest request) {
        CostumerResponse response = service.updateCostumer(id, request);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<CostumerResponse> deleteCostumer(@PathVariable Long id) {
        service.deleteCostumer(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
