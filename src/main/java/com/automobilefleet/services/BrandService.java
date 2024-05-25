package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.BrandRequest;
import com.automobilefleet.api.dto.response.BrandResponse;
import com.automobilefleet.entities.Brand;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface BrandService {

    List<BrandResponse> listBrand();

    Page<BrandResponse> pageBrand(int page, int size);

    BrandResponse getBrandById(UUID id);

    BrandResponse saveBrand(BrandRequest request);

    BrandResponse updateBrand(UUID id, BrandRequest request);

    void deleteBrandById(UUID id);
}
