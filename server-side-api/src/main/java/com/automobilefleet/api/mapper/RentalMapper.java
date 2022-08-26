package com.automobilefleet.api.mapper;

import com.automobilefleet.api.reponse.RentalResponse;
import com.automobilefleet.api.request.RentalRequest;
import com.automobilefleet.entities.Rental;
import com.automobilefleet.repositories.CarRepository;
import com.automobilefleet.repositories.CostumerRepository;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
public class RentalMapper {
    private static CarRepository carRepository;
    private static CostumerRepository costumerRepository;

    private RentalMapper() { }

    public static Rental toRental(RentalRequest request) {
        Rental rental = new Rental();

        rental.setCar(carRepository.findById(request.getCarId()).get());
        rental.setCostumer(costumerRepository.findById(request.getCostumerId()).get());
        rental.setStartDate(request.getStartDate());
        rental.setEndDate(request.getEndDate());
        rental.setTotal(request.getTotal());

        return rental;
    }

    public static RentalResponse toRentalResponse(Rental rental) {
        RentalResponse response = new RentalResponse();

        response.setId(rental.getId());
        response.setCar(CarMapper.carSummary(rental.getCar()));
        response.setCostumer(CostumerMapper.costumerSummary(rental.getCostumer()));
        response.setStartDate(rental.getStartDate());
        response.setEndDate(rental.getEndDate());
        response.setTotal(rental.getTotal());
        response.setCreatedAt(rental.getCreatedAt());
        response.setUpdateAt(rental.getUpdateAt());

        return response;
    }

    public static List<RentalResponse> toRentalResponseList(List<Rental> rentals) {
        List<RentalResponse> response = new ArrayList<>();
        rentals.forEach(costumer -> response.add(toRentalResponse(costumer)));

        return response;
    }

    public static void updateRental(Rental rental, RentalRequest request) {
        rental.setCar(carRepository.findById(request.getCarId()).get());
        rental.setCostumer(costumerRepository.findById(request.getCostumerId()).get());
        rental.setStartDate(request.getStartDate());
        rental.setEndDate(request.getEndDate());
        rental.setTotal(request.getTotal());
        rental.setUpdateAt(LocalDateTime.now());
    }
}
