package com.automobilefleet.services;

import br.com.six2six.fixturefactory.Fixture;
import com.automobilefleet.api.dto.request.RentalRequest;
import com.automobilefleet.api.dto.response.RentalResponse;
import com.automobilefleet.entities.Rental;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.repositories.CarRepository;
import com.automobilefleet.repositories.CostumerRepository;
import com.automobilefleet.repositories.RentalRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

class RentalServiceTest extends ServiceInitialSetup {
    private RentalService service;
    @Mock
    private RentalRepository rentalRepository;
    @Mock
    private CarRepository carRepository;
    @Mock
    private CostumerRepository costumerRepository;
    private Rental rental;
    private RentalResponse response;
    private RentalRequest request;
    private static final UUID ID = UUID.fromString("fc45949e-bce0-4301-9cea-fe92bfd7dc14");

    @BeforeEach
    void setupAttributes() {
        this.service = new RentalService(this.rentalRepository, this.carRepository, this.costumerRepository);
        this.rental = Fixture.from(Rental.class).gimme("rental");
        this.response = new RentalResponse(this.rental);
        this.request = new RentalRequest(this.rental.getCar().getId(), this.rental.getCostumer().getId(),
                this.rental.getStartDate(), this.rental.getEndDate());
    }


    @Override @Test
    void shouldReturnSingleList() {
        Mockito.when(this.rentalRepository.findAll()).thenReturn(Collections.singletonList(this.rental));

        final Optional<RentalResponse> actual = this.service.listOfRental().stream().findFirst();

        // Assertions
        Assertions.assertTrue(actual.isPresent());
        Assertions.assertEquals(this.response, actual.get());

        // Check mock interactions
        Mockito.verify(this.rentalRepository).findAll();
        Mockito.verifyNoMoreInteractions(this.rentalRepository);
    }

    @Override @Test
    void shouldReturnById() {
        Mockito.when(this.rentalRepository.findById(ID)).thenReturn(Optional.ofNullable(this.rental));

        final RentalResponse actual = this.service.getRentalById(ID);

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(this.response, actual);

        // Check mock interactions
        Mockito.verify(this.rentalRepository).findById(ID);
        Mockito.verifyNoMoreInteractions(this.rentalRepository);
    }

    @Override @Test
    void shouldSave() {
        Mockito.when(this.carRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.ofNullable(this.rental.getCar()));

        Mockito.when(this.costumerRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.ofNullable(this.rental.getCostumer()));

        Mockito.when(this.rentalRepository.save(ArgumentMatchers.any(Rental.class))).thenReturn(this.rental);

        final RentalResponse actual = this.service.saveRental(this.request);

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(this.response, actual);

        // Check mock interactions
        Mockito.verify(this.rentalRepository).save(ArgumentMatchers.any(Rental.class));
        Mockito.verifyNoMoreInteractions(this.rentalRepository);
    }

    @Override @Test
    void shouldUpdate() {
        Mockito.when(this.rentalRepository.findById(ID)).thenReturn(Optional.ofNullable(this.rental));
        Mockito.when(this.carRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.ofNullable(this.rental.getCar()));

        Mockito.when(this.costumerRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.ofNullable(this.rental.getCostumer()));

        Mockito.when(this.rentalRepository.save(ArgumentMatchers.any(Rental.class))).thenReturn(this.rental);

        final RentalResponse actual = this.service.updateRental(ID, this.request);

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(this.response, actual);

        // Check mock interactions
        Mockito.verify(this.rentalRepository).save(ArgumentMatchers.any(Rental.class));
    }

    @Override @Test
    void shouldThrowErros() {
        // Throwing error on getRentalById
        Mockito.when(this.rentalRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> this.service.getRentalById(ID));

        // Throwing error on saveRental
        Mockito.when(this.carRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());
        // Car Not Found
        Assertions.assertThrows(NotFoundException.class, () -> this.service.saveRental(this.request));

        // Costumer Not Found
        Mockito.when(this.carRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.ofNullable(this.rental.getCar()));

        Mockito.when(this.costumerRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> this.service.saveRental(this.request));

        // Rental Not Found on updateRental
        Assertions.assertThrows(NotFoundException.class, () -> this.service.updateRental(ID, this.request));

        Mockito.when(this.rentalRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());

        // Car Not Found on updateRental
        Mockito.when(this.rentalRepository.findById(ID)).thenReturn(Optional.ofNullable(this.rental));
        Mockito.when(this.carRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> this.service.updateRental(ID, this.request));

        // Costumer Not Found on updateRental
        Mockito.when(this.rentalRepository.findById(ID)).thenReturn(Optional.ofNullable(this.rental));
        Mockito.when(this.carRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.ofNullable(this.rental.getCar()));
        Mockito.when(this.costumerRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> this.service.updateRental(ID, this.request));
    }
}