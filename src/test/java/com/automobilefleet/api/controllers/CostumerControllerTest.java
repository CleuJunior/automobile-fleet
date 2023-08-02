package com.automobilefleet.api.controllers;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.automobilefleet.api.dto.request.CostumerRequest;
import com.automobilefleet.api.dto.response.CostumerResponse;
import com.automobilefleet.services.CostumerService;
import com.automobilefleet.utils.JsonMapper;
import org.junit.jupiter.api.BeforeAll;
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
    private final static UUID ID = UUID.fromString("32ca0461-0401-4b15-bf57-3d2b18b3828f");
    private final static String URL_ID = "/api/v1/costumer/{id}";
    private final static String URL_LIST = "/api/v1/costumer/list";
    private final static String URL_SAVE = "/api/v1/costumer/save";
    private final static String UPDATE_ID = "/api/v1/costumer/update/{id}";

    @BeforeAll
    static void setup() {
        FixtureFactoryLoader.loadTemplates("com.automobilefleet.utils");
    }

    @BeforeEach
    void setupAttributes() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.controller).alwaysDo(print()).build();
        this.response = Fixture.from(CostumerResponse.class).gimme("response");
        this.request = Fixture.from(CostumerRequest.class).gimme("request");
    }

    @Test
    void shouldGetCostumerByIdAndStatusCodeOK() throws Exception {
        Mockito.when(this.service.getCostumerById(ID)).thenReturn(this.response);
        this.mockMvc.perform(get(URL_ID, ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Mockito.verify(this.service).getCostumerById(ID);
        Mockito.verifyNoMoreInteractions(this.service);
    }

    @Test
    void shouldGetListCostumerAndStatusCodeOK() throws Exception {
        Mockito.when(this.service.listCostumer()).thenReturn(Collections.singletonList(this.response));
        this.mockMvc.perform(get(URL_LIST).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Mockito.verify(this.service).listCostumer();
        Mockito.verifyNoMoreInteractions(this.service);
    }

    @Test
    void shoulSaveCostumerAndStatusCodeCreated() throws Exception {
        Mockito.when(this.service.saveCostumer(any(CostumerRequest.class))).thenReturn(this.response);
        this.mockMvc.perform(post(URL_SAVE).contentType(MediaType.APPLICATION_JSON).content(JsonMapper.asJsonString(request)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        Mockito.verify(this.service).saveCostumer(any(CostumerRequest.class));
        Mockito.verifyNoMoreInteractions(this.service);
    }

    @Test
    void shouldUpdateCostumerAndStatusCodeAccepted() throws Exception {
        Mockito.when(this.service.updateCostumer(eq(ID), any(CostumerRequest.class))).thenReturn(this.response);
        this.mockMvc.perform(put(UPDATE_ID, ID).contentType(MediaType.APPLICATION_JSON)
                        .content(JsonMapper.asJsonString(this.request)))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andReturn();

        Mockito.verify(this.service).updateCostumer(eq(ID), any(CostumerRequest.class));
        Mockito.verifyNoMoreInteractions(this.service);
    }
}