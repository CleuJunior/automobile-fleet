package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.CustomerRequest;
import com.automobilefleet.api.dto.response.CustomerResponse;
import com.automobilefleet.entities.Customer;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.mapper.CustomerMapper;
import com.automobilefleet.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.data.domain.Page.empty;
import static org.springframework.data.domain.PageRequest.of;


@ExtendWith(SpringExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository repository;
    @Mock
    private CustomerMapper mapper;
    @Mock
    private Customer customer;
    @Mock
    private CustomerRequest request;
    @Mock
    private CustomerResponse response;
    @InjectMocks
    private CustomerServiceImpl service;

    private static final UUID ID = randomUUID();
    private static final PageRequest PAGE_REQUEST = of(0, 1);

    @Test
    void shouldReturnListCustomer() {
        var listCustomer = singletonList(customer);
        var listCustomerResponse = singletonList(response);

        given(repository.findAll()).willReturn(listCustomer);
        given(mapper.toCustomerResponseList(listCustomer)).willReturn(listCustomerResponse);

        var actual = service.listCustomer();

        then(actual).hasSize(1);
        then(actual).contains(response);

        verify(repository).findAll();
        verify(mapper).toCustomerResponseList(listCustomer);
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void shouldReturnEmptyListCustomer() {
        given(repository.findAll()).willReturn(emptyList());

        var actual = service.listCustomer();

        then(actual).isEmpty();

        verify(repository).findAll();
        verifyNoInteractions(mapper);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void shouldReturnPageCustomer() {
        var pageCustomer = new PageImpl<>(singletonList(customer), PAGE_REQUEST, 1);
        var pageCustomerResponse = new PageImpl<>(singletonList(response), PAGE_REQUEST, 1);

        given(repository.findAll(PAGE_REQUEST)).willReturn(pageCustomer);
        given(mapper.toCustomerResponsePage(pageCustomer, 0, 1)).willReturn(pageCustomerResponse);

        var actual = service.pageCustomer(0, 1);

        then(actual).hasSize(1);
        then(actual).contains(response);

        verify(repository).findAll(PAGE_REQUEST);
        verify(mapper).toCustomerResponsePage(pageCustomer, 0, 1);
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void shouldReturnEmptyPageCustomer() {
        given(repository.findAll(PAGE_REQUEST)).willReturn(empty());

        var actual = service.pageCustomer(0, 1);

        then(actual).isEmpty();

        verify(repository).findAll(PAGE_REQUEST);
        verifyNoInteractions(mapper);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void shouldReturnCustomerById() {
        given(repository.findById(ID)).willReturn(Optional.of(customer));
        given(mapper.toCustomerResponse(customer)).willReturn(response);

        var actual = service.getCustomerById(ID);

        // Assertions
        then(actual).isEqualTo(response);

        verify(repository).findById(ID);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void shouldThrowErrorWhenCustomerIdNotFound() {
        given(repository.findById(ID)).willReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.getCustomerById(ID));

        verify(repository).findById(ID);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void shouldSaveCustomer() {
        var saveCustomer = new Customer();

        given(repository.save(saveCustomer)).willReturn(customer);
        given(mapper.toCustomerResponse(customer)).willReturn(response);

        var actual = service.saveCustomer(request);

        then(actual).isNotNull();
        then(actual).isEqualTo(response);

        verify(repository).save(saveCustomer);
        verify(mapper).toCustomerResponse(customer);
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void shouldUpdateCustomer() {
        given(repository.findById(ID)).willReturn(Optional.of(customer));
        given(repository.save(customer)).willReturn(customer);
        given(mapper.toCustomerResponse(customer)).willReturn(response);

        var actual = service.updateCustomer(ID, request);

        then(actual).isNotNull();
        then(actual).isEqualTo(response);

        verify(repository).findById(ID);
        verify(repository).save(customer);
        verify(mapper).toCustomerResponse(customer);
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void shouldThrowErroWhenUpdateCustomerWithNonExistingId() {
        given(repository.findById(ID)).willReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.updateCustomer(ID, request));

        verify(repository).findById(ID);
        verifyNoMoreInteractions(repository);
    }
}