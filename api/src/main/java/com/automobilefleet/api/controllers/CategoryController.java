package com.automobilefleet.api.controllers;

import com.automobilefleet.api.dto.request.CategoryRequest;
import com.automobilefleet.api.dto.response.CategoryResponse;
import com.automobilefleet.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping(value = "/api/v1/category")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {
    private final CategoryService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable UUID id) {
        log.info("Getting category by id {}", id);
        return status(OK).body(service.getCategoryById(id));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> listOfCategory() {
        log.info("Getting list of categories");
        return status(OK).body(service.listCategories());
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> saveCategory(@RequestBody @Valid CategoryRequest request) {
        log.info("Saving category {}", request);
        var response = service.saveCategory(request);

        return status(CREATED).body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable UUID id, @RequestBody @Valid CategoryRequest request) {
        log.info("Updating category id {} with request {}", id, request);
        var response = service.updateCategory(id, request);

        return status(ACCEPTED).body(response);
    }
}