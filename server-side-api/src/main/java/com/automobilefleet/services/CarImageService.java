package com.automobilefleet.services;

import com.automobilefleet.api.reponse.CarImageResponse;
import com.automobilefleet.api.request.CarImageRequest;
import com.automobilefleet.entities.CarImage;
import com.automobilefleet.exceptions.CarImageNotFoundException;
import com.automobilefleet.repositories.CarImageRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarImageService {

    private final CarImageRepository repository;
    private final ModelMapper mapper;

    public List<CarImageResponse> listAllImage() {
        return this.repository.findAll().stream()
                .map(carImage -> this.mapper.map(carImage, CarImageResponse.class))
                .collect(Collectors.toList());
    }

    public CarImageResponse getImageById(Long id) {
        CarImage response = this.repository.findById(id)
                .orElseThrow(CarImageNotFoundException::new);

        return this.mapper.map(response, CarImageResponse.class);
    }

    public CarImageResponse saveCarImage(CarImageRequest request) {
        CarImage response = this.mapper.map(request, CarImage.class);
        response = repository.save(response);

        return this.mapper.map(response, CarImageResponse.class);
    }

    public CarImageResponse updateCarImage(Long id, CarImageRequest request) {
        CarImage response = this.repository.findById(id)
                .orElseThrow(CarImageNotFoundException::new);

        response.setCar(request.getCar());
        response.setImage(request.getImage());
        response = this.repository.save(response);

        return this.mapper.map(response, CarImageResponse.class);
    }

    public void deleteCarImage(Long id) {
        CarImage response = this.repository.findById(id)
                .orElseThrow(CarImageNotFoundException::new);

        this.repository.delete(response);
    }
}
