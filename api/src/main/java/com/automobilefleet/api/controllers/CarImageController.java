package com.automobilefleet.api.controllers;

import com.automobilefleet.api.dto.request.CarImageRequest;
import com.automobilefleet.api.dto.response.CarImageResponse;
import com.automobilefleet.services.CarImageService;
import jakarta.validation.Valid;
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


@RestController
@RequestMapping(value = "/api/v1/carImages")
@RequiredArgsConstructor
@Slf4j
public class CarImageController {

    private final CarImageService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CarImageResponse> getCarImageById(@PathVariable UUID id) {
        log.info("Getting image by id {}", id);
        return ResponseEntity.status(OK).body(service.getImageById(id));
    }

    @GetMapping
    public ResponseEntity<List<CarImageResponse>> listOfCarImages() {
        log.info("Getting list of images");
        return ResponseEntity.status(OK).body(service.listAllImage());
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<Page<CarImageResponse>> pageCarImages(@RequestParam int page, @RequestParam int size) {
        log.info("Getting page of images with page {} and size {}", page, size);
        return ResponseEntity.status(OK).body(service.pageCarImage(page, size));
    }

    @PostMapping
    public ResponseEntity<CarImageResponse> saveImage(@RequestBody @Valid CarImageRequest request) {
        log.info("Saving image {}", request);
        var response = service.saveCarImage(request);

        return ResponseEntity.status(CREATED).body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CarImageResponse> updateCarImage(@PathVariable UUID id, @RequestBody @Valid CarImageRequest request) {
        log.info("Updating image id {} with request {}", id, request);
        var response = service.updateCarImage(id, request);

        return ResponseEntity.status(ACCEPTED).body(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCarImageById(@PathVariable UUID id) {
        log.info("Deleting image id {}", id);
        service.deleteCarImageById(id);

        return ResponseEntity.status(NO_CONTENT).build();
    }
}