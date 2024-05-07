package com.automobilefleet.api.controllers;

import com.automobilefleet.api.dto.request.CustomerRequest;
import com.automobilefleet.api.dto.response.CustomerResponse;
import com.automobilefleet.services.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;

import java.util.UUID;

import static java.util.Collections.singletonList;
import static java.util.Objects.requireNonNull;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerServiceImpl service;
    @Mock
    private CustomerResponse response;
    @Mock
    private CustomerRequest request;
    @InjectMocks
    private CustomerController controller;

    private static final UUID ID = randomUUID();

    @Test
    void shouldGetCustomerByIdAndStatusCodeOK() {
        given(service.getCustomerById(ID)).willReturn(response);

        var result = controller.getCustomerById(ID);

        then(result.getStatusCode()).isEqualTo(OK);
        then(result.getBody()).isEqualTo(response);

        verify(service).getCustomerById(ID);
        verifyNoMoreInteractions(service);
    }

    @Test
    void shouldGetListCustomerAndStatusCodeOK() {
        given(service.listCustomer()).willReturn(singletonList(response));

        var result = controller.getListCustomer();

        then(result.getStatusCode()).isEqualTo(OK);
        then(result.getBody()).isEqualTo(singletonList(response));

        verify(service).listCustomer();
        verifyNoMoreInteractions(service);
    }

    @Test
    void shouldGetPageCustomerAndStatusCodeOK() {
        var page = new PageImpl<>(singletonList(response), of(0, 1), 1);

        given(service.pageCustomer(0, 1)).willReturn(page);

        var result = controller.pageCustomer(0, 1);

        then(result.getStatusCode()).isEqualTo(OK);
        then(requireNonNull(result.getBody()).getContent()).isEqualTo(singletonList(response));

        verify(service).pageCustomer(0, 1);
        verifyNoMoreInteractions(service);
    }

    @Test
    void shoulSaveCustomerAndStatusCodeCreated() {
        given(service.saveCustomer(request)).willReturn(response);

        var result = controller.saveCustomer(request);

        then(result.getStatusCode()).isEqualTo(CREATED);
        then(result.getBody()).isEqualTo(response);

        verify(service).saveCustomer(request);
        verifyNoMoreInteractions(service);
    }

    @Test
    void shouldUpdateCustomerAndStatusCodeAccepted() {
        given(service.updateCustomer(ID, request)).willReturn(response);

        var result = controller.updateCustomer(ID, request);

        then(result.getStatusCode()).isEqualTo(ACCEPTED);
        then(result.getBody()).isEqualTo(response);

        verify(service).updateCustomer(ID, request);
        verifyNoMoreInteractions(service);
    }
}