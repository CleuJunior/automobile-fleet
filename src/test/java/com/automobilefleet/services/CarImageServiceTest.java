package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.CarImageRequest;
import com.automobilefleet.api.dto.response.CarImageResponse;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.CarImage;
import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.repositories.CarImageRepository;
import com.automobilefleet.repositories.CarRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

class CarImageServiceTest extends ServiceInitialSetup {
    private CarImageService service;
    @Mock
    private CarImageRepository carImageRepository;
    @Mock
    private CarRepository carRepository;
    private CarImage carImage;
    private CarImageRequest request;
    private CarImageResponse response;
    private static final UUID ID = UUID.fromString("4bfad864-59e7-4fc3-b45e-3886694b3717");

    @BeforeEach
    void setupAttributes() {
        this.service = new CarImageService(this.carImageRepository, this.carRepository);
//        this.carImage = Fixture.from(CarImage.class).gimme("car image");
        this.response = new CarImageResponse(this.carImage);
        this.request = new CarImageRequest(this.carImage.getId(), this.carImage.getLinkImage());
    }

    @Override @Test
    void shouldReturnSingleList() {
        Mockito.when(this.carImageRepository.findAll()).thenReturn(Collections.singletonList(this.carImage));

        final Optional<CarImageResponse> actual = this.service.listAllImage().stream().findFirst();

        // Assertions
        Assertions.assertTrue(actual.isPresent());
        Assertions.assertEquals(this.response, actual.get());

        // Check mock interactions
        Mockito.verify(this.carImageRepository).findAll();
        Mockito.verifyNoMoreInteractions(this.carImageRepository);
    }

    @Override @Test
    void shouldReturnById() {
        Mockito.when(this.carImageRepository.findById(ID)).thenReturn(Optional.of(this.carImage));

        final CarImageResponse actual = this.service.getImageById(ID);

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(this.response, actual);

        // Check mock interactions
        Mockito.verify(this.carImageRepository).findById(ID);
        Mockito.verifyNoMoreInteractions(this.carImageRepository);
    }


    @Override @Test
    void shouldSave() {
        Mockito.when(this.carImageRepository.save(ArgumentMatchers.any(CarImage.class))).thenReturn(this.carImage);
        Mockito.when(this.carRepository.findById(this.request.carId())).thenReturn(Optional.of(new Car()));

        final CarImageResponse actual = this.service.saveCarImage(this.request);

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(this.response, actual);

        Mockito.verify(this.carImageRepository).save(ArgumentMatchers.any(CarImage.class));
        Mockito.verify(this.carRepository).findById(this.request.carId());
        Mockito.verifyNoMoreInteractions(this.carImageRepository);
        Mockito.verifyNoMoreInteractions(this.carRepository);
    }

    @Override @Test
    void shouldUpdate() {
        Mockito.when(this.carRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(this.carImage.getCar()));
        Mockito.when(this.carImageRepository.findById(ID)).thenReturn(Optional.of(this.carImage));
        Mockito.when(this.carImageRepository.save(ArgumentMatchers.any(CarImage.class))).thenReturn(this.carImage);

        final CarImageResponse updatedCarImage = this.service.updateCarImage(ID, this.request);

        // Assertions
        Assertions.assertNotNull(updatedCarImage);
        Assertions.assertEquals(this.response, updatedCarImage);

        // Check mock interactions
        Mockito.verify(this.carImageRepository).findById(ID);
        Mockito.verify(this.carRepository).findById(ArgumentMatchers.any());
        Mockito.verify(this.carImageRepository).save(this.carImage);
        Mockito.verifyNoMoreInteractions(this.carImageRepository);
        Mockito.verifyNoMoreInteractions(this.carRepository);
    }

    @Test
    void souldDeleteCarImageById() {
        Mockito.when(this.carImageRepository.findById(ID)).thenReturn(Optional.of(this.carImage));

        this.service.deleteCarImageById(ID);

        // Check mock interactions
        Mockito.verify(this.carImageRepository).findById(ID);
        Mockito.verify(this.carImageRepository).deleteById(ID);
        Mockito.verifyNoMoreInteractions(this.carImageRepository);
    }

    @Override @Test
    void shouldThrowErros() {
        // Mock for non-existing ID
        UUID idNotFound = UUID.randomUUID();
        Mockito.when(this.carImageRepository.findById(idNotFound)).thenReturn(Optional.empty());

        // Assertions for non-existing ID
        CarImageRequest carImage = new CarImageRequest(null, "");

        NotFoundException carImageNotFound =
                Assertions.assertThrows(NotFoundException.class, () -> this.service.getImageById(idNotFound));

        NotFoundException carImageUpdateIdNotFound =
                Assertions.assertThrows(NotFoundException.class, () -> this.service.updateCarImage(idNotFound, carImage));

        NotFoundException carImageDeleteIdNotFound =
                Assertions.assertThrows(NotFoundException.class, () -> this.service.deleteCarImageById(idNotFound));

        Assertions.assertEquals(ExceptionsConstants.IMAGE_NOT_FOUND, carImageNotFound.getMessage());
        Assertions.assertEquals(ExceptionsConstants.IMAGE_NOT_FOUND, carImageUpdateIdNotFound.getMessage());
        Assertions.assertEquals(ExceptionsConstants.IMAGE_NOT_FOUND, carImageDeleteIdNotFound.getMessage());

        // Mock for existing ID
        Mockito.when(this.carImageRepository.findById(ID)).thenReturn(Optional.ofNullable(this.carImage));

        // Setting car to null for Optional empty.
        this.carImage.setCar(null);

        // Assertions for existing ID
        CarImageRequest carImageRequestExistingId = new CarImageRequest(ID, "link");

        NotFoundException carNotFoundUpdateError =
                Assertions.assertThrows(NotFoundException.class, () -> this.service.updateCarImage(ID, this.request));

        NotFoundException carNotFoundSaveError =
                Assertions.assertThrows(NotFoundException.class, () -> this.service.saveCarImage(carImageRequestExistingId));

        Assertions.assertEquals(ExceptionsConstants.CAR_NOT_FOUND, carNotFoundUpdateError.getMessage());
        Assertions.assertEquals(ExceptionsConstants.CAR_NOT_FOUND, carNotFoundSaveError.getMessage());

        // Check mock interactions
        Mockito.verify(this.carImageRepository).findById(ID);
        Mockito.verify(this.carImageRepository, Mockito.times(3)).findById(idNotFound);
        Mockito.verifyNoMoreInteractions(this.carImageRepository);
    }
}