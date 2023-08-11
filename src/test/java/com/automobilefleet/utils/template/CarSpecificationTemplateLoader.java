package com.automobilefleet.utils.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.CarSpecification;
import com.automobilefleet.entities.Category;
import com.automobilefleet.entities.Specification;
import org.checkerframework.checker.units.qual.Speed;

import java.util.UUID;

public class CarSpecificationTemplateLoader implements TemplateLoader {
    private static final UUID CAR_SPECIFICATION_ID = UUID.fromString("7f9128ae-eea1-478a-a3f6-d8b7d0e8574d");

    // Car
    private static final UUID CAR_ID = UUID.fromString("4f2e3bc7-8522-4543-922c-03480d044e62");
    private static final String CAR_NAME = "488";
    private static final String CAR_DESCRIPTION = "Esportivo da Ferrari";
    private static final Double CAR_DAILY_RATE = 1_500.0;
    private static final Boolean CAR_AVAILABLE = true;
    private static final String CAR_LICENSE_PLATE = "LMN-3456";
    private static final String CAR_COLOR = "Vermelho";

    // Brand
    private static final UUID BRAND_ID = UUID.fromString("4f2dd5bb-ae60-41ca-9227-0fb3dacebcbe");
    private static final String BRAND_NAME =  "Ferrari";

    // Category
    private static final UUID CATEGORY_ID = UUID.fromString("146c8a0a-828c-4d4c-bbc3-fdc70bcf38f9");
    private static final String CATEGORY_NAME = "Coupé";

    private static final String CATEGORY_DESCRIPTION = "Categoria de carros com carroceria coupé";

    // Specification
    private static final UUID SPECIFICATION_ID = UUID.fromString("6b83e4cd-ead6-4af0-8e1e-4c332a842717");
    private static final String SPECIFICATION_NAME = "Motor";
    private static final String SPECIFICATION_DESCRIPTION =
            "Especificação técnica que define o tipo e a potência do motor do veículo.";

    @Override
    public void load() {
        Fixture.of(CarSpecification.class).addTemplate("car specification", new Rule() {{
            add("id", CAR_SPECIFICATION_ID);
            add("car", carBuild());
            add("specification", buildSpecification());
        }});
    }

    private Car carBuild() {
        return Car.builder()
                .id(CAR_ID)
                .name(CAR_NAME)
                .description(CAR_DESCRIPTION)
                .dailyRate(CAR_DAILY_RATE)
                .available(CAR_AVAILABLE)
                .licensePlate(CAR_LICENSE_PLATE)
                .brand(this.buildBrand())
                .category(this.buildCategory())
                .color(CAR_COLOR)
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

    private Specification buildSpecification() {
        return Specification.builder()
                .id(SPECIFICATION_ID)
                .name(SPECIFICATION_NAME)
                .description(SPECIFICATION_DESCRIPTION)
                .build();
    }
}