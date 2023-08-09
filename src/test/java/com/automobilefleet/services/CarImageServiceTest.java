package com.automobilefleet.services;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.automobilefleet.api.dto.request.CarImageRequest;
import com.automobilefleet.api.dto.response.BrandResponse;
import com.automobilefleet.api.dto.response.CarImageResponse;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.CarImage;
import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.repositories.CarImageRepository;
import com.automobilefleet.repositories.CarRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class CarImageServiceTest {
    @InjectMocks
    private CarImageService service;
    @Mock
    private CarImageRepository repository;
    @Mock
    private CarRepository carRepository;
    @Mock
    private ModelMapper mapper;
    private CarImage carImage;
    private CarImageRequest request;
    private CarImageResponse response;
    private static final UUID ID = UUID.fromString("4bfad864-59e7-4fc3-b45e-3886694b3717");

    @BeforeAll
    static void setup() {
        FixtureFactoryLoader.loadTemplates("com.automobilefleet.utils");
    }

    @BeforeEach
    void setupAttributes() {
        this.carImage = Fixture.from(CarImage.class).gimme("car image");
        this.response = Fixture.from(CarImageResponse.class).gimme("response");
        this.request = Fixture.from(CarImageRequest.class).gimme("request");
    }

    @Test
    void shouldReturnSingleListCarImage() {
        Mockito.when(this.mapper.map(this.carImage, CarImageResponse.class)).thenReturn(this.response);
        Mockito.when(this.repository.findAll()).thenReturn(Collections.singletonList(this.carImage));

        final CarImageResponse actual = this.service.listAllImage()
                .stream()
                .findFirst()
                .orElse(null);

        Assertions.assertNotNull(actual);
        Assertions.assertNotNull(actual.getCar());
        Assertions.assertEquals(this.response.getId(), actual.getId());
        Assertions.assertEquals(this.response.getLinkImage(), actual.getLinkImage());
        Assertions.assertEquals(this.response.getCreatedAt(), actual.getCreatedAt());

        Mockito.verify(this.repository).findAll();
        Mockito.verifyNoMoreInteractions(this.repository);
    }

    @Test
    void shouldReturnOneCarImagenById() {
        Mockito.when(this.mapper.map(this.carImage, CarImageResponse.class)).thenReturn(this.response);
        Mockito.when(this.repository.findById(ID)).thenReturn(Optional.of(this.carImage));

        final CarImageResponse actual = this.service.getImageById(ID);

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertDoesNotThrow(() -> new NotFoundException(ExceptionsConstants.SPECIFICATION_NOT_FOUND));
        Assertions.assertInstanceOf(CarImageResponse.class, actual);
        Assertions.assertEquals(this.response.getId(), actual.getId());
        Assertions.assertNotNull(actual.getCar());
        Assertions.assertEquals(this.response.getLinkImage(), actual.getLinkImage());
        Assertions.assertEquals(this.response.getCreatedAt(), actual.getCreatedAt());

        Mockito.verify(this.repository).findById(ID);
        Mockito.verifyNoMoreInteractions(this.repository);
    }


    @Test
    void shouldSaveCarImageWhenCallingSaveCarImage() {
        Mockito.when(this.mapper.map(this.carImage, CarImageResponse.class)).thenReturn(this.response);
        Mockito.when(this.mapper.map(this.request, CarImage.class)).thenReturn(this.carImage);

        Mockito.when(this.repository.save(ArgumentMatchers.any(CarImage.class))).thenReturn(this.carImage);
        Mockito.when(this.carRepository.findById(this.request.getCarId())).thenReturn(Optional.of(new Car()));

        final CarImageResponse actual = this.service.saveCarImage(this.request);

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertNotNull(actual.getCar());
        Assertions.assertInstanceOf(CarImageResponse.class, actual);
        Assertions.assertEquals(this.response.getId(), actual.getId());
        Assertions.assertEquals(this.response.getLinkImage(), actual.getLinkImage());
        Assertions.assertEquals(this.response.getCreatedAt(), actual.getCreatedAt());

        Mockito.verify(this.repository).save(ArgumentMatchers.any(CarImage.class));
        Mockito.verify(this.carRepository).findById(this.request.getCarId());
        Mockito.verifyNoMoreInteractions(this.repository);
        Mockito.verifyNoMoreInteractions(this.carRepository);
    }

    @Test
    void shouldUpdateCarImageWhenCallingUpdate() {
        Mockito.when(this.carRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(new Car()));
        Mockito.when(this.repository.findById(ID)).thenReturn(Optional.of(this.carImage));
        Mockito.when(this.repository.save(ArgumentMatchers.any(CarImage.class))).thenReturn(this.carImage);
        Mockito.when(this.mapper.map(this.carImage, CarImageResponse.class)).thenReturn(this.response);

        final CarImageResponse updatedCarImage = this.service.updateCarImage(ID, this.request);

        // Assertions
        Assertions.assertNotNull(updatedCarImage);
        Assertions.assertEquals(this.response.getId(), updatedCarImage.getId());
        Assertions.assertEquals(this.response.getCar().id(), updatedCarImage.getCar().id());
        Assertions.assertEquals(this.response.getLinkImage(), updatedCarImage.getLinkImage());

        // Check mock interactions
        Mockito.verify(this.repository).findById(ID);
        Mockito.verify(this.carRepository).findById(ArgumentMatchers.any());
        Mockito.verify(this.repository).save(this.carImage);
        Mockito.verifyNoMoreInteractions(this.repository);
        Mockito.verifyNoMoreInteractions(this.carRepository);
    }

    @Test
    void souldDeleteCarImageById() {
        Mockito.when(this.repository.findById(ID)).thenReturn(Optional.of(this.carImage));

        this.service.deleteCarImageById(ID);

        // Check mock interactions
        Mockito.verify(this.repository).findById(ID);
        Mockito.verify(this.repository).deleteById(ID);
        Mockito.verifyNoMoreInteractions(this.repository);
    }

    @Test
    void shouldThrowsNotFoundExceptionWhenIdNonExistingId() {
        // Mock for non-existing ID
        UUID idNotFound = UUID.randomUUID();
        Mockito.when(this.repository.findById(idNotFound)).thenReturn(Optional.empty());

        // Assertions for non-existing ID
        CarImageRequest carImage = new CarImageRequest();

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
        Mockito.when(this.repository.findById(ID)).thenReturn(Optional.ofNullable(this.carImage));

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
        Mockito.verify(this.repository).findById(ID);
        Mockito.verify(this.repository, Mockito.times(3)).findById(idNotFound);
        Mockito.verifyNoMoreInteractions(this.repository);
    }
}