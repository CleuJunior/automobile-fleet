package com.automobilefleet.services;

import com.automobilefleet.api.reponse.CarImageResponse;
import com.automobilefleet.api.request.CarImageRequest;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.CarImage;
import com.automobilefleet.exceptions.BrandNotFoundException;
import com.automobilefleet.exceptions.CarImageNotFoundException;
import com.automobilefleet.repositories.CarImageRepository;
import com.automobilefleet.repositories.CarRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarImageService {

    private final CarImageRepository carImageRepository;
    private final CarRepository carRepository;
    private final ModelMapper mapper;

    public List<CarImageResponse> listAllImage() {
        return this.carImageRepository.findAll().stream()
                .map(carImage -> this.mapper.map(carImage, CarImageResponse.class))
                .collect(Collectors.toList());
    }

    public CarImageResponse getImageById(Long id) {
        CarImage response = this.carImageRepository.findById(id)
                .orElseThrow(CarImageNotFoundException::new);

        return this.mapper.map(response, CarImageResponse.class);
    }

    public CarImageResponse saveCarImage(CarImageRequest request) {
        Car car = this.carRepository.findById(request.getCarId())
            .orElseThrow(BrandNotFoundException::new);

        CarImage response = new CarImage(car, request.getImage());

        response = carImageRepository.save(response);

        return this.mapper.map(response, CarImageResponse.class);
    }

    public CarImageResponse updateCarImage(Long id, CarImageRequest request) {
        CarImage response = this.carImageRepository.findById(id)
                .orElseThrow(CarImageNotFoundException::new);

        Car car = this.carRepository.findById(request.getCarId())
                .orElseThrow(BrandNotFoundException::new);

        response.setCar(car);
        response.setImage(request.getImage());
        response = this.carImageRepository.save(response);

        return this.mapper.map(response, CarImageResponse.class);
    }

    public void deleteCarImage(Long id) {
        CarImage response = this.carImageRepository.findById(id)
                .orElseThrow(CarImageNotFoundException::new);

        this.carImageRepository.delete(response);
    }
}
