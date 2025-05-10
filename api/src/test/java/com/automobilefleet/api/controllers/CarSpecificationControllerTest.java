package com.automobilefleet.api.controllers;

import com.automobilefleet.api.dto.projections.CarSpecificationInfo;
import com.automobilefleet.api.dto.request.CarSpecificationRequest;
import com.automobilefleet.api.dto.response.CarSpecificationResponse;
import com.automobilefleet.services.CarSpecificationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
class CarSpecificationControllerTest {

    @Mock
    private CarSpecificationService service;
    @Mock
    private CarSpecificationResponse response;
    @Mock
    private CarSpecificationInfo info;
    @Mock
    private CarSpecificationRequest request;
    @InjectMocks
    private CarSpecificationController controller;

    private static final UUID ID = UUID.randomUUID();

    @Test
    void shouldGetCarSpecificationInfoByIdAndStatusCodeOK() {
        given(service.getCarSpecification(ID)).willReturn(info);

        var result = controller.getCarSpecificationInfoById(ID);

        then(result.getStatusCode()).isEqualTo(OK);
        then(result.getBody()).isEqualTo(info);

        verify(service).getCarSpecification(ID);
        verifyNoMoreInteractions(service);
    }

    @Test
    void shouldGetCarSpecificationByIdAndStatusCodeOK() {
        given(service.getCarSpecificationById(ID)).willReturn(response);

        var result = controller.getCarSpecificationId(ID);

        then(result.getStatusCode()).isEqualTo(OK);
        then(result.getBody()).isEqualTo(response);

        verify(service).getCarSpecificationById(ID);
        verifyNoMoreInteractions(service);
    }

    @Test
    void shouldGetListCarSpecificationAndStatusCodeOK() {
        given(service.listCarSpecification()).willReturn(singletonList(response));

        var result = controller.listOfCarSpecification();

        then(result.getStatusCode()).isEqualTo(OK);
        then(result.getBody()).isEqualTo(singletonList(response));

        verify(service).listCarSpecification();
        verifyNoMoreInteractions(service);
    }

    @Test
    void shouldSaveCarSpecificationAndStatusCodeCreated() {
        given(service.saveCarSpecification(request)).willReturn(response);

        var result = controller.saveCarSpecification(request);

        then(result.getStatusCode()).isEqualTo(CREATED);
        then(result.getBody()).isEqualTo(response);

        verify(service).saveCarSpecification(request);
        verifyNoMoreInteractions(service);
    }


    @Test
    void shouldUpdateCustomerAndStatusCodeAccepted() {
        given(service.updateCarSpecification(ID, request)).willReturn(response);

        var result = controller.updateCarSpecification(ID, request);

        then(result.getStatusCode()).isEqualTo(ACCEPTED);
        then(result.getBody()).isEqualTo(response);

        verify(service).updateCarSpecification(ID, request);
        verifyNoMoreInteractions(service);
    }
}