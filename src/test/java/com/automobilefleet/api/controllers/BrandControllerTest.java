package com.automobilefleet.api.controllers;

import com.automobilefleet.api.dto.request.BrandRequest;
import com.automobilefleet.api.dto.response.BrandResponse;
import com.automobilefleet.services.BrandServiceImpl;
import com.automobilefleet.utils.JsonMapper;
import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

import static java.time.ZoneId.systemDefault;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.http.HttpStatus.OK;
import static java.util.Collections.singletonList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static java.util.UUID.randomUUID;

@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@Transactional
class BrandControllerTest {

    @InjectMocks
    private BrandController controller;
    @Mock
    private BrandServiceImpl service;
    private BrandResponse response;
    private BrandRequest request;
    private static final Faker faker = new Faker();
    private static final UUID ID = randomUUID();
    private static final String NAME = faker.starTrek().character();
    private static final LocalDateTime CREATED_AT = faker.date().birthday().toInstant().atZone(systemDefault()).toLocalDateTime();


    @BeforeEach
    void setUp() {
        response = new BrandResponse(ID, NAME, CREATED_AT);
        request = new BrandRequest(NAME);
    }

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

        given(service.pageBrand(0, 1)).willReturn(new PageImpl<>(listBrand, PageRequest.of(0, 1)));

        var result = controller.listOfBrand();

        then(result.getStatusCode()).isEqualTo(OK);
        then(result.getBody()).isEqualTo(singletonList(response));

        verify(service).listBrand();
        verifyNoMoreInteractions(service);
    }


//
//    @Test
//    void shoulSaveBrandAndStatusCodeCreated() throws Exception {
//        Mockito.when(this.service.saveBrand(any(BrandRequest.class))).thenReturn(this.response);
//
//        this.mockMvc.perform(post(URL_SAVE).contentType(MediaType.APPLICATION_JSON)
//                        .content(JsonMapper.asJsonString(this.response)))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andReturn();
//
//        verify(this.service).saveBrand(any(BrandRequest.class));
//        verifyNoMoreInteractions(this.service);
//    }
//
//    @Test
//    void shouldUpdateBrandAndStatusCodeAccepted() throws Exception {
//        Mockito.when(this.service.updateBrand(eq(ID), any(BrandRequest.class))).thenReturn(this.response);
//
//        this.mockMvc.perform(put(UPDATE_ID, ID).contentType(MediaType.APPLICATION_JSON)
//                        .content(JsonMapper.asJsonString(this.request)))
//                .andExpect(MockMvcResultMatchers.status().isAccepted())
//                .andReturn();
//
//        verify(this.service).updateBrand(eq(ID), any(BrandRequest.class));
//        verifyNoMoreInteractions(this.service);
//    }
//
//    @Test
//    void shouldDeleteBrandAndStatusCodeNoContent() throws Exception {
//        this.mockMvc.perform(delete(DELETE_ID, ID).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isNoContent())
//                .andReturn();
//
//        verify(this.service).deleteBrandById(ID);
//        verifyNoMoreInteractions(this.service);
//    }
}