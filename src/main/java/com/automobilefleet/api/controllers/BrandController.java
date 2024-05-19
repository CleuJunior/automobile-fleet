package com.automobilefleet.api.controllers;

import com.automobilefleet.api.dto.request.BrandRequest;
import com.automobilefleet.api.dto.response.BrandResponse;
import com.automobilefleet.mapper.BrandMapper;
import com.automobilefleet.services.BrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping(value = "/api/v1/brand")
@RequiredArgsConstructor
@Slf4j
public class BrandController {

    private final BrandService service;
    private final BrandMapper mapper;

    @GetMapping(value = "/{id}")
    public ResponseEntity<BrandResponse> getBrandById(@PathVariable(required = false) UUID id) {
        log.info("Getting brand by id {}", id);

        var brand = service.getBrandById(id);
        var reponse = mapper.toBrandResponse(brand);

        return status(OK).body(reponse);
    }

    @GetMapping
    public ResponseEntity<List<BrandResponse>> listOfBrand() {
        log.info("Getting list of brand");

        var brands = service.listBrandNotDeleted();
        var reponse = mapper.toListBrandResponse(brands);

        return status(OK).body(reponse);
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<Page<BrandResponse>> pageBrand(@RequestParam int page, @RequestParam int size) {
        log.info("Getting page of brand with page {} and size {}", page, size);

        var brands = service.pageBrandNotDeleted(page, size);
        var reponse = mapper.toBrandResponsePage(brands, page, size);

        return status(OK).body(reponse);
    }

    @PostMapping
    public ResponseEntity<BrandResponse> saveBrand(@RequestBody BrandRequest request) {
        log.info("Saving brand {}", request);
        var response = service.saveBrand(request);

        var brands = service.pageBrandNotDeleted(page, size);
        var reponse = mapper.toBrandResponsePage(brands, page, size);

        return status(CREATED).body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BrandResponse> updateBrand(@PathVariable UUID id, @RequestBody BrandRequest request) {
        log.info("Updating brand id {} with request {}", id, request);
        var response = service.updateBrand(id, request);

        return status(ACCEPTED).body(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable UUID id) {
        log.info("Deleting brand id {}", id);
        service.deleteBrandById(id);

        return status(NO_CONTENT).build();
    }
}
