package com.automobilefleet.integrationTest;

import com.automobilefleet.api.dto.request.BrandRequest;
import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static com.automobilefleet.integrationTest.DataIT.BMW_CREATED_AT;
import static com.automobilefleet.integrationTest.DataIT.BMW_ID;
import static com.automobilefleet.integrationTest.DataIT.BRAND_BMW;
import static com.automobilefleet.integrationTest.DataIT.BRAND_CHEVROLET;
import static com.automobilefleet.integrationTest.DataIT.BRAND_FERRARI;
import static com.automobilefleet.integrationTest.DataIT.BRAND_FORD;
import static com.automobilefleet.integrationTest.DataIT.BRAND_HONDA;
import static com.automobilefleet.integrationTest.DataIT.BRAND_NISSAN;
import static com.automobilefleet.integrationTest.DataIT.BRAND_RENAULT;
import static com.automobilefleet.integrationTest.DataIT.BRAND_TOYOTA;
import static com.automobilefleet.integrationTest.DataIT.BRAND_VOLKSWAGEN;
import static com.automobilefleet.integrationTest.DataIT.BRAND_YAMAHA;
import static com.automobilefleet.integrationTest.DataIT.CHEVROLET_ID;
import static com.automobilefleet.integrationTest.DataIT.FERRARI_ID;
import static com.automobilefleet.integrationTest.DataIT.FORD_ID;
import static com.automobilefleet.integrationTest.DataIT.HONDA_ID;
import static com.automobilefleet.integrationTest.DataIT.NISSAN_ID;
import static com.automobilefleet.integrationTest.DataIT.RENAULT_ID;
import static com.automobilefleet.integrationTest.DataIT.TOYOTA_ID;
import static com.automobilefleet.integrationTest.DataIT.VOLKSWAGEN_ID;
import static com.automobilefleet.integrationTest.DataIT.YAMAHA_ID;
import static com.automobilefleet.utils.JsonMapper.asJsonString;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@Transactional
class BrandControllerIT extends IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private final static Faker faker = new Faker();
    private final static String ENDPOINT = "/api/v1/brand";
    private final static String ENDPOINT_ID = ENDPOINT + "/{id}";

    @Test
    void shouldGetBrandByIdAndStatusCodeOK() throws Exception {
        mockMvc.perform(get(ENDPOINT_ID, BMW_ID).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$._id").value(BMW_ID))
                .andExpect(jsonPath("$.name").value(BRAND_BMW))
                .andExpect(jsonPath("$.created_at").value(BMW_CREATED_AT));
    }

    @Test
    void shouldGetListBrandAndStatusCodeOK() throws Exception {
        this.mockMvc.perform(get(ENDPOINT).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(jsonPath("$[*]._id").value(containsInAnyOrder(BMW_ID, CHEVROLET_ID, YAMAHA_ID, RENAULT_ID, NISSAN_ID, TOYOTA_ID, FORD_ID, VOLKSWAGEN_ID, FERRARI_ID, HONDA_ID)))
                .andExpect(jsonPath("$[*].name").value(containsInAnyOrder(BRAND_BMW, BRAND_CHEVROLET, BRAND_YAMAHA, BRAND_RENAULT, BRAND_NISSAN, BRAND_TOYOTA, BRAND_FORD, BRAND_VOLKSWAGEN, BRAND_FERRARI, BRAND_HONDA)));
    }

    @Test
    void shouldGetPagetBrandAndStatusCodeOK() throws Exception {
        this.mockMvc.perform(get(ENDPOINT)
                        .param("page", "0")
                        .param("size", "2")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[*]._id").value(containsInAnyOrder(BMW_ID, CHEVROLET_ID)))
                .andExpect(jsonPath("$.content[*].name").value(containsInAnyOrder(BRAND_BMW, BRAND_CHEVROLET)))
                .andExpect(jsonPath("$.totalElements").value(10))
                .andExpect(jsonPath("$.totalPages").value(5));
    }

    @Test
    void shoulSaveBrandAndStatusCodeCreated() throws Exception {
        var name = faker.harryPotter().book();
        var request = new BrandRequest(name);

        mockMvc.perform(post(ENDPOINT).contentType(APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$._id").isNotEmpty())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.created_at").isNotEmpty());
    }

    @Test
    void shouldUpdateBrandAndStatusCodeAccepted() throws Exception {
        var name = faker.harryPotter().book();
        var request = new BrandRequest(name);

        mockMvc.perform(put(ENDPOINT_ID, BMW_ID).contentType(APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isAccepted())
                .andDo(print())
                .andExpect(jsonPath("$._id").value(BMW_ID))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.created_at").value(BMW_CREATED_AT));
    }
//
//    @Test
//    void shouldDeleteBrandAndStatusCodeNoContent() throws Exception {
//        this.mockMvc.perform(delete(DELETE_ID, ID).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isNoContent())
//                .andReturn();
//
//        Mockito.verify(this.service).deleteBrandById(ID);
//        Mockito.verifyNoMoreInteractions(this.service);
//    }
}