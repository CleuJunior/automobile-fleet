package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.CarImageRequest;
import com.automobilefleet.api.dto.response.CarImageResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface CarImageService {

    List<CarImageResponse> listAllImage();

    Page<CarImageResponse> pageCarImage(int page, int size);


    CarImageResponse getImageById(UUID id);

    CarImageResponse saveCarImage(CarImageRequest request);

    CarImageResponse updateCarImage(UUID id, CarImageRequest request);

    void deleteCarImageById(UUID id);

}
