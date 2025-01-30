package com.automobilefleet.services;

import com.automobilefleet.api.dto.projections.RentalInfo;
import com.automobilefleet.api.dto.request.RentalRequest;
import com.automobilefleet.api.dto.response.RentalResponse;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.Rental;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.exceptions.policyexception.PolicyException;
import com.automobilefleet.mapper.RentalMapper;
import com.automobilefleet.repositories.CarRepository;
import com.automobilefleet.repositories.CustomerRepository;
import com.automobilefleet.repositories.RentalRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static java.lang.Math.round;
import static java.time.temporal.ChronoUnit.DAYS;

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
            return Collections.emptyList();
        }

        log.info("Return list of rentals");
        return mapper.toRentalResponseList(rentalList);
    }

    @Override
    public RentalResponse getRentalById(UUID id) {
        log.info("Rental id {}", id);
        return rentalRepository.findById(id)
                .map(mapper::toRentalResponse)
                .orElseThrow(() -> new NotFoundException("rental.not.found", id));
    }

    @Override
    public RentalInfo findRentalInfoById(UUID id) {
        log.info("Rental info by id: {}", id);
        return rentalRepository.findRentalInfoById(id)
                .orElseThrow(() -> new NotFoundException("rental.not.found", id));
    }

    @Override
    public RentalResponse saveRental(RentalRequest request) {
        var car = carRepository.findById(request.carId())
                .orElseThrow(() -> new NotFoundException("car.not.found", request.carId()));

        var customer = customerRepository.findById(request.customerId())
                .orElseThrow(() -> new NotFoundException("customer.not.found", request.customerId()));

        var total = this.totalRental(request, car);

        verifyCarViability(car);
        car.setAvailable(false);

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
        var rental = rentalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("rental.not.found", id));

        validationRentalModify(rental);

        var car = carRepository.findById(request.carId())
                .orElseThrow(() -> new NotFoundException("car.not.found", request.carId()));

        var customer = customerRepository.findById(request.customerId())
                .orElseThrow(() -> new NotFoundException("customer.not.found", request.customerId()));

        verifyCarViability(car);

        var total = this.totalRental(request, car);

        if (!car.equals(rental.getCar())) {
            rental.getCar().setAvailable(true);
            carRepository.save(rental.getCar());
        }

        car.setAvailable(false);
        rental.setCar(car);
        rental.setCustomer(customer);
        rental.setStartDate(request.startDate());
        rental.setEndDate(request.endDate());
        rental.setTotal(total);

        log.info("Rental updated successfully");
        return mapper.toRentalResponse(rentalRepository.save(rental));
    }

    @Override
    public void deleteRental(UUID id) {
        var rental = rentalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("rental.not.found", id));

        validationRentalModify(rental);

        log.info("Rental id {} deleted successfully", id);
        rentalRepository.delete(rental);
    }

    private void validationRentalModify(Rental rental) {
        if (rental.getStartDate().equals(LocalDate.now())) {
            log.error("Rental id {} cannot be modify", rental.getId());
            throw new PolicyException("rental.cancellation.policy.error");
        }
    }

    private void verifyCarViability(Car car) {
        if (!car.isAvailable()) {
            log.error("Car it's not available");
            throw new PolicyException("car.available.policy.error");
        }
    }

    private double totalRental(RentalRequest request, Car car) {
        var days = DAYS.between(request.startDate(), request.endDate());
        var total = car.getDailyRate() * days;

        return round(total * 100.0) / 100.0;
    }
}