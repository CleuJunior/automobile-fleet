package com.automobilefleet.services;

import com.automobilefleet.api.mapper.CarMapper;
import com.automobilefleet.api.mapper.RentalMapper;
import com.automobilefleet.api.reponse.RentalResponse;
import com.automobilefleet.api.request.RentalRequest;
import com.automobilefleet.entities.Rental;
import com.automobilefleet.repositories.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository repository;

    public List<RentalResponse> listOfRental() {
        List<Rental> rental = repository.findAll();

        return RentalMapper.toRentalResponseList(rental);
    }

    public RentalResponse getRental(Long id) {
        Rental response = repository.findById(id).get();

        return RentalMapper.toRentalResponse(response);
    }

    public RentalResponse saveRental(RentalRequest request) {
        Rental rentalSave = RentalMapper.toRental(request);
        repository.save(rentalSave);

        return RentalMapper.toRentalResponse(rentalSave);
    }

    public RentalResponse updateRental(Long id, RentalRequest request) {
        Rental response = repository.findById(id).get();

        RentalMapper.updateRental(response, request);

        repository.save(response);

        return RentalMapper.toRentalResponse(response);
    }

    public void deleteRental(Long id) {
        repository.deleteById(id);
    }
}