package com.automobilefleet.mapper;

import com.automobilefleet.api.dto.request.CustomerRequest;
import com.automobilefleet.api.dto.response.CustomerResponse;
import com.automobilefleet.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static java.time.LocalDateTime.now;
import static org.springframework.data.domain.PageRequest.of;

@Component
public class CustomerMapper {

    public CustomerResponse toCustomerResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getBirthdate(),
                customer.getEmail(),
                customer.getDriverLicense(),
                customer.getAddress(),
                customer.getPhone(),
                customer.getCreatedAt(),
                customer.getUpdatedAt()
        );
    }

    public List<CustomerResponse> toCustomerResponseList(List<Customer> customers) {
        return customers.stream()
                .filter(Objects::nonNull)
                .map(this::toCustomerResponse)
                .toList();
    }

    public Page<CustomerResponse> toCustomerResponsePage(Page<Customer> customers, int page, int size) {
        var total = customers.getTotalElements();
        var response = toCustomerResponseList(customers.getContent());

        return new PageImpl<>(response, of(page, size), total);
    }

    public Customer apply(Customer current, CustomerRequest customerUpdate) {
        current.setName(customerUpdate.name());
        current.setBirthdate(customerUpdate.birthdate());
        current.setEmail(customerUpdate.email());
        current.setDriverLicense(customerUpdate.driverLicense());
        current.setAddress(customerUpdate.address());
        current.setPhone(customerUpdate.phone());

        return current;
    }
}
