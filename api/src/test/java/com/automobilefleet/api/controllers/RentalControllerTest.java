package com.automobilefleet.api.controllers;

import com.automobilefleet.api.dto.request.RentalRequest;
import com.automobilefleet.api.dto.response.RentalResponse;
import com.automobilefleet.services.RentalService;
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
class RentalControllerTest {
    @Mock
    private RentalService service;
    @Mock
    private RentalResponse response;
    @Mock
    private RentalRequest request;
    @InjectMocks
    private RentalController controller;

    private static final UUID ID = randomUUID();

    @Test
    void shouldRentalByIdAndStatusCodeOK() {
        given(service.getRentalById(ID)).willReturn(response);

        var result = controller.getRentalById(ID);

        then(result.getStatusCode()).isEqualTo(OK);
        then(result.getBody()).isEqualTo(response);

        verify(service).getRentalById(ID);
        verifyNoMoreInteractions(service);
    }

    @Test
    void shouldGetSingleListRentalAndStatusCodeOK() {
        given(service.listOfRental()).willReturn(singletonList(response));

        var result = controller.listOfRental();

        then(result.getStatusCode()).isEqualTo(OK);
        then(result.getBody()).isEqualTo(singletonList(response));

        verify(service).listOfRental();
        verifyNoMoreInteractions(service);
    }

    @Test
    void shoulSaveRentalAndStatusCodeCreated() {
        given(service.saveRental(request)).willReturn(response);

        var result = controller.saveRental(request);

        then(result.getStatusCode()).isEqualTo(CREATED);
        then(result.getBody()).isEqualTo(response);

        verify(service).saveRental(request);
        verifyNoMoreInteractions(service);
    }

    @Test
    void shouldUpdateRentalAndStatusCodeAccepted() {
        given(service.updateRental(ID, request)).willReturn(response);

        var result = controller.updateRental(ID, request);

        then(result.getStatusCode()).isEqualTo(ACCEPTED);
        then(result.getBody()).isEqualTo(response);

        verify(service).updateRental(ID, request);
        verifyNoMoreInteractions(service);
    }
}