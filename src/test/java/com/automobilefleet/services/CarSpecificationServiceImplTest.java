package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.CarSpecificationRequest;
import com.automobilefleet.api.dto.response.CarSpecificationResponse;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.CarSpecification;
import com.automobilefleet.entities.Specification;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.mapper.CarSpecificationMapper;
import com.automobilefleet.repositories.CarRepository;
import com.automobilefleet.repositories.CarSpecificationRepository;
import com.automobilefleet.repositories.SpecificationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.Optional.of;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(SpringExtension.class)
class CarSpecificationServiceImplTest {
    @Mock
    private CarSpecificationRepository carSpecificationRepository;
    @Mock
    private CarRepository carRepository;
    @Mock
    private SpecificationRepository specificationRepository;
    @Mock
    private CarSpecification carSpecification;
    @Mock
    private Car car;
    @Mock
    private Specification specification;
    @Mock
    private CarSpecificationRequest request;
    @Mock
    private CarSpecificationResponse response;
    @Mock
    private CarSpecificationMapper mapper;
    @InjectMocks
    private CarSpecificationServiceImpl service;
    private static final UUID CAR_SPECIFICATION_ID = randomUUID();
    private static final UUID CAR_ID = randomUUID();
    private static final UUID SPECIFICATION_ID = randomUUID();

    @Test
    void shouldReturnCarSpecificationsSingleList() {
        given(carSpecificationRepository.findAll()).willReturn(singletonList(carSpecification));
        given(mapper.toCarSpecificationResponseList(singletonList(carSpecification))).willReturn(singletonList(response));

        var actual = service.listCarSpecification();

        then(actual).hasSize(1);
        then(actual).contains(response);

        verify(carSpecificationRepository).findAll();
        verify(mapper).toCarSpecificationResponseList(singletonList(carSpecification));
        verifyNoMoreInteractions(carSpecificationRepository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void shouldReturnEmptyListCarSpecifications() {
        given(carSpecificationRepository.findAll()).willReturn(emptyList());

        var actual = service.listCarSpecification();

        then(actual).isEmpty();

        verify(carSpecificationRepository).findAll();
        verifyNoMoreInteractions(carSpecificationRepository);
        verifyNoInteractions(mapper);
    }

    @Test
    void shouldReturnCarSpecificationById() {
        given(carSpecificationRepository.findById(CAR_SPECIFICATION_ID)).willReturn(of(carSpecification));
        given(mapper.toCarSpecificationResponse(carSpecification)).willReturn(response);

        var actual = service.getCarSpecificationById(CAR_SPECIFICATION_ID);

        then(actual).isNotNull();
        then(actual).isEqualTo(response);

        verify(carSpecificationRepository).findById(CAR_SPECIFICATION_ID);
        verify(mapper).toCarSpecificationResponse(carSpecification);
        verifyNoMoreInteractions(carSpecificationRepository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void shoulThrowErrorWhendReturnCarSpecificationByIdNonExiting() {
        given(carSpecificationRepository.findById(CAR_SPECIFICATION_ID)).willReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.getCarSpecificationById(CAR_SPECIFICATION_ID));

        verify(carSpecificationRepository).findById(CAR_SPECIFICATION_ID);
        verifyNoMoreInteractions(carSpecificationRepository);
        verifyNoInteractions(mapper);
    }

    @Test
    void shouldSaveCarSpecification() {
        given(request.carId()).willReturn(CAR_ID);
        given(carRepository.findById(CAR_ID)).willReturn(Optional.of(car));

        given(request.specificationId()).willReturn(SPECIFICATION_ID);
        given(specificationRepository.findById(SPECIFICATION_ID)).willReturn(Optional.of(specification));

        given(carSpecificationRepository.save(any(CarSpecification.class))).willReturn(carSpecification);
        given(mapper.toCarSpecificationResponse(carSpecification)).willReturn(response);

        var actual = service.saveCarEspecification(request);

        then(actual).isNotNull();
        then(actual).isEqualTo(response);

        verify(carSpecificationRepository).save((any(CarSpecification.class)));
        verify(carRepository).findById(CAR_ID);
        verify(specificationRepository).findById(SPECIFICATION_ID);
        verify(mapper).toCarSpecificationResponse(carSpecification);
        verifyNoMoreInteractions(carSpecificationRepository);
        verifyNoMoreInteractions(carRepository);
        verifyNoMoreInteractions(specificationRepository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void shouldThrowWhenSaveCarSpecificationWithCarIdNonExisting() {
        given(request.carId()).willReturn(CAR_ID);
        given(carRepository.findById(CAR_ID)).willReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.saveCarEspecification(request));

        verify(carRepository).findById(CAR_ID);
        verifyNoMoreInteractions(carRepository);
        verifyNoInteractions(carSpecificationRepository);
        verifyNoInteractions(specificationRepository);
        verifyNoInteractions(mapper);
    }

    @Test
    void shouldThrowWhenSaveCarSpecificationWithSpecificationIdNonExisting() {
        given(request.carId()).willReturn(CAR_ID);
        given(carRepository.findById(CAR_ID)).willReturn(Optional.of(car));

        given(request.specificationId()).willReturn(SPECIFICATION_ID);
        given(specificationRepository.findById(SPECIFICATION_ID)).willReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.saveCarEspecification(request));

        verify(carRepository).findById(CAR_ID);
        verify(specificationRepository).findById(SPECIFICATION_ID);
        verifyNoMoreInteractions(carRepository);
        verifyNoMoreInteractions(specificationRepository);
        verifyNoInteractions(carSpecificationRepository);
        verifyNoInteractions(mapper);
    }

    @Test
    void shouldUpdateCarSpecification() {
        given(carSpecificationRepository.findById(CAR_SPECIFICATION_ID)).willReturn(Optional.of(carSpecification));

        given(request.carId()).willReturn(CAR_ID);
        given(carRepository.findById(CAR_ID)).willReturn(Optional.of(car));

        given(request.specificationId()).willReturn(SPECIFICATION_ID);
        given(specificationRepository.findById(SPECIFICATION_ID)).willReturn(Optional.of(specification));

        given(carSpecificationRepository.save(carSpecification)).willReturn(carSpecification);
        given(mapper.toCarSpecificationResponse(carSpecification)).willReturn(response);

        var actual = service.updateCarSpecification(CAR_SPECIFICATION_ID, request);

        // Assertions
        then(actual).isNotNull();
        then(actual).isEqualTo(response);

        // Verifications
        verify(carSpecificationRepository).findById(CAR_SPECIFICATION_ID);
        verify(carRepository).findById(CAR_ID);
        verify(specificationRepository).findById(SPECIFICATION_ID);
        verify(carSpecificationRepository).save(carSpecification);
        verify(mapper).toCarSpecificationResponse(carSpecification);
        verifyNoMoreInteractions(carSpecificationRepository);
        verifyNoMoreInteractions(carRepository);
        verifyNoMoreInteractions(mapper);
    }
}