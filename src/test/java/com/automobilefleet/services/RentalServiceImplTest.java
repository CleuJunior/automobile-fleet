package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.RentalRequest;
import com.automobilefleet.api.dto.response.RentalResponse;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.Customer;
import com.automobilefleet.entities.Rental;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.exceptions.policyexception.PolicyException;
import com.automobilefleet.mapper.RentalMapper;
import com.automobilefleet.repositories.CarRepository;
import com.automobilefleet.repositories.CustomerRepository;
import com.automobilefleet.repositories.RentalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static java.time.LocalDate.now;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.Optional.empty;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class RentalServiceImplTest {

    @Mock
    private RentalRepository rentalRepository;
    @Mock
    private CarRepository carRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private Customer customer;
    @Mock
    private Car car;
    @Mock
    private Rental rental;
    @Mock
    private RentalResponse response;
    @Mock
    private RentalRequest request;
    @Mock
    private RentalMapper mapper;

    @InjectMocks
    private RentalServiceImpl service;

    private static final UUID RENTAL_ID = randomUUID();
    private static final UUID CAR_ID = randomUUID();
    private static final UUID CUSTOMER_ID = randomUUID();

    @Test
    void shouldReturnSingleListRental() {
        var rentalList = singletonList(rental);

        given(rentalRepository.findAll()).willReturn(rentalList);
        given(mapper.toRentalResponseList(rentalList)).willReturn(singletonList(response));

        var result = service.listOfRental();

        then(result).isNotEmpty();
        then(result).contains(response);

        verify(rentalRepository).findAll();
        verify(mapper).toRentalResponseList(rentalList);
        verifyNoMoreInteractions(rentalRepository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void shouldReturnEmptyListRental() {
        given(rentalRepository.findAll()).willReturn(emptyList());

        var result = service.listOfRental();

        then(result).isEmpty();

        verify(rentalRepository).findAll();
        verifyNoMoreInteractions(rentalRepository);
        verifyNoInteractions(mapper);
    }

    @Test
    void shouldReturnRentalById() {
        given(rentalRepository.findById(RENTAL_ID)).willReturn(Optional.of(rental));
        given(mapper.toRentalResponse(rental)).willReturn(response);

        var result = service.getRentalById(RENTAL_ID);

        then(result).isNotNull();
        then(result).isEqualTo(response);

        verify(rentalRepository).findById(RENTAL_ID);
        verify(mapper).toRentalResponse(rental);
        verifyNoMoreInteractions(rentalRepository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void shouldThrowErrorWhenRentalIdNotFound() {
        given(rentalRepository.findById(RENTAL_ID)).willReturn(empty());

        assertThrows(NotFoundException.class, () -> service.getRentalById(RENTAL_ID));

        verify(rentalRepository).findById(RENTAL_ID);
        verifyNoInteractions(mapper);
        verifyNoMoreInteractions(rentalRepository);
    }

    @Test
    void shouldSaveRental() {
        given(request.carId()).willReturn(CAR_ID);
        given(request.customerId()).willReturn(CUSTOMER_ID);
        given(request.startDate()).willReturn(now());
        given(request.endDate()).willReturn(now());

        given(carRepository.findById(CAR_ID)).willReturn(Optional.of(car));
        given(customerRepository.findById(CUSTOMER_ID)).willReturn(Optional.of(customer));
        given(car.isAvailable()).willReturn(true);

        given(rentalRepository.save(any(Rental.class))).willReturn(rental);
        given(mapper.toRentalResponse(rental)).willReturn(response);

        var result = service.saveRental(request);

        then(result).isNotNull();
        then(result).isEqualTo(response);

        verify(rentalRepository).save(any(Rental.class));
        verify(carRepository).findById(CAR_ID);
        verify(customerRepository).findById(CUSTOMER_ID);
        verify(mapper).toRentalResponse(rental);
        verifyNoMoreInteractions(rentalRepository);
        verifyNoMoreInteractions(carRepository);
        verifyNoMoreInteractions(customerRepository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void shoulThrowErrorWhendSaveRentalWithCarIdNonExisting() {
        given(request.carId()).willReturn(CAR_ID);
        given(carRepository.findById(CAR_ID)).willReturn(empty());

        assertThrows(NotFoundException.class, () -> service.saveRental(request));

        verify(carRepository).findById(CAR_ID);
        verifyNoMoreInteractions(carRepository);
        verifyNoInteractions(rentalRepository);
        verifyNoInteractions(customerRepository);
        verifyNoInteractions(mapper);
    }


    @Test
    void shoulThrowErrorWhendSaveRentalWithCustomerIdNonExisting() {
        given(request.carId()).willReturn(CAR_ID);
        given(request.customerId()).willReturn(CUSTOMER_ID);

        given(carRepository.findById(CAR_ID)).willReturn(Optional.of(car));
        given(customerRepository.findById(CUSTOMER_ID)).willReturn(empty());

        assertThrows(NotFoundException.class, () -> service.saveRental(request));

        verify(carRepository).findById(CAR_ID);
        verify(customerRepository).findById(CUSTOMER_ID);
        verifyNoMoreInteractions(carRepository);
        verifyNoMoreInteractions(customerRepository);
        verifyNoInteractions(rentalRepository);
        verifyNoInteractions(mapper);
    }

    @Test
    void shouldUpdateRental() {
        given(rentalRepository.findById(RENTAL_ID)).willReturn(Optional.of(rental));

        given(request.carId()).willReturn(CAR_ID);
        given(request.customerId()).willReturn(CUSTOMER_ID);

        given(request.startDate()).willReturn(now());
        given(request.endDate()).willReturn(now());

        given(carRepository.findById(CAR_ID)).willReturn(Optional.of(car));
        given(customerRepository.findById(CUSTOMER_ID)).willReturn(Optional.of(customer));

//        given(car.isAvailable()).willReturn(true);
        given(rental.getCar()).willReturn(car);
        given(rental.getStartDate()).willReturn(now().plusDays(1));

        given(rentalRepository.save(rental)).willReturn(rental);
        given(mapper.toRentalResponse(rental)).willReturn(response);

        var result = service.updateRental(RENTAL_ID, request);

        then(result).isNotNull();
        then(result).isEqualTo(response);

        verify(rentalRepository).findById(RENTAL_ID);
        verify(rentalRepository).save(rental);
        verify(carRepository).findById(CAR_ID);
        verify(customerRepository).findById(CUSTOMER_ID);
        verify(mapper).toRentalResponse(rental);
        verifyNoMoreInteractions(rentalRepository);
        verifyNoMoreInteractions(carRepository);
        verifyNoMoreInteractions(customerRepository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void shouldDeleteRental() {
        given(rentalRepository.findById(RENTAL_ID)).willReturn(Optional.of(rental));
        given(rental.getStartDate()).willReturn(now().plusDays(1));
        willDoNothing().given(rentalRepository).delete(rental);

        service.deleteRental(RENTAL_ID);

        verify(rentalRepository).findById(RENTAL_ID);
        verify(rentalRepository).delete(rental);
    }

    @Test
    void shouldThrowPolicyErrorWhenDeleteRental() {
        given(rentalRepository.findById(RENTAL_ID)).willReturn(Optional.of(rental));
        given(rental.getId()).willReturn(RENTAL_ID);
        given(rental.getStartDate()).willReturn(now());

        assertThrows(PolicyException.class, () -> service.deleteRental(RENTAL_ID));

        verify(rentalRepository).findById(RENTAL_ID);
        verify(rentalRepository, times(0)).delete(rental);
    }
}