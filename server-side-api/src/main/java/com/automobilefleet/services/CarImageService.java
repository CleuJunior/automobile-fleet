package com.automobilefleet.services;

import com.automobilefleet.api.response.CarImageResponse;
import com.automobilefleet.api.request.CarImageRequest;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.CarImage;
import com.automobilefleet.exceptions.notfoundexception.BrandNotFoundException;
import com.automobilefleet.exceptions.notfoundexception.CarImageNotFoundException;
import com.automobilefleet.repositories.CarImageRepository;
import com.automobilefleet.repositories.CarRepository;
import com.automobilefleet.util.mapper.CarImageMapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CarImageService {
    private final CarImageRepository carImageRepository;
    private final CarRepository carRepository;

    public List<CarImageResponse> listAllImage() {
        return this.carImageRepository.findAll().stream()
                .map(CarImageMapperUtils::toCarImageReponse)
                .collect(Collectors.toList());
    }

    public CarImageResponse getImageById(UUID id) {
        CarImage response = this.carImageRepository.findById(id)
                .orElseThrow(CarImageNotFoundException::new);

        return CarImageMapperUtils.toCarImageReponse(response);
    }

    public CarImageResponse saveCarImage(CarImageRequest request) {
        Car car = this.carRepository.findById(request.getCarId())
                .orElseThrow(BrandNotFoundException::new);

        CarImage response = new CarImage(car, request.getLinkImage());
       return CarImageMapperUtils.toCarImageReponse(this.carImageRepository.save(response));
    }

    public CarImageResponse updateCarImage(UUID id, CarImageRequest request) {
        CarImage response = this.carImageRepository.findById(id)
                .orElseThrow(CarImageNotFoundException::new);

        Car car = this.carRepository.findById(request.getCarId())
                .orElseThrow(BrandNotFoundException::new);

        response.setCar(car);
        response.setLinkImage(request.getLinkImage());
        return CarImageMapperUtils.toCarImageReponse(this.carImageRepository.save(response));
    }

    public void deleteCarImage(UUID id) {
        CarImage response = this.carImageRepository.findById(id)
                .orElseThrow(CarImageNotFoundException::new);

        this.carImageRepository.delete(response);
    }
}
