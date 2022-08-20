package com.automobilefleet.services;

import com.automobilefleet.api.mapper.BrandMapper;
import com.automobilefleet.api.reponse.BrandResponse;
import com.automobilefleet.api.request.BrandRequest;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.repositories.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository repository;

    public List<BrandResponse> listBrand() {
        List<Brand> brand = repository.findAll();

        return BrandMapper.toBrandResponseList(brand);
    }

    public BrandResponse getBrand(Long id) {
        Brand response = repository.findById(id).get();

        return BrandMapper.toBrandResponse(response);
    }

    public BrandResponse saveBrand(BrandRequest request) {
        Brand brandSave = BrandMapper.toBrand(request);
        repository.save(brandSave);

        return BrandMapper.toBrandResponse(brandSave);
    }

    public BrandResponse updateBrand(Long id, BrandRequest request) {
        Brand response = repository.findById(id).get();
        BrandMapper.updateBrand(response, request);

        repository.save(response);

        return BrandMapper.toBrandResponse(response);
    }

    public void deleteBrand(Long id) {
        repository.deleteById(id);
    }
}
