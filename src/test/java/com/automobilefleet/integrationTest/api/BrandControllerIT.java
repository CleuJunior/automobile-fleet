package com.automobilefleet.integrationTest.api;

import com.automobilefleet.api.dto.request.BrandRequest;
import com.github.javafaker.Faker;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import static com.automobilefleet.integrationTest.api.DataIT.BMW_ID;
import static com.automobilefleet.integrationTest.api.DataIT.BRAND_BMW;
import static com.automobilefleet.integrationTest.api.DataIT.BRAND_CHEVROLET;
import static com.automobilefleet.integrationTest.api.DataIT.BRAND_FERRARI;
import static com.automobilefleet.integrationTest.api.DataIT.BRAND_FORD;
import static com.automobilefleet.integrationTest.api.DataIT.BRAND_HONDA;
import static com.automobilefleet.integrationTest.api.DataIT.BRAND_NISSAN;
import static com.automobilefleet.integrationTest.api.DataIT.BRAND_RENAULT;
import static com.automobilefleet.integrationTest.api.DataIT.BRAND_TOYOTA;
import static com.automobilefleet.integrationTest.api.DataIT.BRAND_VOLKSWAGEN;
import static com.automobilefleet.integrationTest.api.DataIT.BRAND_YAMAHA;
import static com.automobilefleet.integrationTest.api.DataIT.CHEVROLET_ID;
import static com.automobilefleet.integrationTest.api.DataIT.CREATED_AT_ONE;
import static com.automobilefleet.integrationTest.api.DataIT.FERRARI_ID;
import static com.automobilefleet.integrationTest.api.DataIT.FORD_ID;
import static com.automobilefleet.integrationTest.api.DataIT.HONDA_ID;
import static com.automobilefleet.integrationTest.api.DataIT.NISSAN_ID;
import static com.automobilefleet.integrationTest.api.DataIT.RENAULT_ID;
import static com.automobilefleet.integrationTest.api.DataIT.TOYOTA_ID;
import static com.automobilefleet.integrationTest.api.DataIT.VOLKSWAGEN_ID;
import static com.automobilefleet.integrationTest.api.DataIT.YAMAHA_ID;
import static com.automobilefleet.utils.JsonMapper.asJsonString;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class BrandControllerIT extends AbstractWebIntegrationTest {

    @Container
    @ServiceConnection
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");
    @Autowired
    private MockMvc mockMvc;
    private final static Faker faker = new Faker();
    private final static String ENDPOINT = "/api/v1/brand";
    private final static String ENDPOINT_ID = ENDPOINT + "/{id}";

    @BeforeAll
    static void beforeAll() {
        var flyway = Flyway.configure()
                .dataSource(postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword())
                .load();

        flyway.migrate();
    }

    @Test
    void shouldGetBrandByIdAndStatusCodeOK() throws Exception {
        mockMvc.perform(get(ENDPOINT_ID, BMW_ID).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$._id").value(BMW_ID))
                .andExpect(jsonPath("$.name").value(BRAND_BMW))
                .andExpect(jsonPath("$.created_at").value(CREATED_AT_ONE));
    }

    @Test
    void shouldGetListBrandAndStatusCodeOK() throws Exception {
        mockMvc.perform(get(ENDPOINT).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(jsonPath("$[*]._id").value(containsInAnyOrder(BMW_ID, CHEVROLET_ID, YAMAHA_ID, RENAULT_ID, NISSAN_ID, TOYOTA_ID, FORD_ID, VOLKSWAGEN_ID, FERRARI_ID, HONDA_ID)))
                .andExpect(jsonPath("$[*].name").value(containsInAnyOrder(BRAND_BMW, BRAND_CHEVROLET, BRAND_YAMAHA, BRAND_RENAULT, BRAND_NISSAN, BRAND_TOYOTA, BRAND_FORD, BRAND_VOLKSWAGEN, BRAND_FERRARI, BRAND_HONDA)));
    }

    @Test
    void shouldGetPagetBrandAndStatusCodeOK() throws Exception {
        mockMvc.perform(get(ENDPOINT)
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
                .andExpect(jsonPath("$._id").value(BMW_ID))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.created_at").value(CREATED_AT_ONE));
    }
//
//    @Test
//    void shouldDeleteBrandAndStatusCodeNoContent() throws Exception {
//        mockMvc.perform(delete(DELETE_ID, ID).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isNoContent())
//                .andReturn();
//
//        Mockito.verify(service).deleteBrandById(ID);
//        Mockito.verifyNoMoreInteractions(service);
//    }
}