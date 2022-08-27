package com.automobilefleet.api.controllers;

import com.automobilefleet.api.reponse.BrandResponse;
import com.automobilefleet.api.request.BrandRequest;
import com.automobilefleet.entities.CarSpecifications;
import com.automobilefleet.repositories.CarSpecificationRepository;
import com.automobilefleet.services.BrandService;
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
@RequestMapping(value = "/api/v1/car-specification")
@RequiredArgsConstructor
public class CarSpecificationController {
    private final CarSpecificationRepository repository;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CarSpecifications> getBrandById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findById(id).get());
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<CarSpecifications>> listOfBrand() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }

//    @PostMapping(value = "/save")
//    public ResponseEntity<BrandResponse> saveBrand(@RequestBody BrandRequest request) {
//        BrandResponse response = service.saveBrand(request);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
//    }
//
//    @PutMapping(value = "/update/{id}")
//    public ResponseEntity<BrandResponse> updateBrand(@PathVariable Long id, @RequestBody BrandRequest request) {
//        BrandResponse response = service.updateBrand(id, request);
//
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
//    }
//
//    @DeleteMapping(value = "/delete/{id}")
//    public ResponseEntity<BrandResponse> deleteBrand(@PathVariable Long id) {
//        service.deleteBrand(id);
//
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    }
}
