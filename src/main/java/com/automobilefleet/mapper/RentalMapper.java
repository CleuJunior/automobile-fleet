package com.automobilefleet.mapper;

import com.automobilefleet.api.dto.response.RentalResponse;
import com.automobilefleet.entities.Rental;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class RentalMapper {

    private final CarMapper carMapper;
    private final CustomerMapper customerMapper;

    public RentalResponse toRentalResponse(Rental rental) {
        var car = carMapper.toCarResponse(rental.getCar());
        var customer = customerMapper.toCustomerResponse(rental.getCustomer());

        return new RentalResponse(
                rental.getId(),
                car,
                customer,
                rental.getStartDate(),
                rental.getEndDate(),
                rental.getTotal(),
                rental.getCreatedAt(),
                rental.getUpdatedAt()
        );
    }

    public List<RentalResponse> toRentalResponseList(List<Rental> rentals) {
        return rentals.stream()
                .filter(Objects::nonNull)
                .map(this::toRentalResponse)
                .toList();
    }
}
