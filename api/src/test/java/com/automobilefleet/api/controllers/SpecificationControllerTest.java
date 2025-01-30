package com.automobilefleet.api.controllers;

import com.automobilefleet.api.dto.request.SpecificationRequest;
import com.automobilefleet.api.dto.response.SpecificationResponse;
import com.automobilefleet.services.SpecificationServiceImpl;
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
class SpecificationControllerTest {

    @Mock
    private SpecificationServiceImpl service;
    @Mock
    private SpecificationResponse response;
    @Mock
    private SpecificationRequest request;
    @InjectMocks
    private SpecificationController controller;

    private static final UUID ID = randomUUID();

    @Test
    void shouldGetSpecificationByIdAndStatusCodeOK() {
        given(service.getSpecificationById(ID)).willReturn(response);

        var result = controller.getSpecificationById(ID);

        then(result.getStatusCode()).isEqualTo(OK);
        then(result.getBody()).isEqualTo(response);

        verify(service).getSpecificationById(ID);
        verifyNoMoreInteractions(service);
    }

    @Test
    void shouldGetListSpecificationAndStatusCodeOK() {
        given(service.listSpecifications()).willReturn(singletonList(response));

        var result = controller.listOfSpecifications();

        then(result.getStatusCode()).isEqualTo(OK);
        then(result.getBody()).isEqualTo(singletonList(response));

        verify(service).listSpecifications();
        verifyNoMoreInteractions(service);
    }

    @Test
    void shouldGetPageSpecificationAndStatusCodeOK() {
        var listSpecifications = singletonList(response);
        var pageSpecifications = new PageImpl<>(listSpecifications, of(0, 1), 1);

        given(service.pageSpecification(0, 1)).willReturn(pageSpecifications);

        var result = controller.pageSpecification(0, 1);

        then(result.getStatusCode()).isEqualTo(OK);
        then(requireNonNull(result.getBody()).getContent()).isEqualTo(listSpecifications);

        verify(service).pageSpecification(0, 1);
        verifyNoMoreInteractions(service);
    }

    @Test
    void shoulSaveSpecificationAndStatusCodeCreated() {
        given(service.saveSpecification(request)).willReturn(response);

        var result = controller.saveSpecification(request);

        then(result.getStatusCode()).isEqualTo(CREATED);
        then(result.getBody()).isEqualTo(response);

        verify(service).saveSpecification(request);
        verifyNoMoreInteractions(service);
    }

    @Test
    void shouldUpdateSpecificationAndStatusCodeAccepted() {
        given(service.updateSpecification(ID, request)).willReturn(response);

        var result = controller.updateSpecification(ID, request);

        then(result.getStatusCode()).isEqualTo(ACCEPTED);
        then(result.getBody()).isEqualTo(response);

        verify(service).updateSpecification(ID, request);
        verifyNoMoreInteractions(service);
    }

//    @Test
//    void shouldDeleteSpecificationdAndStatusCodeNoContent() {
//        willDoNothing().given(service).deleteSpecificationById(ID);
//
//        var result = controller.deleteSpecificaition(ID);
//
//        then(result.getStatusCode()).isEqualTo(NO_CONTENT);
//        then(result.getBody()).isNull();
//
//        verify(service).deleteSpecificationById(ID);
//        verifyNoMoreInteractions(service);
//    }
}