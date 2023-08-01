package com.automobilefleet.api.controllers;

import com.automobilefleet.api.dto.request.BrandRequest;
import com.automobilefleet.api.dto.response.BrandResponse;
import com.automobilefleet.services.BrandService;
import com.automobilefleet.utils.FactoryUtils;
import com.automobilefleet.utils.JsonMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@Transactional
class BrandControllerTest {
    @InjectMocks
    private BrandController controller;
    @Mock
    private BrandService service;
    private MockMvc mockMvc;
    private BrandResponse response;
    private BrandRequest request;
    private final static UUID ID = UUID.fromString("b86a92d8-6908-426e-8316-f72b0c849a4b");
    private final static String BASE_URL = "/api/v1/brand";
    private final static String URL_ID = BASE_URL + "/{id}";
    private final static String URL_LIST = BASE_URL + "/list";
    private final static String URL_SAVE = BASE_URL + "/save";
    private final static String UPDATE_ID = BASE_URL + "/update/{id}";
    private final static String DELETE_ID = BASE_URL + "/delete/{id}";

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.controller).alwaysDo(print()).build();
        this.response = FactoryUtils.createBrandReponse();
        this.request = FactoryUtils.createBrandRequest();
    }

    @Test
    void shouldGetBrandByIdAndStatusCodeOK() throws Exception {
        Mockito.when(this.service.getBrandById(ID)).thenReturn(this.response);
        this.mockMvc.perform(get(URL_ID, ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Mockito.verify(this.service).getBrandById(ID);
        Mockito.verifyNoMoreInteractions(this.service);
    }

    @Test
    void shouldGetListBrandAndStatusCodeOK() throws Exception {
        Mockito.when(this.service.listBrand()).thenReturn(Collections.singletonList(this.response));
        this.mockMvc.perform(get(URL_LIST).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Mockito.verify(this.service).listBrand();
        Mockito.verifyNoMoreInteractions(this.service);
    }

    @Test
    void shoulSaveBrandAndStatusCodeCreated() throws Exception {
        Mockito.when(this.service.saveBrand(any(BrandRequest.class))).thenReturn(this.response);

        this.mockMvc.perform(post(URL_SAVE).contentType(MediaType.APPLICATION_JSON)
                        .content(JsonMapper.asJsonString(this.response)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        Mockito.verify(this.service).saveBrand(any(BrandRequest.class));
        Mockito.verifyNoMoreInteractions(this.service);
    }

    @Test
    void shouldUpdateBrandAndStatusCodeAccepted() throws Exception {
        Mockito.when(this.service.updateBrand(eq(ID), any(BrandRequest.class))).thenReturn(this.response);

        this.mockMvc.perform(put(UPDATE_ID, ID).contentType(MediaType.APPLICATION_JSON)
                        .content(JsonMapper.asJsonString(this.request)))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andReturn();

        Mockito.verify(this.service).updateBrand(eq(ID), any(BrandRequest.class));
        Mockito.verifyNoMoreInteractions(this.service);
    }

    @Test
    void shouldDeleteBrandAndStatusCodeNoContent() throws Exception {
        this.mockMvc.perform(delete(DELETE_ID, ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn();

        Mockito.verify(this.service).deleteBrandById(ID);
        Mockito.verifyNoMoreInteractions(this.service);
    }
}