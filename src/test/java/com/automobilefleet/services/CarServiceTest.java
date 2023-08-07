package com.automobilefleet.services;

import br.com.six2six.fixturefactory.Fixture;
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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

class CarServiceTest extends ServiceInitialSetup<Car, CarResponse> {
    @InjectMocks
    private CarService service;
    @Mock
    private CarRepository carRepository;
    @Mock
    private BrandRepository brandRepository;
    @Mock
    private CategoryRepository categoryRepository;
    private static final UUID ID = UUID.fromString("4f2e3bc7-8522-4543-922c-03480d044e62");

    @BeforeEach
    void setupAttributes() {
        super.entity = Fixture.from(Car.class).gimme("car");
        super.response = Fixture.from(CarResponse.class).gimme("response");
    }

    @Override @Test
    public void shouldReturnSingleList() {
        Mockito.when(this.mapper.map(this.entity, CarResponse.class)).thenReturn(super.response);
        Mockito.when(this.carRepository.findAll()).thenReturn(Collections.singletonList(super.entity));

        final CarResponse actual = this.service.listOfCars()
                .stream()
                .findFirst()
                .orElse(null);

        // Assertions
        this.basicAssertions(actual);

        // Verifications
        Mockito.verify(this.carRepository).findAll();
        Mockito.verifyNoMoreInteractions(this.carRepository);
    }

    @Override @Test
    public void shouldReturnById() {
        Mockito.when(this.mapper.map(this.entity, CarResponse.class)).thenReturn(super.response);
        Mockito.when(this.carRepository.findById(ID)).thenReturn(Optional.ofNullable(super.entity));

        final CarResponse actual = this.service.getCarById(ID);

        // Assertions
        this.basicAssertions(actual);

        // Verifications
        Mockito.verify(this.carRepository).findById(ID);
        Mockito.verifyNoMoreInteractions(this.carRepository);
    }

    @Override @Test
    public void shouldSave() {
        Mockito.when(this.mapper.map(this.entity, CarResponse.class)).thenReturn(super.response);
        Mockito.when(this.carRepository.save(ArgumentMatchers.any(Car.class))).thenReturn(super.entity);
        Mockito.when(this.brandRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(new Brand()));
        Mockito.when(this.categoryRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(new Category()));

        final CarResponse actual = this.service.saveCar(new CarRequest());

        // Assertions
        this.basicAssertions(actual);

        // Verifications
        Mockito.verify(this.carRepository).save((ArgumentMatchers.any(Car.class)));
        Mockito.verifyNoMoreInteractions(this.carRepository);
    }

    @Test
    void shouldThrowExpecitonsWhenBrandNotFoundWhenCallingSave() {
        CarRequest carRequest = new CarRequest();
        Mockito.when(this.brandRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> this.service.saveCar(carRequest));
    }

    @Test
    void shouldThrowExpecitonsWhenCategoryNotFoundWhenCallingSave() {
        CarRequest carRequest = new CarRequest();
        Mockito.when(this.brandRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(new Brand()));
        Mockito.when(this.categoryRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> this.service.saveCar(carRequest));
    }

    @Override @Test
    public void shouldUpdate() {
        Mockito.when(this.mapper.map(this.entity, CarResponse.class)).thenReturn(super.response);
        Mockito.when(this.carRepository.findById(ID)).thenReturn(Optional.ofNullable(super.entity));
        Mockito.when(this.carRepository.save(ArgumentMatchers.any(Car.class))).thenReturn(super.entity);
        Mockito.when(this.brandRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(new Brand()));
        Mockito.when(this.categoryRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(new Category()));

        final CarResponse actual = this.service.updateCar(ID, new CarRequest());

        // Assertions
        this.basicAssertions(actual);

        // Verifications
        Mockito.verify(this.carRepository).findById(ID);
        Mockito.verify(this.carRepository).save(ArgumentMatchers.any(Car.class));
    }

    @Test
    void shouldThrowExpecitonsWhenCarNotFoundWhenCallingUpdate() {
        CarRequest carRequest = new CarRequest();
        Mockito.when(this.carRepository.findById(ID)).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> this.service.updateCar(ID, carRequest));
    }

    @Test
    void shouldThrowExpecitonsWhenBrandNotFoundWhenCallingUpdate() {
        CarRequest carRequest = new CarRequest();
        Mockito.when(this.carRepository.findById(ID)).thenReturn(Optional.ofNullable(super.entity));
        Mockito.when(this.brandRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> this.service.updateCar(ID, carRequest));
    }

    @Test
    void shouldThrowExpecitonsWhenCategoryNotFoundWhenCallingUpdate() {
        CarRequest carRequest = new CarRequest();
        Mockito.when(this.carRepository.findById(ID)).thenReturn(Optional.ofNullable(super.entity));
        Mockito.when(this.brandRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(new Brand()));
        Mockito.when(this.categoryRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> this.service.updateCar(ID, carRequest));
    }

//
//    @Test
//    void shouldThrowExpecitonsWhenCategoryNotFoundWhenCallingSave() {
//        CarRequest carRequest = new CarRequest();
//        Mockito.when(this.brandRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(new Brand()));
//        Mockito.when(this.categoryRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());
//        Assertions.assertThrows(NotFoundException.class, () -> this.service.saveCar(carRequest));
//    }

    private void basicAssertions(CarResponse actual) {
        final UUID id = UUID.fromString("4f2e3bc7-8522-4543-922c-03480d044e62");
        final String name = "488";
        final String description = "Esportivo da Ferrari";
        final Double dailyRate = 1_500.0;
        final Boolean available = false;
        final String licensePlate = "LMN-3456";
        final String color = "Vermelho";
        final LocalDateTime createdAt = LocalDateTime.of(2017, 3, 12, 22, 28, 12);

        Assertions.assertNotNull(actual);
        Assertions.assertNotNull(actual.getBrand());
        Assertions.assertNotNull(actual.getCategory());
        Assertions.assertEquals(id, actual.getId());
        Assertions.assertEquals(name, actual.getName());
        Assertions.assertEquals(description, actual.getDescription());
        Assertions.assertEquals(dailyRate, actual.getDailyRate());
        Assertions.assertEquals(available, actual.isAvailable());
        Assertions.assertEquals(licensePlate, actual.getLicensePlate());
        Assertions.assertEquals(color, actual.getColor());
        Assertions.assertEquals(createdAt, actual.getCreatedAt());
    }
}