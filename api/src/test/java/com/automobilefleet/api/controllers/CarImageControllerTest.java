package com.automobilefleet.api.controllers;

import com.automobilefleet.api.dto.request.CarImageRequest;
import com.automobilefleet.api.dto.response.CarImageResponse;
import com.automobilefleet.services.CarImageServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;
import java.util.UUID;

import static java.util.Collections.singletonList;
import static java.util.Objects.requireNonNull;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
class CarImageControllerTest {
    @Mock
    private CarImageServiceImpl service;
    @Mock
    private CarImageResponse response;
    @Mock
    private CarImageRequest request;
    @InjectMocks
    private CarImageController controller;

    private static final UUID ID = randomUUID();

    @Test
    void shouldGetImageByIdAndStatusCodeOK() {
        given(service.getImageById(ID)).willReturn(response);

        var result = controller.getCarImageById(ID);

        then(result.getStatusCode()).isEqualTo(OK);
        then(result.getBody()).isEqualTo(response);

        verify(service).getImageById(ID);
        verifyNoMoreInteractions(service);
    }

    @Test
    void shouldGetImagesListAndStatusCodeOK() {
        given(service.listAllImage()).willReturn(Collections.singletonList(response));

        var result = controller.listOfCarImages();

        then(result.getStatusCode()).isEqualTo(OK);
        then(result.getBody()).isEqualTo(singletonList(response));

        verify(service).listAllImage();
        verifyNoMoreInteractions(service);
    }

    @Test
    void shouldGetPageImagesAndStatusCodeOK() {
        var listImages = singletonList(response);
        var pageImages = new PageImpl<>(listImages, of(0, 1), 1);

        given(service.pageCarImage(0, 1)).willReturn(pageImages);

        var result = controller.pageCarImages(0, 1);

        then(result.getStatusCode()).isEqualTo(OK);
        then(requireNonNull(result.getBody()).getContent()).isEqualTo(listImages);

        verify(service).pageCarImage(0, 1);
        verifyNoMoreInteractions(service);
    }

    @Test
    void shoulSaveImageAndStatusCodeCreated() {
        given(service.saveCarImage(request)).willReturn(response);

        var result = controller.saveImage(request);

        then(result.getStatusCode()).isEqualTo(CREATED);
        then(result.getBody()).isEqualTo(response);

        verify(service).saveCarImage(request);
        verifyNoMoreInteractions(service);
    }

    @Test
    void shouldUpdateImageAndStatusCodeAccepted() {
        given(service.updateCarImage(ID, request)).willReturn(response);

        var result = controller.updateCarImage(ID, request);

        then(result.getStatusCode()).isEqualTo(ACCEPTED);
        then(result.getBody()).isEqualTo(response);

        verify(service).updateCarImage(ID, request);
        verifyNoMoreInteractions(service);
    }

    @Test
    void shouldDeleteImageAndStatusCodeNoContent() {
        willDoNothing().given(service).deleteCarImageById(ID);

        var result = controller.deleteCarImageById(ID);

        then(result.getStatusCode()).isEqualTo(NO_CONTENT);
        then(result.getBody()).isNull();

        verify(service).deleteCarImageById(ID);
        verifyNoMoreInteractions(service);
    }
}