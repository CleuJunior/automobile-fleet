package com.automobilefleet.util.mapper;

import com.automobilefleet.api.response.CarImageResponse;
import com.automobilefleet.api.response.CarResponse;
import com.automobilefleet.entities.CarImage;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class CarImageMapperUtils {

    public static CarImageResponse toCarImageReponse(CarImage carImage) {
        CarResponse carResponse = CarMapperUtils.toCarResponse(carImage.getCar());

        return new CarImageResponse(carImage.getId(), carResponse, carImage.getLinkImage(), carImage.getCreatedAt());
    }
}