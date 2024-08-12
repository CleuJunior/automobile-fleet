package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.CarImageRequest;
import com.automobilefleet.api.dto.response.CarImageResponse;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.CarImage;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.mapper.CarImageMapper;
import com.automobilefleet.repositories.CarImageRepository;
import com.automobilefleet.repositories.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;
import java.util.UUID;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.data.domain.Page.empty;
import static org.springframework.data.domain.PageRequest.of;

@ExtendWith(MockitoExtension.class)
class CarImageServiceImplTest {

    @Mock
    private CarImageRepository carImageRepository;
    @Mock
    private CarRepository carRepository;
    @Mock
    private CarImageMapper mapper;
    @Mock
    private CarImage carImage;
    @Mock
    private Car car;
    @Mock
    private CarImageRequest request;
    @Mock
    private CarImageResponse response;
    @InjectMocks
    private CarImageServiceImpl service;

    private static final UUID ID_IMAGE = UUID.randomUUID();
    private static final UUID ID_CAR = UUID.randomUUID();
    private static final PageRequest PAGE_REQUEST = of(0, 1);

    @Test
    void shouldReturnSingleListOfImages() {
        given(carImageRepository.findAll()).willReturn(singletonList(carImage));
        given(mapper.toCarImageResponseList(singletonList(carImage))).willReturn(singletonList(response));

        var actual = service.listAllImage();

        // Assertions
        then(actual).isNotEmpty();
        then(actual).contains(response);

        // Check mock interactions
        verify(carImageRepository).findAll();
        verify(mapper).toCarImageResponseList(singletonList(carImage));
        verifyNoMoreInteractions(carImageRepository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void shouldReturnEmptyLisImages() {
        given(carImageRepository.findAll()).willReturn(emptyList());

        var result = service.listAllImage();

        then(result).isEmpty();

        verify(carImageRepository).findAll();
        verifyNoInteractions(mapper);
        verifyNoMoreInteractions(carImageRepository);
    }

    @Test
    void shouldReturnPageImages() {
        var carImagePage = new PageImpl<>(singletonList(carImage), PAGE_REQUEST, 1);
        var carImageResponses = new PageImpl<>(singletonList(response), PAGE_REQUEST, 1);

        given(carImageRepository.findAll(PAGE_REQUEST)).willReturn(carImagePage);
        given(mapper.toCarImageResponsePage(carImagePage, 0, 1)).willReturn(carImageResponses);

        var actual = service.pageCarImage(0, 1);

        then(actual).hasSize(1);
        then(actual).contains(response);

        verify(carImageRepository).findAll(PAGE_REQUEST);
        verify(mapper).toCarImageResponsePage(carImagePage, 0, 1);
        verifyNoMoreInteractions(carImageRepository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void shouldReturnEmptyPageImages() {
        given(carImageRepository.findAll(PAGE_REQUEST)).willReturn(empty());

        var actual = service.pageCarImage(0, 1);

        then(actual).isEmpty();

        verify(carImageRepository).findAll(PAGE_REQUEST);
        verifyNoInteractions(mapper);
        verifyNoMoreInteractions(carImageRepository);
    }

    @Test
    void shouldReturnImageById() {
        given(carImageRepository.findById(ID_IMAGE)).willReturn(Optional.of(carImage));
        given(mapper.toCarImageResponse(carImage)).willReturn(response);

        var actual = service.getImageById(ID_IMAGE);

        // Assertions
        then(actual).isNotNull();
        then(actual).isEqualTo(response);

        // Check mock interactions
        verify(carImageRepository).findById(ID_IMAGE);
        verify(mapper).toCarImageResponse(carImage);
        verifyNoMoreInteractions(carImageRepository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void shouldSaveImage() {
        given(carImageRepository.save(any(CarImage.class))).willReturn(carImage);
        given(request.carId()).willReturn(ID_CAR);
        given(carRepository.findById(ID_CAR)).willReturn(Optional.of(car));
        given(mapper.toCarImageResponse(carImage)).willReturn(response);

        var actual = service.saveCarImage(request);

        // Assertions
        then(actual).isNotNull();
        then(actual).isEqualTo(response);

        verify(carImageRepository).save(any(CarImage.class));
        verify(request).carId();
        verify(carRepository).findById(ID_CAR);
        verify(mapper).toCarImageResponse(carImage);
        verifyNoMoreInteractions(carImageRepository);
        verifyNoMoreInteractions(carRepository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void shouldThrowErroWhenSaveImageCarIdNonExist() {
        given(request.carId()).willReturn(ID_CAR);

        assertThrows(NotFoundException.class, () -> service.saveCarImage(request));

        verify(carRepository).findById(ID_CAR);
        verifyNoMoreInteractions(carRepository);
    }

    @Test
    void shouldUpdateImage() {
        given(carImageRepository.findById(ID_IMAGE)).willReturn(Optional.of(carImage));
        given(request.carId()).willReturn(ID_CAR);
        given(carRepository.findById(ID_CAR)).willReturn(Optional.of(car));
        given(carImageRepository.save(any(CarImage.class))).willReturn(carImage);
        given(mapper.apply(carImage, car, request.linkImage())).willReturn(carImage);
        given(mapper.toCarImageResponse(carImage)).willReturn(response);

        var actual = service.updateCarImage(ID_IMAGE, request);

        then(actual).isNotNull();
        then(actual).isEqualTo(response);

        verify(carImageRepository).findById(ID_IMAGE);
        verify(request).carId();
        verify(carRepository).findById(ID_CAR);
        verify(carImageRepository).save(any(CarImage.class));
        verify(mapper).apply(carImage, car, request.linkImage());
        verify(mapper).toCarImageResponse(carImage);
        verifyNoMoreInteractions(carImageRepository);
        verifyNoMoreInteractions(carRepository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void shouldThrowErrorWhenUpdateImageWithCarNonExitingId() {
        given(request.carId()).willReturn(ID_CAR);
        given(carRepository.findById(ID_CAR)).willReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.updateCarImage(ID_IMAGE, request));

        verify(request, times(2)).carId();
        verify(carRepository).findById(ID_CAR);
        verifyNoInteractions(mapper);
    }

    @Test
    void shouldDeleteCarImageById() {
        given(carImageRepository.findById(ID_IMAGE)).willReturn(Optional.of(carImage));
        willDoNothing().given(carImageRepository).delete(carImage);

        service.deleteCarImageById(ID_IMAGE);

        // Check mock interactions
        verify(carImageRepository).findById(ID_IMAGE);
        verify(carImageRepository).delete(carImage);
        verifyNoMoreInteractions(carImageRepository);

    }

    @Test
    void shouldThrowErrorWhenDeleteCarImageByIdNonExist() {
        given(carImageRepository.findById(ID_IMAGE)).willReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.deleteCarImageById(ID_IMAGE));

        // Check mock interactions
        verify(carImageRepository).findById(ID_IMAGE);
        verify(carImageRepository, times(0)).delete(carImage);
        verifyNoMoreInteractions(carImageRepository);
    }
}