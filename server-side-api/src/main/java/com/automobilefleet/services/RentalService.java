package com.automobilefleet.services;

import com.automobilefleet.api.response.RentalResponse;
import com.automobilefleet.api.request.RentalRequest;
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
import com.automobilefleet.util.RentalUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
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
        return this.rentalRepository.findAll().stream()
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


        Rental response = RentalUtils.ofRental(car, costumer, request);
        response = rentalRepository.save(response);

        return this.mapper.map(response, RentalResponse.class);
    }

    public RentalResponse updateRental(UUID id, RentalRequest request) {
        Rental response = this.rentalRepository.findById(id)
                .orElseThrow(RentalNotFoundException::new);

        Car car = this.carRepository.findById(request.getCarId())
                .orElseThrow(CarNotFoundException::new);

        Costumer costumer = this.costumerRepository.findById(request.getCostumerId())
                .orElseThrow(() -> new NotFoundException(ExceptionsConstants.COSTUMER_NOT_FOUND));

        RentalUtils.updateRental(response, request, car, costumer);

        this.rentalRepository.save(response);

        return this.mapper.map(response, RentalResponse.class);
    }

}