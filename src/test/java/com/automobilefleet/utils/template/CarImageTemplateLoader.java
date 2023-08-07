package com.automobilefleet.utils.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.automobilefleet.api.dto.request.CarImageRequest;
import com.automobilefleet.api.dto.response.BrandResponse;
import com.automobilefleet.api.dto.response.CarImageResponse;
import com.automobilefleet.api.dto.response.CarResponse;
import com.automobilefleet.api.dto.response.CategoryResponse;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.CarImage;
import com.automobilefleet.entities.Category;

import java.time.LocalDateTime;
import java.util.UUID;

public class CarImageTemplateLoader implements TemplateLoader {
    private static final UUID ID = UUID.fromString("4bfad864-59e7-4fc3-b45e-3886694b3717");
    private static final String URL_IMAGE = "89504E470D0A1A0A0000000D49484452000000";
    private static final LocalDateTime CREATED_AT = LocalDateTime.of(2017, 3, 12, 22, 28, 12);

    @Override
    public void load() {
        Fixture.of(CarImage.class).addTemplate("car image", new Rule() {{
            add("id", ID);
            add("car", buildCar());
            add("linkImage", URL_IMAGE);
            add("createdAt", CREATED_AT);
        }});

        Fixture.of(CarImageResponse.class).addTemplate("response", new Rule() {{
            add("id", ID);
            add("car", buildCarResponse(buildCar()));
            add("linkImage", URL_IMAGE);
            add("createdAt", CREATED_AT);
        }});

        Fixture.of(CarImageRequest.class).addTemplate("request", new Rule() {{
            add("carId", UUID.fromString("4dafc4f4-5e90-478d-a386-841d74aa368a"));
            add("linkImage", URL_IMAGE);
        }});
    }

    private Car buildCar() {
        return Car
                .builder()
                .id(UUID.fromString("4dafc4f4-5e90-478d-a386-841d74aa368a"))
                .name("Civic")
                .description("Sedan médio da Honda")
                .dailyRate(110.0)
                .isAvailable(true)
                .licensePlate("OPQ-7890")
                .brand(this.buildBrand())
                .category(this.buildCategory())
                .color("Preto")
                .createdAt(CREATED_AT)
                .build();
    }

    private Brand buildBrand() {
        return Brand
                .builder()
                .id(UUID.fromString("3f831dbb-de3b-4b1a-95dc-602cdeaa7012"))
                .name("Honda")
                .createdAt(CREATED_AT)
                .build();
    }

    private Category buildCategory() {
        return Category
                .builder()
                .id(UUID.fromString("dc915ab8-aa4f-4dbd-bd09-94f8c12ff52d"))
                .name("Carros Antigos")
                .description("Veículos com mais de 30 anos de fabricação")
                .createdAt(CREATED_AT)
                .build();
    }

    private CarResponse buildCarResponse(Car car) {
        BrandResponse brandReponse = new BrandResponse(
                car.getBrand().getId(),
                car.getBrand().getName(),
                car.getBrand().getCreatedAt()
        );

        CategoryResponse categoryResponse = new CategoryResponse(
                car.getCategory().getId(),
                car.getCategory().getName(),
                car.getCategory().getDescription(),
                car.getCategory().getCreatedAt()
        );

        return CarResponse.builder()
                .id(car.getId())
                .name(car.getName())
                .description(car.getDescription())
                .dailyRate(car.getDailyRate())
                .isAvailable(car.isAvailable())
                .licensePlate(car.getLicensePlate())
                .brand(brandReponse)
                .category(categoryResponse)
                .color(car.getColor())
                .createdAt(car.getCreatedAt())
                .build();
    }
}