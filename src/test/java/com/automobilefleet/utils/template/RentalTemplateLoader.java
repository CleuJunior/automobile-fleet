package com.automobilefleet.utils.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.Category;
import com.automobilefleet.entities.Costumer;
import com.automobilefleet.entities.Rental;

import java.time.LocalDate;
import java.util.UUID;

public class RentalTemplateLoader implements TemplateLoader {

    // RENTAL
    private static final UUID RENTAL_ID = UUID.fromString("fc45949e-bce0-4301-9cea-fe92bfd7dc14");
    private static final LocalDate RENTAL_START_DATE = LocalDate.of(2023, 3, 20);
    private static final LocalDate RENTAL_END_DATE = LocalDate.of(2023, 3, 25);
    private static final double RENTAL_TOTAL = 493.85;

    // Car
    private static final UUID CAR_ID = UUID.fromString("55491147-1d2f-455e-9958-1e35f1df5a82");
    private static final String CAR_NAME = "Série 3";
    private static final String CAR_DESCRIPTION = "Sedan médio da BMW";
    private static final Double CAR_DAILY_RATE = 98.77;
    private static final Boolean CAR_AVAILABLE = true;
    private static final String CAR_LICENSE_PLATE = "XPT-1146";
    private static final String CAR_COLOR = "Preto";

    // Brand
    private static final UUID BRAND_ID = UUID.fromString("51208e86-1f63-4e18-ae59-941fd5e342cf");
    private static final String BRAND_NAME =  "BMW";

    // Category
    private static final UUID CATEGORY_ID = UUID.fromString("1deb1e69-6b1a-4d88-9a1b-a9889398b54a");
    private static final String CATEGORY_NAME = "SUVs";
    private static final String CATEGORY_DESCRIPTION = "Veículos utilitários esportivos";

    // COSTUMER
    private static final UUID COSTUMER_ID = UUID.fromString("32ca0461-0401-4b15-bf57-3d2b18b3828f");
    private static final String COSTUMER_NAME = "Raimunda Regina Porto";
    private static final LocalDate COSTUMER_BIRTHDATE = LocalDate.of(1974, 3, 5);
    private static final String COSTUMER_EMAIL = "raimundareginaporto@clinicasilhouette.com.br";
    private static final String COSTUMER_DRIVER_LICENSE = "08493447718";
    private static final String COSTUMER_ADDRESS = "Rua Astrogildo de Almeida, 328";
    private static final String COSTUMER_PHONE = "(73) 99362-1339";

    @Override
    public void load() {
        Fixture.of(Rental.class).addTemplate("rental", new Rule() {{
            add("id", RENTAL_ID);
            add("car", carBuild());
            add("costumer", costumerBuild());
            add("startDate", RENTAL_START_DATE);
            add("endDate", RENTAL_END_DATE);
            add("total", RENTAL_TOTAL);
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
                .brand(brandBuilt())
                .category(buildCategory())
                .color(CAR_COLOR)
                .build();
    }

    private Brand brandBuilt() {
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

    private Costumer costumerBuild() {
        return Costumer.builder()
                .id(COSTUMER_ID)
                .name(COSTUMER_NAME)
                .birthdate(COSTUMER_BIRTHDATE)
                .email(COSTUMER_EMAIL)
                .driverLicense(COSTUMER_DRIVER_LICENSE)
                .address(COSTUMER_ADDRESS)
                .phone(COSTUMER_PHONE)
                .build();
    }
}