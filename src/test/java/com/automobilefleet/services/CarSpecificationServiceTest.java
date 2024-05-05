package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.CarSpecificationRequest;
import com.automobilefleet.api.dto.response.CarSpecificationResponse;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.CarSpecification;
import com.automobilefleet.entities.Specification;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
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
    private CarSpecification carSpecification;
    private CarSpecificationResponse response;
    private static final UUID CAR_SPECIFICATION_ID = UUID.fromString("7f9128ae-eea1-478a-a3f6-d8b7d0e8574d");
    private static final UUID CAR_ID = UUID.fromString("55491147-1d2f-455e-9958-1e35f1df5a82");
    private static final UUID SPECIFICATION_ID = UUID.fromString("7eef4f65-96a1-4463-8a7f-3a5df541f310");

    @BeforeEach
    void setupAttributes() {
        this.service = new CarSpecificationService(this.carSpecificationRepository, this.carRepository,
                this.specificationRepository);

//        this.carSpecification = Fixture.from(CarSpecification.class).gimme("car specification");
        this.response = new CarSpecificationResponse(this.carSpecification);
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

    @Override @Test
    void shouldUpdate() {
        Mockito.when(this.carSpecificationRepository.findById(CAR_SPECIFICATION_ID))
                .thenReturn(Optional.ofNullable(this.carSpecification));

        Mockito.when(this.carRepository.findById(CAR_ID)).thenReturn(Optional.of(this.carSpecification.getCar()));

        Mockito.when(this.specificationRepository.findById(SPECIFICATION_ID))
                .thenReturn(Optional.of(this.carSpecification.getSpecification()));

        Mockito.when(this.carSpecificationRepository.save(ArgumentMatchers.any(CarSpecification.class)))
                .thenReturn(this.carSpecification);

        final CarSpecificationResponse actual = this.service.updateCarSpecification(
                CAR_SPECIFICATION_ID,
                new CarSpecificationRequest(CAR_ID, SPECIFICATION_ID)
        );

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(this.response, actual);

        // Verifications
        Mockito.verify(this.carSpecificationRepository).save(ArgumentMatchers.any());
    }

    @Override @Test
    void shouldThrowErros() {
        // Mock error for getCarSpecificationById
        Mockito.when(this.carSpecificationRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> this.service.getCarSpecificationById(UUID.randomUUID()));

        // Mock error for saveCarEspecification
        /* Car error */
        Mockito.when(this.carRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class,
                () -> this.service.saveCarEspecification(new CarSpecificationRequest(UUID.randomUUID(), UUID.randomUUID())));

        /* Specification error */
        Mockito.when(this.carRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(new Car()));
        Mockito.when(this.specificationRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class,
                () -> this.service.saveCarEspecification(new CarSpecificationRequest(UUID.randomUUID(), UUID.randomUUID())));

        // Mock error for updateCarSpecification
        /* Car Specification error */
        Mockito.when(this.carSpecificationRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class,
                () -> this.service.updateCarSpecification(
                        UUID.randomUUID(),
                        new CarSpecificationRequest(UUID.randomUUID(), UUID.randomUUID()))
        );

        /* Car error */
        Mockito.when(this.carSpecificationRepository.findById(CAR_SPECIFICATION_ID))
                .thenReturn(Optional.ofNullable(this.carSpecification));

        UUID carNotFoundId = UUID.randomUUID();

        Mockito.when(this.carRepository.findById(carNotFoundId)).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class,
                () -> this.service.updateCarSpecification(
                        CAR_SPECIFICATION_ID,
                        new CarSpecificationRequest(carNotFoundId, UUID.randomUUID()))
        );

        /* Specification error */
        UUID carExistingId = UUID.randomUUID();

        Mockito.when(this.carRepository.findById(carExistingId)).thenReturn(Optional.of(new Car()));
        Mockito.when(this.specificationRepository.findById(UUID.randomUUID())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class,
                () -> this.service.updateCarSpecification(
                        CAR_SPECIFICATION_ID,
                        new CarSpecificationRequest(carExistingId, UUID.randomUUID()))
        );
    }
}