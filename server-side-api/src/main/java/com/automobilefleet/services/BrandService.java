package com.automobilefleet.services;

import com.automobilefleet.api.reponse.BrandResponse;
import com.automobilefleet.api.request.BrandRequest;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.exceptions.BrandNotFoundException;
import com.automobilefleet.repositories.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository repository;
    private final ModelMapper mapper;
    public List<BrandResponse> listBrand() {

        return this.repository.findAll().stream()
                .map(brand -> this.mapper.map(brand, BrandResponse.class))
                .collect(Collectors.toList());
    }

    public BrandResponse getBrand(Long id) {
        Brand response = this.repository.findById(id)
                .orElseThrow(BrandNotFoundException::new);

        return this.mapper.map(response, BrandResponse.class);
    }

    public BrandResponse saveBrand(BrandRequest request) {
        Brand brandSave = this.mapper.map(request, Brand.class);
        this.repository.save(brandSave);

        return this.mapper.map(brandSave, BrandResponse.class);
    }

    public BrandResponse updateBrand(Long id, BrandRequest request) {
        Brand response = this.repository.findById(id)
                .orElseThrow(BrandNotFoundException::new);

        response.setName(request.getName());

        this.repository.save(response);
        return this.mapper.map(response, BrandResponse.class);
    }

    public void deleteBrand(Long id) {
        try {
            this.repository.deleteById(id);

        } catch (BrandNotFoundException e) {
            throw new BrandNotFoundException();
        }
    }
}
