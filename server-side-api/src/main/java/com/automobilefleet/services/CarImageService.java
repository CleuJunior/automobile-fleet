package com.automobilefleet.services;

import com.automobilefleet.api.mapper.CarImageMapper;
import com.automobilefleet.api.reponse.CarImageResponse;
import com.automobilefleet.api.request.CarImageRequest;
import com.automobilefleet.entities.CarImage;
import com.automobilefleet.repositories.CarImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarImageService {

    private final CarImageRepository carImageRepository;

    public List<CarImageResponse> listAllImage() {
        List<CarImage> carImages = this.carImageRepository.findAll();

        return CarImageMapper.toCarImageResponseList(carImages);
    }

    public CarImageResponse getImageById(Long id) {
        CarImage response = this.carImageRepository.findById(id).get();

        return CarImageMapper.toCarImageResponse(response);
    }

    public CarImageResponse saveCarImage(CarImageRequest request) {
        CarImage carImage = CarImageMapper.toCarImage(request);
        carImageRepository.save(carImage);

        return CarImageMapper.toCarImageResponse(carImage);
    }

    public CarImageResponse updateCarImage(Long id, CarImageRequest request) {
        CarImage response = this.carImageRepository.findById(id).get();
        CarImageMapper.updateCarImage(response, request);

        this.carImageRepository.save(response);
        return CarImageMapper.toCarImageResponse(response);
    }

    public void deleteCarImage(Long id) {
        this.carImageRepository.deleteById(id);
    }

}
