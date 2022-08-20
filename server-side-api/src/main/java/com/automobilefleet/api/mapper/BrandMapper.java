package com.automobilefleet.api.mapper;

import com.automobilefleet.api.reponse.BrandResponse;
import com.automobilefleet.api.request.BrandRequest;
import com.automobilefleet.entities.Brand;

import java.util.ArrayList;
import java.util.List;


public class BrandMapper {

    private BrandMapper() { }

    public static Brand toBrand(BrandRequest request) {
        Brand brand = new Brand();
        brand.setName(request.getName());

        return brand;
    }

    public static BrandResponse toBrandResponse(Brand brand) {
        BrandResponse response = new BrandResponse();
        response.setId(brand.getId());
        response.setName(brand.getName());
        response.setCreatedAt(brand.getCreatedAt());

        return response;
    }

    public static List<BrandResponse> toBrandResponseList(List<Brand> brand) {
        List<BrandResponse> response = new ArrayList<>();
        brand.forEach(brands -> response.add(toBrandResponse(brands)));

        return response;
    }

    public static void updateBrand(Brand brand, BrandRequest request) {
        brand.setName(request.getName());
    }
}
