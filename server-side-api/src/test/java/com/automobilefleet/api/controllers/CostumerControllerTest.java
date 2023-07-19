package com.automobilefleet.api.controllers;

import com.automobilefleet.api.request.CostumerRequest;
import com.automobilefleet.api.response.CostumerResponse;
import com.automobilefleet.entities.Costumer;
import com.automobilefleet.services.CostumerService;
import com.automobilefleet.utils.costumer.CostumerFactoryUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@Transactional
class CostumerControllerTest {
    @InjectMocks
    private CostumerController controller;
    @Mock
    private CostumerService service;
    private MockMvc mockMvc;
    private CostumerResponse response;
    private CostumerRequest request;
    private final static Long ID = 1L;
    private final static String BASE_URL = "/api/v1/costumer";
    private final static String URL_ID = BASE_URL + "/{id}";
    private final static String URL_LIST = BASE_URL + "/list";
    private final static String URL_SAVE =  BASE_URL + "/save";
    private final static String UPDATE_ID =  BASE_URL + "/update/{id}";

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.controller).alwaysDo(print()).build();
        this.response = CostumerFactoryUtils.costumerResponseBuildRaimunda();
        this.request = CostumerFactoryUtils.costumerRequestBuildRaimunda();
    }

    @Test
    void shouldGetCostumerByIdAndStatusCodeOK() throws Exception {
        Mockito.when(this.service.getCostumerById(ID)).thenReturn(this.response);
        mockMvc.perform(get(URL_ID, ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Mockito.verify(this.service).getCostumerById(ID);
        Mockito.verifyNoMoreInteractions(this.service);
    }

    @Test
    void shouldGetListCostumerAndStatusCodeOK() throws Exception {
        List<CostumerResponse> listCostumerResponse = List.of(
                CostumerFactoryUtils.costumerResponseBuildRaimunda(),
                CostumerFactoryUtils.costumerResponseBuildGustavo(),
                CostumerFactoryUtils.costumerReponseBuildMaercela()
        );

        Mockito.when(this.service.listCostumer()).thenReturn(listCostumerResponse);
        mockMvc.perform(get(URL_LIST).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Mockito.verify(this.service).listCostumer();
        Mockito.verifyNoMoreInteractions(this.service);
    }

    @Test
    void shoulSaveCostumerAndStatusCodeCreated() throws Exception {
        Mockito.when(this.service.saveCostumer(any(CostumerRequest.class))).thenReturn(this.response);
        mockMvc.perform(post(URL_SAVE).contentType(MediaType.APPLICATION_JSON).content(this.asJsonString(request)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        Mockito.verify(this.service).saveCostumer(any(CostumerRequest.class));
        Mockito.verifyNoMoreInteractions(this.service);
    }

    @Test
    void shouldUpdateCostumerAndStatusCodeAccepted() throws Exception {
        Mockito.when(this.service.updateCostumer(eq(ID), any(CostumerRequest.class))).thenReturn(this.response);
        mockMvc.perform(put(UPDATE_ID, ID).contentType(MediaType.APPLICATION_JSON).content(this.asJsonString(request)))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andReturn();

        Mockito.verify(this.service).updateCostumer(eq(ID), any(CostumerRequest.class));
        Mockito.verifyNoMoreInteractions(this.service);
    }

    private String asJsonString(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}