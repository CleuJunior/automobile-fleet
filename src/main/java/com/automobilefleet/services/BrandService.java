package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.BrandRequest;
import com.automobilefleet.api.dto.response.BrandResponse;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.repositories.BrandRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BrandService {
    private final BrandRepository repository;

    public List<BrandResponse> listBrand() {
        log.info("Return list of brand");

        return repository.findAll().stream()
                .filter(Objects::nonNull)
                .map(BrandResponse::new)
                .toList();
    }

    public BrandResponse getBrandById(UUID id) {
        var brand = findBrandOrThrow(id);

        log.info("Brand id {} found successfully", id);
        return new BrandResponse(brand);
    }

    public BrandResponse saveBrand(BrandRequest request) {
        var response = Brand
                .builder()
                .name(request.name())
                .build();

        log.info("Brand saved successfully");
        return new BrandResponse(repository.save(response));
    }

    public BrandResponse updateBrand(UUID id, BrandRequest request) {
        var brand = findBrandOrThrow(id);
        brand.setName(request.name());

        log.info("Brand updated successfully");
        return new BrandResponse(repository.save(brand));
    }

    public void deleteBrandById(UUID id) {
        var brand = findBrandOrThrow(id);

        log.info("Brand id {} deleted successfully", id);
        repository.delete(brand);
    }

    private Brand findBrandOrThrow(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> {
                    log.error("Brand id: {} not found", id);
                    return new NotFoundException(ExceptionsConstants.BRAND_NOT_FOUND);
                });
    }
}