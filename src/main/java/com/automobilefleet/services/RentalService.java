package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.RentalRequest;
import com.automobilefleet.api.dto.response.RentalResponse;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.Costumer;
import com.automobilefleet.entities.Rental;
import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.repositories.CarRepository;
import com.automobilefleet.repositories.CostumerRepository;
import com.automobilefleet.repositories.RentalRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;
    private final CarRepository carRepository;
    private final CostumerRepository costumerRepository;

    public List<RentalResponse> listOfRental() {
        return this.rentalRepository.findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(RentalResponse::new)
                .toList();
    }

    public RentalResponse getRentalById(UUID id) {
        Optional<Rental> response = this.rentalRepository.findById(id);

        if (response.isEmpty()) {
            throw new NotFoundException(ExceptionsConstants.RENTAL_NOT_FOUND);
        }

        return new RentalResponse(response.get());
    }

    public RentalResponse saveRental(RentalRequest request) {
        Optional<Car> car = this.carRepository.findById(request.carId());

        if (car.isEmpty()) {
            throw new NotFoundException(ExceptionsConstants.CAR_NOT_FOUND);
        }

        Optional<Costumer> costumer = this.costumerRepository.findById(request.costumerId());

        if (costumer.isEmpty()) {
            throw new NotFoundException(ExceptionsConstants.COSTUMER_NOT_FOUND);
        }

        double total = this.totalRental(request, car.get());

        Rental response = Rental.builder()
                .car(car.get())
                .costumer(costumer.get())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .total(total)
                .build();

        return new RentalResponse(this.rentalRepository.save(response));
    }

    public RentalResponse updateRental(UUID id, RentalRequest request) {
        Optional<Rental> response = this.rentalRepository.findById(id);

        if (response.isEmpty()) {
            throw new NotFoundException(ExceptionsConstants.RENTAL_NOT_FOUND);
        }

        Optional<Car> car = this.carRepository.findById(request.carId());

        if (car.isEmpty()) {
            throw new NotFoundException(ExceptionsConstants.CAR_NOT_FOUND);
        }

        Optional<Costumer> costumer = this.costumerRepository.findById(request.costumerId());

        if (costumer.isEmpty()) {
            throw new NotFoundException(ExceptionsConstants.COSTUMER_NOT_FOUND);
        }

        this.updateRental(response.get(), request, car.get(), costumer.get());
        return new RentalResponse(this.rentalRepository.save(response.get()));
    }

    private void updateRental(Rental rental, RentalRequest request, Car car, Costumer costumer) {
        double total = this.totalRental(request, car);

        rental.setCar(car);
        rental.setCostumer(costumer);
        rental.setStartDate(request.startDate());
        rental.setEndDate(request.endDate());
        rental.setTotal(total);
    }

    private double totalRental(RentalRequest request, Car car) {
        Long days = ChronoUnit.DAYS.between(request.startDate(), request.endDate());
        double total = car.getDailyRate() * days;
        return Math.round(total * 100.0) / 100.0;
    }
}