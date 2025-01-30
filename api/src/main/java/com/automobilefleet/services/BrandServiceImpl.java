package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.BrandRequest;
import com.automobilefleet.api.dto.response.BrandResponse;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.mapper.BrandMapper;
import com.automobilefleet.repositories.BrandRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.UUID;


@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository repository;
    private final BrandMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<BrandResponse> listBrand() {
        var brands = repository.findAll();

        if (brands.isEmpty()) {
            log.info("Empty list of brand");
            return Collections.emptyList();
        }

        log.info("Return list of brand");
        return mapper.toListBrandResponse(brands);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BrandResponse> pageBrand(int page, int size) {
        var brands = repository.findAll(PageRequest.of(page, size));

        if (brands.isEmpty()) {
            log.info("Empty page of brand");
            return Page.empty();
        }

        log.info("Return page of brand");
        return mapper.toBrandResponsePage(brands, page, size);
    }

    @Override
    @Transactional(readOnly = true)
    public BrandResponse getBrandById(UUID id) {
        log.info("Finding brand id {}", id);
        return repository.findById(id)
                .map(mapper::toBrandResponse)
                .orElseThrow(() -> new NotFoundException("brand.not.found", id));
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
        return repository.findById(id)
                .map(current -> mapper.apply(current, request))
                .map(repository::save)
                .map(mapper::toBrandResponse)
                .orElseThrow(() -> new NotFoundException("brand.not.found", id));
    }

    @Override
    public void deleteBrandById(UUID id) {
        repository.findById(id)
                .ifPresentOrElse(
                        current -> {
                            repository.delete(current);
                            log.info("Brand id {} deleted successfully", id);
                        },
                        () -> {
                            log.error("Brand id: {} not found", id);
                            throw new NotFoundException("brand.not.found", id);
                        }
                );
    }
}