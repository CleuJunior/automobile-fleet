package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.BrandRequest;
import com.automobilefleet.api.dto.response.BrandResponse;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.repositories.BrandRepository;
import com.automobilefleet.util.mapper.BrandMapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository repository;

    public List<BrandResponse> listBrand() {
        return this.repository.findAll().stream()
                .map(BrandMapperUtils::toBrandReponse)
                .collect(Collectors.toList());
    }

    public BrandResponse getBrandById(UUID id) {
        Brand response = this.repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionsConstants.BRAND_NOT_FOUND));

        return BrandMapperUtils.toBrandReponse(response);
    }

    public BrandResponse saveBrand(BrandRequest request) {
        Brand response = BrandMapperUtils.toBrand(request);
        return BrandMapperUtils.toBrandReponse(this.repository.save(response));
    }

    public BrandResponse updateBrand(UUID id, BrandRequest request) {
        Brand response = this.repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionsConstants.BRAND_NOT_FOUND));

        response.setName(request.getName());
        return BrandMapperUtils.toBrandReponse(this.repository.save(response));
    }

    public void deleteBrandById(UUID id) {
        Brand brand = this.repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionsConstants.BRAND_NOT_FOUND));
        this.repository.delete(brand);
    }
}