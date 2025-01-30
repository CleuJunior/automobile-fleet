package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.CustomerRequest;
import com.automobilefleet.api.dto.response.CustomerResponse;
import com.automobilefleet.search.CustomerSearch;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;


public interface CustomerService {

    List<CustomerResponse> listCustomer();

    Page<CustomerResponse> pageCustomer(int page, int size);

    CustomerResponse getCustomerById(UUID id);

    Page<CustomerResponse> searchCustomer(CustomerSearch search);

    CustomerResponse saveCustomer(CustomerRequest request);

    CustomerResponse updateCustomer(UUID id, CustomerRequest request);
}