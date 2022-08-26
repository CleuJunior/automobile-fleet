package com.automobilefleet.api.mapper;

import com.automobilefleet.api.reponse.RentalResponse;
import com.automobilefleet.api.request.RentalRequest;
import com.automobilefleet.entities.Rental;
import com.automobilefleet.repositories.CarRepository;
import com.automobilefleet.repositories.CostumerRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RentalMapper {
    private static CarRepository carRepository;
    private static CostumerRepository costumerRepository;

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

//    public static List<CostumerResponse> toCostumerResponseList(List<Costumer> costumers) {
//        List<CostumerResponse> response = new ArrayList<>();
//        costumers.forEach(costumer -> response.add(toCostumerResponse(costumer)));
//
//        return response;
//    }
//
//    public static void updateCostumer(Costumer costumer, CostumerRequest request) {
//        costumer.setName(request.getName());
//        costumer.setBirthDate(request.getBirthDate());
//        costumer.setEmail(request.getEmail());
//        costumer.setDriveLicense(request.getDriveLicense());
//        costumer.setAddress(request.getAddress());
//        costumer.setPhone(request.getPhone());
//        costumer.setUpdateAt(LocalDateTime.now());
//    }
}
