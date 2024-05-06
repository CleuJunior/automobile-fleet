package com.automobilefleet.mapper;

import com.automobilefleet.api.dto.response.BrandResponse;
import com.automobilefleet.entities.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static org.springframework.data.domain.PageRequest.of;

@Component
public class BrandMapper {

    public BrandResponse toBrandResponse(Brand brand) {
        return new BrandResponse(brand);
    }

    public List<BrandResponse> toListBrandResponse(List<Brand> brands) {
        return brands.stream()
                .filter(Objects::nonNull)
                .map(this::toBrandResponse)
                .toList();
    }

    public Page<BrandResponse> toBrandResponsePage(Page<Brand> brands, int page, int size) {
        var total = brands.getTotalElements();
        var response = toListBrandResponse(brands.getContent());

        return new PageImpl<>(response, of(page, size), total);
    }
}
