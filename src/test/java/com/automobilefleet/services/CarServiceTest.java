package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.CarRequest;
import com.automobilefleet.api.dto.response.BrandResponse;
import com.automobilefleet.api.dto.response.CarResponse;
import com.automobilefleet.api.dto.response.CategoryResponse;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.Category;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.mapper.CarMapper;
import com.automobilefleet.repositories.BrandRepository;
import com.automobilefleet.repositories.CarRepository;
import com.automobilefleet.repositories.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

class CarServiceTest extends ServiceInitialSetup {
    private CarService service;
    @Mock
    private CarRepository carRepository;
    @Mock
    private BrandRepository brandRepository;
    @Mock
    private CategoryRepository categoryRepository;
    private final CarMapper mapper = new CarMapper();
    private Car car;
    private CarResponse response;

    // Car Attributes
    private static final UUID CAR_ID = UUID.fromString("4f2e3bc7-8522-4543-922c-03480d044e62");
    private static final String CAR_NAME = "488";
    private static final String CAR_DESCRIPTION = "Esportivo da Ferrari";
    private static final Double DAILY_RATE = 1_500.0;
    private static final Boolean AVAILABLE = true;
    private static final String LICENSE_PLATE = "LMN-3456";
    private static final String COLOR = "Vermelho";

    // Brand Attributes
    private static final UUID BRAND_ID = UUID.fromString("0a7d6250-0be5-4036-8f23-33dc1762bed0");
    private static final String BRAND_NAME = "BMW";

    // Category Attributes
    private static final UUID CATEGORY_ID = UUID.fromString("b86a92d8-6908-426e-8316-f72b0c849a4b");
    private static final String CATEGORY_NAME = "SUVs";
    private static final String CATEGORY_DESCRIPTION = "Veículos utilitários esportivos";

    @BeforeEach
    void setupAttributes() {
        this.service = new CarService(this.carRepository, this.brandRepository, this.categoryRepository, this.mapper);

        BrandResponse brandResponse = new BrandResponse(BRAND_ID, BRAND_NAME);
        CategoryResponse categoryResponse = new CategoryResponse(CATEGORY_ID, CATEGORY_NAME, CATEGORY_DESCRIPTION);

        this.response = new CarResponse(CAR_ID, CAR_NAME, CAR_DESCRIPTION, DAILY_RATE, AVAILABLE, LICENSE_PLATE,
                brandResponse, categoryResponse, COLOR);

        Category category = Category.builder()
                .id(CATEGORY_ID)
                .name(CATEGORY_NAME)
                .description(CATEGORY_DESCRIPTION)
                .build();

        Brand brand = Brand.builder()
                .id(BRAND_ID)
                .name(BRAND_NAME)
                .build();

        this.car = Car.builder()
                .id(CAR_ID)
                .name(CAR_NAME)
                .description(CAR_DESCRIPTION)
                .dailyRate(DAILY_RATE)
                .available(AVAILABLE)
                .licensePlate(LICENSE_PLATE)
                .brand(brand)
                .category(category)
                .color(COLOR)
                .build();
    }

    @Override @Test
    void shouldReturnSingleList() {
        Mockito.when(this.carRepository.findAll()).thenReturn(Collections.singletonList(this.car));

        final Optional<CarResponse> actual = this.service.listOfCars().stream().findFirst();

        // Assertions
        Assertions.assertTrue(actual.isPresent());
        Assertions.assertEquals(this.response, actual.get());

        // Verifications
        Mockito.verify(this.carRepository).findAll();
        Mockito.verifyNoMoreInteractions(this.carRepository);
    }

    @Test
    void shouldReturnSingleListCallingByName() {
        Mockito.when(this.carRepository.findCarsByBrand_Name(BRAND_NAME))
                .thenReturn(Collections.singletonList(this.car));

        final Optional<CarResponse> actual = this.service.findByCarBrand(BRAND_NAME).stream().findFirst();

        // Assertions
        Assertions.assertTrue(actual.isPresent());
        Assertions.assertEquals(this.response, actual.get());

        // Verifications
        Mockito.verify(this.carRepository).findCarsByBrand_Name(BRAND_NAME);
        Mockito.verifyNoMoreInteractions(this.carRepository);
    }

    @Test
    void shouldReturnSingleListCallingByAvailable() {
        Mockito.when(this.carRepository.findByAvailable(true))
                .thenReturn(Collections.singletonList(this.car));

        final Optional<CarResponse> actual = this.service.findByCarAvailable().stream().findFirst();

        // Assertions
        Assertions.assertTrue(actual.isPresent());
        Assertions.assertEquals(this.response, actual.get());

        // Verifications
        Mockito.verify(this.carRepository).findByAvailable(true);
        Mockito.verifyNoMoreInteractions(this.carRepository);
    }

    @Override @Test
    void shouldReturnById() {
        Mockito.when(this.carRepository.findById(CAR_ID)).thenReturn(Optional.ofNullable(this.car));

        final CarResponse actual = this.service.getCarById(CAR_ID);

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(this.response, actual);

        // Verifications
        Mockito.verify(this.carRepository).findById(CAR_ID);
        Mockito.verifyNoMoreInteractions(this.carRepository);
    }

    @Override @Test
    void shouldSave() {
        Mockito.when(this.carRepository.save(ArgumentMatchers.any(Car.class))).thenReturn(this.car);
        Mockito.when(this.brandRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(new Brand()));
        Mockito.when(this.categoryRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(new Category()));

        final CarResponse actual = this.service.saveCar(new CarRequest());

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(this.response, actual);

        // Verifications
        Mockito.verify(this.carRepository).save((ArgumentMatchers.any(Car.class)));
        Mockito.verifyNoMoreInteractions(this.carRepository);
    }

    @Override @Test
    void shouldUpdate() {
        Mockito.when(this.carRepository.findById(CAR_ID)).thenReturn(Optional.of(new Car()));
        Mockito.when(this.carRepository.save(ArgumentMatchers.any(Car.class))).thenReturn(this.car);
        Mockito.when(this.brandRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(new Brand()));
        Mockito.when(this.categoryRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(new Category()));

        final CarResponse actual = this.service.updateCar(CAR_ID, new CarRequest());

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(this.response, actual);

        // Verifications
        Mockito.verify(this.carRepository).findById(CAR_ID);
        Mockito.verify(this.carRepository).save(ArgumentMatchers.any(Car.class));
    }

    @Override @Test
    void shouldThrowErros() {
        // Throwing errors for the save method.
        CarRequest brandNotFound = new CarRequest();
        Mockito.when(this.brandRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> this.service.saveCar(brandNotFound));

        CarRequest categoryNotFound = new CarRequest();
        Mockito.when(this.brandRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(new Brand()));
        Mockito.when(this.categoryRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> this.service.saveCar(categoryNotFound));

        CarRequest carNotFound = new CarRequest();
        Mockito.when(this.carRepository.findById(CAR_ID)).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> this.service.updateCar(CAR_ID, carNotFound));

        // Throwing errors for the update method.
        CarRequest carRequest = new CarRequest();
        Mockito.when(this.carRepository.findById(CAR_ID)).thenReturn(Optional.ofNullable(this.car));
        Mockito.when(this.brandRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> this.service.updateCar(CAR_ID, carRequest));

        CarRequest request = new CarRequest();
        Mockito.when(this.carRepository.findById(CAR_ID)).thenReturn(Optional.ofNullable(this.car));
        Mockito.when(this.brandRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(new Brand()));
        Mockito.when(this.categoryRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> this.service.updateCar(CAR_ID, request));
    }
}