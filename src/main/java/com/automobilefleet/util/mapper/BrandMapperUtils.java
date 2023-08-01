package com.automobilefleet.util.mapper;

import com.automobilefleet.api.dto.request.BrandRequest;
import com.automobilefleet.api.dto.response.BrandResponse;
import com.automobilefleet.entities.Brand;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class BrandMapperUtils {

    public static Brand toBrand(BrandRequest request) {
        return Brand.builder()
                .name(request.getName())
                .build();
    }

    public static BrandResponse toBrandReponse(Brand brand) {
        return new BrandResponse(brand.getId(), brand.getName(), brand.getCreatedAt());
    }
}