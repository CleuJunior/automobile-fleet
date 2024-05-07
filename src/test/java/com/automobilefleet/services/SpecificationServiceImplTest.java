package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.SpecificationRequest;
import com.automobilefleet.api.dto.response.SpecificationResponse;
import com.automobilefleet.entities.Specification;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.mapper.SpecificationMapper;
import com.automobilefleet.repositories.SpecificationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

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

@ExtendWith(MockitoExtension.class)
class SpecificationServiceImplTest {

    @Mock
    private SpecificationRepository repository;
    @Mock
    private SpecificationMapper mapper;
    @Mock
    private Specification specification;
    @Mock
    private SpecificationRequest request;
    @Mock
    private SpecificationResponse response;
    @InjectMocks
    private SpecificationServiceImpl service;

    private static final UUID ID = randomUUID();
    private static final PageRequest PAGE_REQUEST = of(0, 1);

    @Test
    void shouldReturnListSpecification() {
        var specifications = singletonList(specification);

        given(repository.findAll()).willReturn(specifications);
        given(mapper.toSpecificationResponseList(specifications)).willReturn(singletonList(response));

        var result = service.listSpecifications();

        then(result).isNotEmpty();
        then(result).contains(response);

        verify(repository).findAll();
        verify(mapper).toSpecificationResponseList(specifications);
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void shouldReturnEmptyListSpecification() {
        given(repository.findAll()).willReturn(emptyList());

        var result = service.listSpecifications();

        then(result).isEmpty();

        verify(repository).findAll();
        verifyNoInteractions(mapper);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void shouldReturnPageSpecification() {
        var pageSpecification = new PageImpl<>(singletonList(specification), PAGE_REQUEST, 1);
        var pageSpecificationResponse = new PageImpl<>(singletonList(response), PAGE_REQUEST, 1);

        given(repository.findAll(PAGE_REQUEST)).willReturn(pageSpecification);
        given(mapper.toSpecificationResponsePage(pageSpecification, 0, 1)).willReturn(pageSpecificationResponse);

        var actual = service.pageSpecification(0, 1);

        then(actual).hasSize(1);
        then(actual).contains(response);

        verify(repository).findAll(PAGE_REQUEST);
        verify(mapper).toSpecificationResponsePage(pageSpecification, 0, 1);
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void shouldReturnEmptyPageSpecification() {
        given(repository.findAll(PAGE_REQUEST)).willReturn(empty());

        var actual = service.pageSpecification(0, 1);

        then(actual).isEmpty();

        verify(repository).findAll(PAGE_REQUEST);
        verifyNoInteractions(mapper);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void shouldReturnSpecificationById() {
        given(repository.findById(ID)).willReturn(Optional.of(specification));
        given(mapper.toSpecificationResponse(specification)).willReturn(response);

        var result = service.getSpecificationById(ID);

        then(result).isNotNull();
        then(result).isEqualTo(response);

        verify(repository).findById(ID);
        verify(mapper).toSpecificationResponse(specification);
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void shouldThrowErrorWhenSpecificationIdNotFound() {
        given(repository.findById(ID)).willReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.getSpecificationById(ID));

        verify(repository).findById(ID);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void shouldSaveSpecification() {
        var saveSpecification = new Specification();

        given(repository.save(saveSpecification)).willReturn(specification);
        given(mapper.toSpecificationResponse(specification)).willReturn(response);

        var result = service.saveSpecification(request);

        then(result).isNotNull();
        then(result).isEqualTo(response);

        verify(repository).save(saveSpecification);
        verify(mapper).toSpecificationResponse(specification);
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void shouldUpdateSpecification() {
        // Config mocks behavior
        given(repository.findById(ID)).willReturn(Optional.of(specification));
        given(repository.save(specification)).willReturn(specification);
        given(mapper.toSpecificationResponse(specification)).willReturn(response);

        // Call the method to be tested
        var result = service.updateSpecification(ID, request);

        // Assertions
        then(result).isNotNull();
        then(result).isEqualTo(response);

        // Check mock interactions
        verify(repository).findById(ID);
        verify(repository).save(specification);
        verify(mapper).toSpecificationResponse(specification);
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void shouldThrowErroWhenUpdateSpecificationWithNonExistingId() {
        given(repository.findById(ID)).willReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.updateSpecification(ID, request));

        verify(repository).findById(ID);
        verifyNoMoreInteractions(repository);
    }
}