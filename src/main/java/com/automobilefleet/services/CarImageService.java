package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.CarImageRequest;
import com.automobilefleet.api.dto.response.CarImageResponse;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.CarImage;
import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.repositories.CarImageRepository;
import com.automobilefleet.repositories.CarRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
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

    public CarImageResponse getImageById(UUID id) {
        Optional<CarImage> response = this.carImageRepository.findById(id);

        if (response.isEmpty())
            throw new NotFoundException(ExceptionsConstants.IMAGE_NOT_FOUND);

        return this.mapper.map(response.get(), CarImageResponse.class);
    }

    public CarImageResponse saveCarImage(CarImageRequest request) {
        Optional<Car> car = this.carRepository.findById(request.getCarId());

        if (car.isEmpty())
            throw new NotFoundException(ExceptionsConstants.CAR_NOT_FOUND);

        CarImage response = this.mapper.map(request, CarImage.class);
        return this.mapper.map(this.carImageRepository.save(response), CarImageResponse.class);
    }

    public CarImageResponse updateCarImage(UUID id, CarImageRequest request) {
        Optional<CarImage> response = this.carImageRepository.findById(id);

        if (response.isEmpty())
            throw new NotFoundException(ExceptionsConstants.IMAGE_NOT_FOUND);

        Optional<Car> car = this.carRepository.findById(request.getCarId());

        if (car.isEmpty())
           throw new NotFoundException(ExceptionsConstants.CAR_NOT_FOUND);

        response.get().setCar(car.get());
        response.get().setLinkImage(request.getLinkImage());
        return this.mapper.map(this.carImageRepository.save(response.get()), CarImageResponse.class);
    }

    public void deleteCarImageById(UUID id) {
        Optional<CarImage> response = this.carImageRepository.findById(id);

        if (response.isEmpty())
            throw new NotFoundException(ExceptionsConstants.IMAGE_NOT_FOUND);

        this.carImageRepository.deleteById(response.get().getId());
    }
}