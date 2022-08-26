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

//    public List<RentalRequest> listOfCars() {
//        List<Rental> rental = repository.findAll();
//
//        return RentalMapper.toRentalResponse(rental);
//    }

    public RentalResponse getRental(Long id) {
        Rental response = repository.findById(id).get();

        return RentalMapper.toRentalResponse(response);
    }
//
//    public CarResponse saveCar(CarRequest request) {
//        Car carSave = CarMapper.toCar(request);
//        repository.save(carSave);
//
//        return CarMapper.toCarResponse(carSave);
//    }
//
//    public CarResponse updateCar(Long id, CarRequest request) {
//        Car response = repository.findById(id).get();
//
//        CarMapper.updateCar(response, request);
//
//        repository.save(response);
//
//        return CarMapper.toCarResponse(response);
//    }
//
//    public void deleteCar(Long id) {
//        repository.deleteById(id);
//    }
}