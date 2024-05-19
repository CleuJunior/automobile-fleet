package com.automobilefleet.services;

import com.automobilefleet.entities.Brand;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface BrandService {

    List<Brand> listBrand();

    List<Brand> listBrandNotDeleted();

    Page<Brand> pageBrand(int page, int size);

    Page<Brand> pageBrandNotDeleted(int page, int size);

    Brand getBrandById(UUID id);
    Brand getBrandByIdNotDeleted(UUID id);

    Brand saveBrand(Brand request);

    Brand updateBrand(UUID id, Brand request);

    void softDeleteBrandById(UUID id);

    void deleteBrandById(UUID id);
}
