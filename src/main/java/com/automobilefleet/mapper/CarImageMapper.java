package com.automobilefleet.mapper;

import com.automobilefleet.api.dto.response.CarImageResponse;
import com.automobilefleet.entities.CarImage;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static org.springframework.data.domain.PageRequest.of;

@Component
@AllArgsConstructor
public class CarImageMapper {

    private final CarMapper carMapper;

    public CarImageResponse toCarImageResponse(CarImage carImage) {
        var car = carMapper.toCarResponse(carImage.getCar());

        return new CarImageResponse(
                carImage.getId(),
                car.id(),
                carImage.getLinkImage(),
                carImage.getCreatedAt()
        );
    }

    public List<CarImageResponse> toCarImagenResponseList(List<CarImage> images) {
        return images.stream()
                .filter(Objects::nonNull)
                .map(this::toCarImageResponse)
                .toList();
    }

    public Page<CarImageResponse> toSpecificationResponsePage(Page<CarImage> images, int page, int size) {
        var total = images.getTotalElements();
        var response = toCarImagenResponseList(images.getContent());

        return new PageImpl<>(response, of(page, size), total);
    }
}
