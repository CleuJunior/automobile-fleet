package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.BrandRequest;
import com.automobilefleet.api.dto.response.BrandResponse;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.mapper.BrandMapper;
import com.automobilefleet.repositories.BrandRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.Collections.emptyList;
import static org.springframework.data.domain.Page.empty;
import static org.springframework.data.domain.PageRequest.of;


@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository repository;
    private final BrandMapper mapper;

    @Override
    public List<BrandResponse> listBrand() {
        var brands = repository.findAll();

        if (brands.isEmpty()) {
            log.info("Empty list of brand");
            return emptyList();
        }

        log.info("Return list of brand");
        return mapper.toListBrandResponse(brands);
    }

    @Override
    public Page<BrandResponse> pageBrand(int page, int size) {
        var brands = repository.findAll(of(page, size));

        if (brands.isEmpty()) {
            log.info("Empty page of brand");
            return empty();
        }

        log.info("Return page of brand");
        return mapper.toBrandResponsePage(brands, page, size);
    }

    @Override
    public BrandResponse getBrandById(UUID id) {
        var brand = findBrandOrThrow(id);

        log.info("Brand id {} found successfully", id);
        return mapper.toBrandResponse(brand);
    }

    @Override
    public BrandResponse saveBrand(BrandRequest request) {
        var response = Brand
                .builder()
                .name(request.name())
                .build();

        var brand = repository.save(response);
        log.info("Brand saved successfully");

        return mapper.toBrandResponse(brand);
    }

    @Override
    public BrandResponse updateBrand(UUID id, BrandRequest request) {
        var brand = findBrandOrThrow(id);
        brand.setName(request.name());

        repository.save(brand);
        log.info("Brand updated successfully");

        return mapper.toBrandResponse(brand);
    }

    @Override
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