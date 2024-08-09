package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.CarImageRequest;
import com.automobilefleet.api.dto.response.CarImageResponse;
import com.automobilefleet.entities.CarImage;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.mapper.CarImageMapper;
import com.automobilefleet.repositories.CarImageRepository;
import com.automobilefleet.repositories.CarRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.Collections.emptyList;
import static org.springframework.data.domain.Page.empty;
import static org.springframework.data.domain.PageRequest.of;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CarImageServiceImpl implements CarImageService {

    private final CarImageRepository carImageRepository;
    private final CarRepository carRepository;
    private final CarImageMapper mapper;

    @Override
    public List<CarImageResponse> listAllImage() {
        var images = carImageRepository.findAll();

        if (images.isEmpty()) {
            log.info("Empty list of images");
            return emptyList();
        }

        log.info("Return list of images");
        return mapper.toCarImageResponseList(images);
    }

    @Override
    public Page<CarImageResponse> pageCarImage(int page, int size) {
        var images = carImageRepository.findAll(of(page, size));

        if (images.isEmpty()) {
            log.info("Empty page of images");
            return empty();
        }

        log.info("Return page of images with size {}", size);
        return mapper.toCarImageResponsePage(images, page, size);
    }

    @Override
    public CarImageResponse getImageById(UUID id) {
        var image = findImageOrThrow(id);

        log.info("Image id {} found successfully!", id);
        return mapper.toCarImageResponse(image);
    }

    @Override
    public CarImageResponse saveCarImage(CarImageRequest request) {
        var car = carRepository.findById(request.carId());

        if (car.isEmpty()) {
            log.error("Car id {} not found", request.carId());
            throw new NotFoundException("car.not.found", request.carId());
        }

        var response = CarImage.builder()
                .car(car.get())
                .linkImage(request.linkImage())
                .build();

        log.info("Image saved successfully");
        return mapper.toCarImageResponse(carImageRepository.save(response));
    }

    @Override
    public CarImageResponse updateCarImage(UUID id, CarImageRequest request) {
        var image = findImageOrThrow(id);

        var car = carRepository.findById(request.carId());

        if (car.isEmpty()) {
            log.error("Car id {} not found", request.carId());
            throw new NotFoundException("car.not.found", request.carId());
        }

        image.setCar(car.get());
        image.setLinkImage(request.linkImage());

        log.info("Image id {} updated successfully", id);
        return mapper.toCarImageResponse(carImageRepository.save(image));
    }

    @Override
    public void deleteCarImageById(UUID id) {
        var response = findImageOrThrow((id));

        carImageRepository.delete(response);
        log.info("Image id {} deleted successfully", id);
    }

    private CarImage findImageOrThrow(UUID id) {
        var response = carImageRepository.findById(id);

        if (response.isEmpty()) {
            log.error("Image id {} not found", id);
            throw new NotFoundException("image.not.found", id);
        }

        return response.get();
    }
}