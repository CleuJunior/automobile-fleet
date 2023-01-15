package com.automobilefleet.api.mapper;

import com.automobilefleet.api.reponse.BrandResponse;
import com.automobilefleet.api.reponse.CarImageResponse;
import com.automobilefleet.api.request.BrandRequest;
import com.automobilefleet.api.request.CarImageRequest;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.entities.CarImage;

import java.util.ArrayList;
import java.util.List;

public class CarImageMapper {

    public static CarImage toCarImage(CarImageRequest request) {
        CarImage response = new CarImage();
        response.setCar(request.getCar());
        response.setImage(request.getImage());

        return response;
    }

    public static CarImageResponse toCarImageResponse(CarImage carImage) {
        CarImageResponse response = new CarImageResponse();
        response.setId(carImage.getId());
        response.setCar(carImage.getCar());
        response.setImage(carImage.getImage());

        response.setCreatedAt(carImage.getCreatedAt());

        return response;
    }

    public static List<CarImageResponse> toCarImageResponseList(List<CarImage> carImages) {
        List<CarImageResponse> listResponses = new ArrayList<>();
        carImages.forEach(image -> listResponses.add(toCarImageResponse(image)));

        return listResponses;
    }

    public static void updateCarImage(CarImage carImage, CarImageRequest request) {
        carImage.setCar(request.getCar());
        carImage.setImage(request.getImage());
    }
}
