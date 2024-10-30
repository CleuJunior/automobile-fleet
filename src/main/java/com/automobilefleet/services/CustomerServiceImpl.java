package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.CustomerRequest;
import com.automobilefleet.api.dto.response.CustomerResponse;
import com.automobilefleet.entities.Customer;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.mapper.CustomerMapper;
import com.automobilefleet.query.CustomerQuery;
import com.automobilefleet.repositories.CustomerRepository;
import com.automobilefleet.search.CustomerSearch;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.springframework.data.domain.PageRequest.of;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final CustomerQuery query;
    private final CustomerMapper mapper;

    @Override
    public List<CustomerResponse> listCustomer() {
        var customerList = repository.findAll();

        if (customerList.isEmpty()) {
            log.info("Empty list of customers");
            return Collections.emptyList();
        }

        log.info("Return list of customers");
        return mapper.toCustomerResponseList(customerList);
    }

    @Override
    public Page<CustomerResponse> pageCustomer(int page, int size) {
        var customerList = repository.findAll(of(page, size));

        if (customerList.isEmpty()) {
            log.info("Empty page of customers");
            return Page.empty();
        }

        log.info("Return page of customer with size {}", size);
        return mapper.toCustomerResponsePage(customerList, page, size);
    }

    @Override
    public CustomerResponse getCustomerById(UUID id) {
        log.info("Finding customer id: {}", id);
        return repository.findById(id)
                .map(mapper::toCustomerResponse)
                .orElseThrow(() -> new NotFoundException("customer.not.found", id));
    }

    @Override
    public Page<CustomerResponse> searchCustomer(CustomerSearch search) {
        var customers = repository.findAll(query.query(search), search.pageable());
        return mapper.toCustomerResponsePage(customers, search.getPage(), search.getSize());
    }

    @Override
    public CustomerResponse saveCustomer(CustomerRequest request) {
        log.info("Customer saved successfully");
        return mapper.toCustomerResponse(repository.save(new Customer(request)));
    }

    @Override
    public CustomerResponse updateCustomer(UUID id, CustomerRequest request) {
        log.info("Customer id: {}", id);
        return repository.findById(id)
                .map(current -> mapper.apply(current, request))
                .map(repository::save)
                .map(mapper::toCustomerResponse)
                .orElseThrow(() -> new NotFoundException("customer.not.found", id));
    }
}