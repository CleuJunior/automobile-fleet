package com.automobilefleet.mapper;

import com.automobilefleet.api.dto.response.CarResponse;
import com.automobilefleet.api.dto.response.CustomerResponse;
import com.automobilefleet.api.dto.response.RentalResponse;
import com.automobilefleet.api.dto.response.SpecificationResponse;
import com.automobilefleet.entities.Rental;
import com.automobilefleet.entities.Specification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static org.springframework.data.domain.PageRequest.of;

@Component
@AllArgsConstructor
public class RentalMapper {

    private final CarMapper carMapper;
    private final CustomerMapper customerMapper;

    public RentalResponse toRentalResponse(Rental rental) {
        var car= carMapper.toCarResponse(rental.getCar());
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

    public List<RentalResponse> toRenttalResponseList(List<Rental> rentals) {
        return rentals.stream()
                .filter(Objects::nonNull)
                .map(this::toRentalResponse)
                .toList();
    }
}
