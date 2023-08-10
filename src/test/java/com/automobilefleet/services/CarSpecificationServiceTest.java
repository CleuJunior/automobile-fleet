package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.CarSpecificationRequest;
import com.automobilefleet.api.dto.response.BrandResponse;
import com.automobilefleet.api.dto.response.CarResponse;
import com.automobilefleet.api.dto.response.CarSpecificationResponse;
import com.automobilefleet.api.dto.response.CategoryResponse;
import com.automobilefleet.api.dto.response.SpecificationResponse;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.CarSpecification;
import com.automobilefleet.entities.Category;
import com.automobilefleet.entities.Specification;
import com.automobilefleet.mapper.CarSpecificationMapper;
import com.automobilefleet.repositories.CarRepository;
import com.automobilefleet.repositories.CarSpecificationRepository;
import com.automobilefleet.repositories.SpecificationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

class CarSpecificationServiceTest extends ServiceInitialSetup{
    private CarSpecificationService service;
    @Mock
    private CarSpecificationRepository carSpecificationRepository;
    @Mock
    private CarRepository carRepository;
    @Mock
    private SpecificationRepository specificationRepository;
    private final CarSpecificationMapper mapper = new CarSpecificationMapper();
    private CarSpecification carSpecification;
    private CarSpecificationResponse response;
    private static final UUID CAR_SPECIFICATION_ID = UUID.fromString("7f9128ae-eea1-478a-a3f6-d8b7d0e8574d");

    // Car Attributes
    private static final UUID CAR_ID = UUID.fromString("55491147-1d2f-455e-9958-1e35f1df5a82");
    private static final String CAR_NAME = "Série 3";
    private static final String CAR_DESCRIPTION = "Sedan médio da BMW";
    private static final double CAR_DAILY_RATE = 98.77;
    private static final boolean CAR_AVAILABLE = true;
    private static final String CAR_LICENSE_PLATE = "XPT-1146";
    private static final String CAR_COLOR = "Preto";

    // Brand Attributes
    private static final UUID BRAND_ID = UUID.fromString("51208e86-1f63-4e18-ae59-941fd5e342cf");
    private static final String BRAND_NAME = "BMW";

    // Category Attributes
    private static final UUID CATEGORY_ID = UUID.fromString("51208e86-1f63-4e18-ae59-941fd5e342cf");
    private static final String CATEGORY_NAME = "SUVs";
    private static final String CATEGORY_DESCRIPTION = "Veículos utilitários esportivos";

    // Specification Attributes
    private static final UUID SPECIFICATION_ID = UUID.fromString("7eef4f65-96a1-4463-8a7f-3a5df541f310");
    private static final String SPECIFICATION_NAME = "Motor";
    private static final String SPECIFICATION_DESCRIPTION = "Especificação técnica que define o tipo e a potência do motor do veículo.";

    @BeforeEach
    void setupAttributes() {
        this.service = new CarSpecificationService(this.carSpecificationRepository, this.carRepository,
                this.specificationRepository, this.mapper);

        Brand brand = Brand.builder()
                .id(BRAND_ID)
                .name(BRAND_NAME)
                .build();

        Category category = Category.builder()
                .id(CATEGORY_ID)
                .name(CATEGORY_NAME)
                .description(CATEGORY_DESCRIPTION)
                .build();

        Car car = Car.builder()
                .id(CAR_ID)
                .name(CAR_NAME)
                .description(CAR_DESCRIPTION)
                .dailyRate(CAR_DAILY_RATE)
                .available(CAR_AVAILABLE)
                .licensePlate(CAR_LICENSE_PLATE)
                .brand(brand)
                .category(category)
                .color(CAR_COLOR)
                .build();

        Specification specification = Specification.builder()
                .id(SPECIFICATION_ID)
                .name(SPECIFICATION_NAME)
                .description(SPECIFICATION_DESCRIPTION)
                .build();

        this.carSpecification = CarSpecification.builder()
                .id(CAR_SPECIFICATION_ID)
                .car(car)
                .specification(specification)
                .build();

        BrandResponse brandResponse = new BrandResponse(BRAND_ID, BRAND_NAME);
        CategoryResponse categoryResponse = new CategoryResponse(CATEGORY_ID, CATEGORY_NAME, CATEGORY_DESCRIPTION);

        CarResponse carResponse = new CarResponse(CAR_ID, CAR_NAME, CAR_DESCRIPTION, CAR_DAILY_RATE, CAR_AVAILABLE,
                CAR_LICENSE_PLATE, brandResponse, categoryResponse, CAR_COLOR);

        SpecificationResponse specificationResponse = new SpecificationResponse(SPECIFICATION_ID, SPECIFICATION_NAME,
                SPECIFICATION_DESCRIPTION);

        this.response = new CarSpecificationResponse(CAR_SPECIFICATION_ID, carResponse, specificationResponse);
    }

    @Override @Test
    void shouldReturnSingleList() {
        Mockito.when(this.carSpecificationRepository.findAll()).thenReturn(Collections.singletonList(this.carSpecification));

        final Optional<CarSpecificationResponse> actual = this.service.listCarSpecification().stream().findFirst();

        // Assertions
        Assertions.assertTrue(actual.isPresent());
        Assertions.assertEquals(this.response, actual.get());

        // Verifications
        Mockito.verify(this.carSpecificationRepository).findAll();
    }

    @Override @Test
    void shouldReturnById() {
        Mockito.when(this.carSpecificationRepository.findById(CAR_SPECIFICATION_ID))
                .thenReturn(Optional.ofNullable(this.carSpecification));

        final CarSpecificationResponse actual = this.service.getCarSpecificationById(CAR_SPECIFICATION_ID);

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(this.response, actual);

        // Verifications
        Mockito.verify(this.carSpecificationRepository).findById(CAR_SPECIFICATION_ID);
        Mockito.verifyNoMoreInteractions(this.carSpecificationRepository);
    }

    @Override @Test
    void shouldSave() {
        Mockito.when(this.carSpecificationRepository.save(ArgumentMatchers.any())).thenReturn(this.carSpecification);
        Mockito.when(this.carRepository.findById(CAR_ID)).thenReturn(Optional.of(new Car()));
        Mockito.when(this.specificationRepository.findById(SPECIFICATION_ID)).thenReturn(Optional.of(new Specification()));

        final CarSpecificationResponse actual =
                this.service.saveCarEspecification(new CarSpecificationRequest(CAR_ID, SPECIFICATION_ID));

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(this.response, actual);

        // Verifications
        Mockito.verify(this.carSpecificationRepository).save(ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(this.carSpecificationRepository);
    }

    @Override
    void shouldUpdate() {

    }

    @Override
    void shouldThrowErros() {

    }
}