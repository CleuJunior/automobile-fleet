package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.RentalRequest;
import com.automobilefleet.api.dto.response.RentalResponse;

import java.util.List;
import java.util.UUID;

public interface RentalService {


    List<RentalResponse> listOfRental();

    RentalResponse getRentalById(UUID id);

    RentalResponse saveRental(RentalRequest request);

    RentalResponse updateRental(UUID id, RentalRequest request);

    void deleteRental(UUID id);
}