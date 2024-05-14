package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.RentalRequest;
import com.automobilefleet.api.dto.response.RentalResponse;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.Customer;
import com.automobilefleet.entities.Rental;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.mapper.RentalMapper;
import com.automobilefleet.repositories.CarRepository;
import com.automobilefleet.repositories.CustomerRepository;
import com.automobilefleet.repositories.RentalRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.automobilefleet.exceptions.ExceptionsConstants.CAR_NOT_FOUND;
import static com.automobilefleet.exceptions.ExceptionsConstants.CUSTOMER_NOT_FOUND;
import static com.automobilefleet.exceptions.ExceptionsConstants.RENTAL_NOT_FOUND;
import static java.lang.Math.round;
import static java.time.LocalDateTime.now;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.Collections.emptyList;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RentalServiceImpl implements RentalService {
    private final RentalRepository rentalRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final RentalMapper mapper;

    @Override
    public List<RentalResponse> listOfRental() {
        var rentalList = rentalRepository.findAll();

        if (rentalList.isEmpty()) {
            log.info("Empty list of rentals");
            return emptyList();
        }

        log.info("Return list of rentals");
        return mapper.toRentalResponseList(rentalList);
    }

    @Override
    public RentalResponse getRentalById(UUID id) {
        var rental = findRentalOrThrow(id);

        log.info("Rental id {} found successfully", id);
        return mapper.toRentalResponse(rental);
    }

    @Override
    public RentalResponse saveRental(RentalRequest request) {
        var car = findCarOrThrow(request.carId());
        var customer = findCustomerOrThrow(request.customerId());
        var total = totalRental(request, car);

        var rental = Rental.builder()
                .car(car)
                .customer(customer)
                .startDate(request.startDate())
                .endDate(request.endDate())
                .total(total)
                .build();

        log.info("Rental saved successfully");
        return mapper.toRentalResponse(rentalRepository.save(rental));
    }

    @Override
    public RentalResponse updateRental(UUID id, RentalRequest request) {
        var rental = findRentalOrThrow(id);
        var car = findCarOrThrow(request.carId());
        var customer = findCustomerOrThrow(request.customerId());

        updateRental(rental, request, car, customer);
        log.info("Rental updated successfully");
        return mapper.toRentalResponse(rentalRepository.save(rental));
    }

    private void updateRental(Rental rental, RentalRequest request, Car car, Customer customer) {
        var total = totalRental(request, car);

        rental.setCar(car);
        rental.setCustomer(customer);
        rental.setStartDate(request.startDate());
        rental.setEndDate(request.endDate());
        rental.setTotal(total);
        rental.setUpdatedAt(now());
    }

    private double totalRental(RentalRequest request, Car car) {
        var days = DAYS.between(request.startDate(), request.endDate());
        var total = car.getDailyRate() * days;

        return round(total * 100.0) / 100.0;
    }

    private Rental findRentalOrThrow(UUID id) {
        var response = rentalRepository.findById(id);

        if (response.isEmpty()) {
            log.error("Rental id {} not found", id);
            throw new NotFoundException(RENTAL_NOT_FOUND);
        }

        return response.get();
    }

    private Car findCarOrThrow(UUID id) {
        var response = carRepository.findById(id);

        if (response.isEmpty()) {
            log.error("Car id {} not found", id);
            throw new NotFoundException(CAR_NOT_FOUND);
        }

        return response.get();
    }

    private Customer findCustomerOrThrow(UUID id) {
        var response = customerRepository.findById(id);

        if (response.isEmpty()) {
            log.error("Customer id {} not found", id);
            throw new NotFoundException(CUSTOMER_NOT_FOUND);
        }

        return response.get();
    }

}