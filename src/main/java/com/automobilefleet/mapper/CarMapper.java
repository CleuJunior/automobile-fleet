package com.automobilefleet.mapper;

import com.automobilefleet.api.dto.response.CarResponse;
import com.automobilefleet.entities.Car;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static org.springframework.data.domain.PageRequest.of;

@Component
@AllArgsConstructor
public class CarMapper {

    private final BrandMapper brandMapper;
    private final CategoryMapper categoryMapper;

    public CarResponse toCarResponse(Car car) {
        var brand = brandMapper.toBrandResponse(car.getBrand());
        var category = categoryMapper.toCategoryResponse(car.getCategory());

        return new CarResponse(
                car.getId(),
                car.getName(),
                car.getDescription(),
                car.getDailyRate(),
                car.isAvailable(),
                car.getLicensePlate(),
                brand,
                category,
                car.getColor(),
                car.getCreatedAt()
        );
    }

    public List<CarResponse> toCarResponseList(List<Car> cars) {
        return cars.stream()
                .filter(Objects::nonNull)
                .map(this::toCarResponse)
                .toList();
    }

    public Page<CarResponse> toCarResponsePage(Page<Car> cars, int page, int size) {
        var total = cars.getTotalElements();
        var response = toCarResponseList(cars.getContent());

        return new PageImpl<>(response, of(page, size), total);
    }
}
