package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.CarRequest;
import com.automobilefleet.api.dto.response.CarResponse;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.Category;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
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
    private Car car;
    private CarResponse response;
    private static final UUID CAR_ID = UUID.fromString("4f2e3bc7-8522-4543-922c-03480d044e62");
    private static final String BRAND_NAME = "BMW";

    @BeforeEach
    void setupAttributes() {
        this.service = new CarService(this.carRepository, this.brandRepository, this.categoryRepository);
//        this.car = Fixture.from(Car.class).gimme("car");
        this.response = new CarResponse(this.car);
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

        CarRequest carRequest = new CarRequest(
                "",
                "",
                null,
                false,
                "",
                null,
                null,
                ""
        );

        final CarResponse actual = this.service.saveCar(carRequest);

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

        CarRequest carRequest = new CarRequest(
                "",
                "",
                null,
                false,
                "",
                null,
                null,
                ""
        );

        final CarResponse actual = this.service.updateCar(CAR_ID, carRequest);

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
        CarRequest brandNotFound = new CarRequest(
                "",
                "",
                null,
                false,
                "",
                null,
                null,
                ""
        );

        Mockito.when(this.brandRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> this.service.saveCar(brandNotFound));

        CarRequest categoryNotFound = new CarRequest(
                "",
                "",
                null,
                false,
                "",
                null,
                null,
                ""
        );

        Mockito.when(this.brandRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(new Brand()));
        Mockito.when(this.categoryRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> this.service.saveCar(categoryNotFound));

        CarRequest carNotFound = new CarRequest(
                "",
                "",
                null,
                false,
                "",
                null,
                null,
                ""
        );

        Mockito.when(this.carRepository.findById(CAR_ID)).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> this.service.updateCar(CAR_ID, carNotFound));

        // Throwing errors for the update method.
        CarRequest carRequest = new CarRequest(
                "",
                "",
                null,
                false,
                "",
                null,
                null,
                ""
        );

        Mockito.when(this.carRepository.findById(CAR_ID)).thenReturn(Optional.ofNullable(this.car));
        Mockito.when(this.brandRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> this.service.updateCar(CAR_ID, carRequest));

        CarRequest request = new CarRequest(
                "",
                "",
                null,
                false,
                "",
                null,
                null,
                ""
        );

        Mockito.when(this.carRepository.findById(CAR_ID)).thenReturn(Optional.ofNullable(this.car));
        Mockito.when(this.brandRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(new Brand()));
        Mockito.when(this.categoryRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> this.service.updateCar(CAR_ID, request));
    }
}