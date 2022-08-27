package com.automobilefleet.api.mapper;

import com.automobilefleet.api.reponse.BrandResponse;
import com.automobilefleet.api.request.BrandRequest;
import com.automobilefleet.api.request.CarSpecificationRequest;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.entities.CarSpecification;
import com.automobilefleet.services.CarService;
import com.automobilefleet.services.SpecificationService;

import java.util.ArrayList;
import java.util.List;


public class CarSpecificationMapper {
    private static CarService carService;
    private static SpecificationService specificationService;

    private CarSpecificationMapper() { }

    public static CarSpecification toCarSpecification(CarSpecificationRequest request) {
        CarSpecification carSpecification = new CarSpecification();

//        carSpecification.setCar(carService.getCar(request.getCarId()));

        return carSpecification;
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