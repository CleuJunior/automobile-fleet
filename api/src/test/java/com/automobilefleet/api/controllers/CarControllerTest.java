package com.automobilefleet.api.controllers;

import com.automobilefleet.api.dto.request.CarRequest;
import com.automobilefleet.api.dto.response.CarResponse;
import com.automobilefleet.services.CarServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static java.util.Collections.singletonList;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
class CarControllerTest {
    @Mock
    private CarServiceImpl service;
    @Mock
    private CarResponse response;
    @Mock
    private CarRequest request;
    @InjectMocks
    private CarController controller;

    private static final UUID ID = randomUUID();
    private static final String BRAND = "GENERIC_BRAND";

    @Test
    void shouldGetCarByIdAndStatusCodeOK() {
        given(service.getCarById(ID)).willReturn(response);

        var result = controller.getCardById(ID);

        then(result.getStatusCode()).isEqualTo(OK);
        then(result.getBody()).isEqualTo(response);

        verify(service).getCarById(ID);
        verifyNoMoreInteractions(service);
    }

    @Test
    void shouldGetSingleListCarAndStatusCodeOK() {
        given(service.listOfCars()).willReturn(singletonList(response));

        var result = controller.listOfCars();

        then(result.getStatusCode()).isEqualTo(OK);
        then(result.getBody()).isEqualTo(singletonList(response));

        verify(service).listOfCars();
        verifyNoMoreInteractions(service);
    }

    @Test
    void shouldGetSingleListCarAvailableAndStatusCodeOK() {
        given(service.findByCarAvailable()).willReturn(singletonList(response));

        var result = controller.getCarAvailable();

        then(result.getStatusCode()).isEqualTo(OK);
        then(result.getBody()).isEqualTo(singletonList(response));

        verify(service).findByCarAvailable();
        verifyNoMoreInteractions(service);
    }

    @Test
    void shouldGetCarSingleListByBrandAndStatusCodeOK() {
        given(service.findByCarBrand(BRAND)).willReturn(singletonList(response));

        var result = controller.getListCarByBrand(BRAND);

        then(result.getStatusCode()).isEqualTo(OK);
        then(result.getBody()).isEqualTo(singletonList(response));

        verify(service).findByCarBrand(BRAND);
        verifyNoMoreInteractions(service);
    }

    @Test
    void shoulSaveCarAndStatusCodeCreated() {
        given(service.saveCar(request)).willReturn(response);

        var result = controller.saveCar(request);

        then(result.getStatusCode()).isEqualTo(CREATED);
        then(result.getBody()).isEqualTo(response);

        verify(service).saveCar(request);
        verifyNoMoreInteractions(service);
    }

    @Test
    void shouldUpdateCarAndStatusCodeAccepted() {
        given(service.updateCar(ID, request)).willReturn(response);

        var result = controller.updateCar(ID, request);

        then(result.getStatusCode()).isEqualTo(ACCEPTED);
        then(result.getBody()).isEqualTo(response);

        verify(service).updateCar(ID, request);
        verifyNoMoreInteractions(service);
    }
}