package com.automobilefleet.mapper;

import com.automobilefleet.api.dto.response.BrandResponse;
import com.automobilefleet.entities.Brand;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class BrandMapper implements Function<Brand, BrandResponse> {

    @Override
    public BrandResponse apply(Brand brand) {
        return new BrandResponse(brand.getId(), brand.getName());
    }
}