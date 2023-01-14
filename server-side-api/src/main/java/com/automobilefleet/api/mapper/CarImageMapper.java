package com.automobilefleet.api.mapper;

import com.automobilefleet.api.reponse.BrandResponse;
import com.automobilefleet.api.request.BrandRequest;
import com.automobilefleet.api.request.CarImageRequest;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.entities.CarImage;

import java.util.ArrayList;
import java.util.List;

public class CarImageMapper {

    public static CarImage toBrand(CarImageRequest request) {
        CarImage response = new CarImage();
        response.setCarId(request);
        response.setName(request.getName());

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
