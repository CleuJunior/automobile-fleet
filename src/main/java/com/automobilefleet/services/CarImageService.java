package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.CarImageRequest;
import com.automobilefleet.api.dto.response.CarImageResponse;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.CarImage;
import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.repositories.CarImageRepository;
import com.automobilefleet.repositories.CarRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CarImageService {
    private final CarImageRepository carImageRepository;
    private final CarRepository carRepository;

    public List<CarImageResponse> listAllImage() {
        return this.carImageRepository.findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(CarImageResponse::new)
                .toList();
    }

    public CarImageResponse getImageById(UUID id) {
        Optional<CarImage> response = this.carImageRepository.findById(id);

        if (response.isEmpty()) {
            throw new NotFoundException(ExceptionsConstants.IMAGE_NOT_FOUND);
        }

        return new CarImageResponse(response.get());
    }

    public CarImageResponse saveCarImage(CarImageRequest request) {
        Optional<Car> car = this.carRepository.findById(request.carId());

        if (car.isEmpty()) {
            throw new NotFoundException(ExceptionsConstants.CAR_NOT_FOUND);
        }

        CarImage response = CarImage.builder()
                .car(car.get())
                .linkImage(request.linkImage())
                .build();

        return new CarImageResponse(this.carImageRepository.save(response));
    }

    public CarImageResponse updateCarImage(UUID id, CarImageRequest request) {
        Optional<CarImage> response = this.carImageRepository.findById(id);

        if (response.isEmpty())
            throw new NotFoundException(ExceptionsConstants.IMAGE_NOT_FOUND);

        Optional<Car> car = this.carRepository.findById(request.carId());

        if (car.isEmpty())
           throw new NotFoundException(ExceptionsConstants.CAR_NOT_FOUND);

        response.get().setCar(car.get());
        response.get().setLinkImage(request.linkImage());
        return new CarImageResponse(this.carImageRepository.save(response.get()));
    }

    public void deleteCarImageById(UUID id) {
        Optional<CarImage> response = this.carImageRepository.findById(id);

        if (response.isEmpty())
            throw new NotFoundException(ExceptionsConstants.IMAGE_NOT_FOUND);

        this.carImageRepository.deleteById(response.get().getId());
    }
}