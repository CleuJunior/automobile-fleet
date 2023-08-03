package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.BrandRequest;
import com.automobilefleet.api.dto.response.BrandResponse;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.repositories.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository repository;
    private final ModelMapper mapper;

    public List<BrandResponse> listBrand() {
        return this.repository.findAll().stream()
                .filter(Objects::nonNull)
                .map(brand -> this.mapper.map(brand, BrandResponse.class))
                .collect(Collectors.toList());
    }

    public BrandResponse getBrandById(UUID id) {
        Brand response = this.repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionsConstants.BRAND_NOT_FOUND));

        return this.mapper.map(response, BrandResponse.class);
    }

    public BrandResponse saveBrand(BrandRequest request) {
        Brand response = this.mapper.map(request, Brand.class);
        return this.mapper.map(this.repository.save(response), BrandResponse.class);
    }

    public BrandResponse updateBrand(UUID id, BrandRequest request) {
        Brand response = this.repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionsConstants.BRAND_NOT_FOUND));

        response.setName(request.getName());
        return this.mapper.map(this.repository.save(response), BrandResponse.class);
    }

    public void deleteBrandById(UUID id) {
        Brand brand = this.repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionsConstants.BRAND_NOT_FOUND));
        this.repository.delete(brand);
    }
}