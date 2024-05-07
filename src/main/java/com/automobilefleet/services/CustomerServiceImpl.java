package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.CustomerRequest;
import com.automobilefleet.api.dto.response.CustomerResponse;
import com.automobilefleet.entities.Customer;
import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.mapper.CustomerMapper;
import com.automobilefleet.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.time.LocalDateTime.now;
import static java.util.Collections.emptyList;
import static org.springframework.data.domain.Page.empty;
import static org.springframework.data.domain.PageRequest.of;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    @Override
    public List<CustomerResponse> listCustomer() {
        var customerList = repository.findAll();

        if (customerList.isEmpty()) {
            log.info("Empty list of customers");
            return emptyList();
        }

        log.info("Return list of customers");
        return mapper.toCustomerResponseList(customerList);
    }

    @Override
    public Page<CustomerResponse> pageCustomer(int page, int size) {
        var customerList = repository.findAll(of(page, size));

        if (customerList.isEmpty()) {
            log.info("Empty page of customers");
            return empty();
        }

        log.info("Return page of customer with size {}", size);
        return mapper.toCustomerResponsePage(customerList, page, size);
    }

    @Override
    public CustomerResponse getCustomerById(UUID id) {
        var customer = findCustomerOrThrow(id);

        log.info("Customer id {} found successfully!", id);
        return mapper.toCustomerResponse(customer);
    }

    @Override
    public CustomerResponse saveCustomer(CustomerRequest request) {
        var customer = Customer.builder()
                .name(request.name())
                .birthdate(request.birthdate())
                .email(request.email())
                .driverLicense(request.driverLicense())
                .address(request.address())
                .phone(request.phone())
                .build();

        log.info("Customer saved successfully");
        return mapper.toCustomerResponse(repository.save(customer));
    }

    @Override
    public CustomerResponse updateCustomer(UUID id, CustomerRequest request) {
        var customer = findCustomerOrThrow(id);

        updateCustomer(customer, request);

        log.info("Customer updated successfully");
        return mapper.toCustomerResponse(repository.save(customer));
    }

    private Customer findCustomerOrThrow(UUID id) {
        var response = repository.findById(id);

        if (response.isEmpty()) {
            log.error("Customer id {} not found", id);
            throw new NotFoundException(ExceptionsConstants.COSTUMER_NOT_FOUND);
        }

        return response.get();
    }

    private void updateCustomer(Customer customer, CustomerRequest request) {
        customer.setName(request.name());
        customer.setBirthdate(request.birthdate());
        customer.setEmail(request.email());
        customer.setDriverLicense(request.driverLicense());
        customer.setAddress(request.address());
        customer.setPhone(request.phone());
        customer.setUpdatedAt(now());
    }
}