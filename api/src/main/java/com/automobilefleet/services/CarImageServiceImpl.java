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

import java.util.Collections;
import java.util.List;
import java.util.UUID;

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
            return Collections.emptyList();
        }

        log.info("Return list of images");
        return mapper.toCarImageResponseList(images);
    }

    @Override
    public Page<CarImageResponse> pageCarImage(int page, int size) {
        var images = carImageRepository.findAll(of(page, size));

        if (images.isEmpty()) {
            log.info("Empty page of images");
            return Page.empty();
        }

        log.info("Return page of images with size {}", size);
        return mapper.toCarImageResponsePage(images, page, size);
    }

    @Override
    public CarImageResponse getImageById(UUID id) {
        log.info("Image id {}", id);
        return carImageRepository.findById(id)
                .map(mapper::toCarImageResponse)
                .orElseThrow(() -> new NotFoundException("car.image.not.found", id));
    }

    @Override
    public CarImageResponse saveCarImage(CarImageRequest request) {
        var car = carRepository.findById(request.carId())
                .orElseThrow(() -> new NotFoundException("car.not.found", request.carId()));

        var response = CarImage.builder()
                .car(car)
                .linkImage(request.linkImage())
                .build();

        log.info("Image saved successfully");
        return mapper.toCarImageResponse(carImageRepository.save(response));
    }

    @Override
    public CarImageResponse updateCarImage(UUID id, CarImageRequest request) {
        var car = carRepository.findById(request.carId())
                .orElseThrow(() -> new NotFoundException("car.not.found", request.carId()));

        return carImageRepository.findById(id)
                .map(current -> mapper.apply(current, car, request.linkImage()))
                .map(carImageRepository::save)
                .map(mapper::toCarImageResponse)
                .orElseThrow(() -> new NotFoundException("car.image.not.found", id));
    }

    @Override
    public void deleteCarImageById(UUID id) {
        carImageRepository.findById(id)
                .ifPresentOrElse(
                        current -> {
                            carImageRepository.delete(current);
                            log.info("Image id {} deleted successfully", id);
                        },
                        () -> {
                             log.error("Image id: {} not found", id);
                             throw new NotFoundException("image.not.found", id);
                        }
                );
    }
}