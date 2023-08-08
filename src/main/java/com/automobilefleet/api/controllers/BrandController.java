package com.automobilefleet.api.controllers;

import com.automobilefleet.api.dto.request.BrandRequest;
import com.automobilefleet.api.dto.response.BrandResponse;
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
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/brand")
@RequiredArgsConstructor
public class BrandController {
    private final BrandService service;

    @GetMapping(value = "/{id}")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<BrandResponse> getBrandById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.getBrandById(id));
    }

    @GetMapping(value = "/list")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<BrandResponse>> listOfBrand() {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.listBrand());
    }

    @PostMapping(value = "/save")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<BrandResponse> saveBrand(@RequestBody BrandRequest request) {
        BrandResponse response = this.service.saveBrand(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(value = "/update/{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<BrandResponse> updateBrand(@PathVariable UUID id, @RequestBody BrandRequest request) {
        BrandResponse response = this.service.updateBrand(id, request);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @DeleteMapping(value = "/delete/{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteBrand(@PathVariable UUID id) {
        this.service.deleteBrandById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
