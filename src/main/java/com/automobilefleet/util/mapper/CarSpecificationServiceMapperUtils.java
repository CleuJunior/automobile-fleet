package com.automobilefleet.util.mapper;

import com.automobilefleet.api.response.BrandResponse;
import com.automobilefleet.api.response.CarResponse;
import com.automobilefleet.api.response.CarSpecificationResponse;
import com.automobilefleet.api.response.SpecificationResponse;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.entities.CarSpecification;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class CarSpecificationServiceMapperUtils {

    public static CarSpecificationResponse toCarSpecificationResponse(CarSpecification carSpecification) {
        CarResponse carResponse = CarMapperUtils.toCarResponse(carSpecification.getCar());
        SpecificationResponse specificationResponse =
                SpecificationMapperUtils.toSpecificationReponse(carSpecification.getSpecification());

        return new CarSpecificationResponse(carSpecification.getId(), carResponse, specificationResponse);
    }

    public static BrandResponse toBrandReponse(Brand brand) {
        return new BrandResponse(brand.getId(), brand.getName(), brand.getCreatedAt());
    }
}