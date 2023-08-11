package com.automobilefleet.utils.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.CarImage;
import com.automobilefleet.entities.Category;

import java.util.UUID;

public class CarImageTemplateLoader implements TemplateLoader {
    private static final UUID CAR_IMAGE_ID = UUID.fromString("4bfad864-59e7-4fc3-b45e-3886694b3717");
    private static final String URL_IMAGE = "89504E470D0A1A0A0000000D49484452000000";

    // CAR
    public static final UUID CAR_ID = UUID.fromString("4dafc4f4-5e90-478d-a386-841d74aa368a");
    public static final String CAR_NAME = "Civic";
    public static final String CAR_DESCRIPTION = "Sedan médio da Honda";
    public static final double CAR_DAILY_RATE = 110.0;
    public static final boolean AVAILABLE = true;
    public static final String LICENSE_PLATE = "OPQ-7890";
    public static final String COLOR = "Preto";

    // BRAND
    public static final UUID BRAND_ID = UUID.fromString("3f831dbb-de3b-4b1a-95dc-602cdeaa7012");
    public static final String BRAND_NAME = "Honda";

    // CATEGORY
    public static final UUID CATEGORY_ID = UUID.fromString("dc915ab8-aa4f-4dbd-bd09-94f8c12ff52d");
    public static final String CATEGORY_NAME = "Carros Antigos";
    public static final String CATEGORY_DESCRIPTION = "Veículos com mais de 30 anos de fabricação";

    @Override
    public void load() {
        Fixture.of(CarImage.class).addTemplate("car image", new Rule() {{
            add("id", CAR_IMAGE_ID);
            add("car", buildCar());
            add("linkImage", URL_IMAGE);
        }});
    }

    private Car buildCar() {
        return Car
                .builder()
                .id(CAR_ID)
                .name(CAR_NAME)
                .description(CAR_DESCRIPTION)
                .dailyRate(CAR_DAILY_RATE)
                .available(AVAILABLE)
                .licensePlate(LICENSE_PLATE)
                .brand(this.buildBrand())
                .category(this.buildCategory())
                .color(COLOR)
                .build();
    }

    private Brand buildBrand() {
        return Brand
                .builder()
                .id(BRAND_ID)
                .name(BRAND_NAME)
                .build();
    }

    private Category buildCategory() {
        return Category
                .builder()
                .id(CATEGORY_ID)
                .name(CATEGORY_NAME)
                .description(CATEGORY_DESCRIPTION)
                .build();
    }
}