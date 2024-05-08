package com.automobilefleet.api.controllers;

import com.automobilefleet.api.dto.request.BrandRequest;
import com.automobilefleet.api.dto.response.BrandResponse;
import com.automobilefleet.services.BrandServiceImpl;
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
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
class BrandControllerTest {

    @Mock
    private BrandServiceImpl service;
    @Mock
    private BrandResponse response;
    @Mock
    private BrandRequest request;
    @InjectMocks
    private BrandController controller;

    private static final UUID ID = randomUUID();

    @Test
    void shouldGetBrandByIdAndStatusCodeOK() {
        given(service.getBrandById(ID)).willReturn(response);

        var result = controller.getBrandById(ID);

        then(result.getStatusCode()).isEqualTo(OK);
        then(result.getBody()).isEqualTo(response);

        verify(service).getBrandById(ID);
        verifyNoMoreInteractions(service);
    }

    @Test
    void shouldGetListBrandAndStatusCodeOK() {
        given(service.listBrand()).willReturn(singletonList(response));

        var result = controller.listOfBrand();

        then(result.getStatusCode()).isEqualTo(OK);
        then(result.getBody()).isEqualTo(singletonList(response));

        verify(service).listBrand();
        verifyNoMoreInteractions(service);
    }

    @Test
    void shouldGetPageBrandAndStatusCodeOK() {
        var listBrand = singletonList(response);
        var pageBrand = new PageImpl<>(listBrand, of(0, 1), 1);

        given(service.pageBrand(0, 1)).willReturn(pageBrand);

        var result = controller.pageBrand(0, 1);

        then(result.getStatusCode()).isEqualTo(OK);
        then(requireNonNull(result.getBody()).getContent()).isEqualTo(listBrand);

        verify(service).pageBrand(0, 1);
        verifyNoMoreInteractions(service);
    }

    @Test
    void shoulSaveBrandAndStatusCodeCreated() {
        given(service.saveBrand(request)).willReturn(response);

        var result = controller.saveBrand(request);

        then(result.getStatusCode()).isEqualTo(CREATED);
        then(result.getBody()).isEqualTo(response);

        verify(service).saveBrand(request);
        verifyNoMoreInteractions(service);
    }

    @Test
    void shouldUpdateBrandAndStatusCodeAccepted() {
        given(service.updateBrand(ID, request)).willReturn(response);

        var result = controller.updateBrand(ID, request);

        then(result.getStatusCode()).isEqualTo(ACCEPTED);
        then(result.getBody()).isEqualTo(response);

        verify(service).updateBrand(ID, request);
        verifyNoMoreInteractions(service);
    }

    @Test
    void shouldDeleteBrandAndStatusCodeNoContent() {
        willDoNothing().given(service).deleteBrandById(ID);

        var result = controller.deleteBrand(ID);

        then(result.getStatusCode()).isEqualTo(NO_CONTENT);
        then(result.getBody()).isNull();

        verify(service).deleteBrandById(ID);
        verifyNoMoreInteractions(service);
    }
}