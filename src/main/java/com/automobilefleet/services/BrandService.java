package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.BrandRequest;
import com.automobilefleet.api.dto.response.BrandResponse;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.repositories.BrandRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository repository;

    public List<BrandResponse> listBrand() {
        return this.repository.findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(BrandResponse::new)
                .toList();
    }

    public BrandResponse getBrandById(UUID id) {
        Brand response = this.repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionsConstants.BRAND_NOT_FOUND));

        return new BrandResponse(response);
    }

    public BrandResponse saveBrand(BrandRequest request) {
        Brand response = Brand
                .builder()
                .name(request.name())
                .build();

        return new BrandResponse(this.repository.save(response));
    }

    public BrandResponse updateBrand(UUID id, BrandRequest request) {
        Brand response = this.repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionsConstants.BRAND_NOT_FOUND));

        response.setName(request.name());
        return new BrandResponse(this.repository.save(response));
    }

    public void deleteBrandById(UUID id) {
        Brand brand = this.repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionsConstants.BRAND_NOT_FOUND));
        this.repository.delete(brand);
    }
}