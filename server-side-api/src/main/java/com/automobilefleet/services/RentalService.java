package com.automobilefleet.services;

import com.automobilefleet.api.reponse.RentalResponse;
import com.automobilefleet.api.request.RentalRequest;
import com.automobilefleet.entities.Rental;
import com.automobilefleet.exceptions.RentalNotFoundException;
import com.automobilefleet.repositories.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository repository;
    private final ModelMapper mapper;

    public List<RentalResponse> listOfRental() {
        return this.repository.findAll().stream()
                .map(rental -> this.mapper.map(rental, RentalResponse.class))
                .collect(Collectors.toList());
    }

    public RentalResponse getRentalById(Long id) {
        Rental response = this.repository.findById(id)
                .orElseThrow(RentalNotFoundException::new);

        return this.mapper.map(response, RentalResponse.class);
    }

    public RentalResponse saveRental(RentalRequest request) {
        Rental response = this.mapper.map(request, Rental.class);
        response = repository.save(response);

        return this.mapper.map(response, RentalResponse.class);
    }

    public RentalResponse updateRental(Long id, RentalRequest request) {
        Rental response = this.repository.findById(id)
                .orElseThrow(RentalNotFoundException::new);

        response.setCar(request.getCar());
        response.setCostumer(request.getCostumer());
        response.setStartDate(request.getStartDate());
        response.setEndDate(request.getEndDate());
        response.setTotal(request.getTotal());
        response.setUpdateAt(LocalDateTime.now());

        this.repository.save(response);

        return this.mapper.map(response, RentalResponse.class);
    }

    public void deleteRental(Long id) {
        Rental rental = this.repository.findById(id)
                .orElseThrow(RentalNotFoundException::new);

        this.repository.delete(rental);
    }
}