package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.RentalRequest;
import com.automobilefleet.api.dto.response.RentalResponse;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.Costumer;
import com.automobilefleet.entities.Rental;
import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.CarNotFoundException;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.exceptions.notfoundexception.RentalNotFoundException;
import com.automobilefleet.repositories.CarRepository;
import com.automobilefleet.repositories.CostumerRepository;
import com.automobilefleet.repositories.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;
    private final CarRepository carRepository;
    private final CostumerRepository costumerRepository;
    private final ModelMapper mapper;

    public List<RentalResponse> listOfRental() {
        return this.rentalRepository.findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(rental -> this.mapper.map(rental, RentalResponse.class))
                .collect(Collectors.toList());
    }

    public RentalResponse getRentalById(UUID id) {
        Rental response = this.rentalRepository.findById(id)
                .orElseThrow(RentalNotFoundException::new);

        return this.mapper.map(response, RentalResponse.class);
    }

    public RentalResponse saveRental(RentalRequest request) {
        Car car = this.carRepository.findById(request.getCarId())
                .orElseThrow(CarNotFoundException::new);

        Costumer costumer = this.costumerRepository.findById(request.getCostumerId())
                .orElseThrow(() -> new NotFoundException(ExceptionsConstants.COSTUMER_NOT_FOUND));

        double total = this.totalRental(request, car);

        Rental response = Rental.builder()
                .car(car)
                .costumer(costumer)
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .total(total)
                .build();

        return this.mapper.map(this.rentalRepository.save(response), RentalResponse.class);
    }

    public RentalResponse updateRental(UUID id, RentalRequest request) {
        Rental response = this.rentalRepository.findById(id)
                .orElseThrow(RentalNotFoundException::new);

        Car car = this.carRepository.findById(request.getCarId())
                .orElseThrow(CarNotFoundException::new);

        Costumer costumer = this.costumerRepository.findById(request.getCostumerId())
                .orElseThrow(() -> new NotFoundException(ExceptionsConstants.COSTUMER_NOT_FOUND));

        this.updateRental(response, request, car, costumer);
        return this.mapper.map(this.rentalRepository.save(response), RentalResponse.class);
    }

    public void updateRental(Rental rental, RentalRequest request, Car car, Costumer costumer) {
        double total = this.totalRental(request, car);

        rental.setCar(car);
        rental.setCostumer(costumer);
        rental.setStartDate(request.getStartDate());
        rental.setEndDate(request.getEndDate());
        rental.setTotal(total);
    }

    private double totalRental(RentalRequest request, Car car) {
        Long days = ChronoUnit.DAYS.between(request.getStartDate(), request.getEndDate());
        return car.getDailyRate() * days;
    }
}