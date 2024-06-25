package integrationTest.api;

import com.automobilefleet.api.dto.request.SpecificationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import integrationTest.api.config.AbstractWebIntegrationTest;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.webservices.server.AutoConfigureMockWebServiceClient;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import static integrationTest.api.data.DataIT.CREATED_AT_FOUR;
import static integrationTest.api.data.DataIT.SPECIFICATION_BRAKES;
import static integrationTest.api.data.DataIT.SPECIFICATION_BRAKES_DESCRIPTION;
import static integrationTest.api.data.DataIT.SPECIFICATION_BRAKES_ID;
import static integrationTest.api.data.DataIT.SPECIFICATION_ENGINE;
import static integrationTest.api.data.DataIT.SPECIFICATION_ENGINE_DESCRIPTION;
import static integrationTest.api.data.DataIT.SPECIFICATION_ENGINE_ID;
import static integrationTest.api.data.DataIT.SPECIFICATION_EXCHANGE;
import static integrationTest.api.data.DataIT.SPECIFICATION_EXCHANGE_DESCRIPTION;
import static integrationTest.api.data.DataIT.SPECIFICATION_EXCHANGE_ID;
import static integrationTest.api.data.DataIT.SPECIFICATION_HEIGHT;
import static integrationTest.api.data.DataIT.SPECIFICATION_HEIGHT_DESCRIPTION;
import static integrationTest.api.data.DataIT.SPECIFICATION_HEIGHT_ID;
import static integrationTest.api.data.DataIT.SPECIFICATION_LENGTH;
import static integrationTest.api.data.DataIT.SPECIFICATION_LENGTH_DESCRIPTION;
import static integrationTest.api.data.DataIT.SPECIFICATION_LENGTH_ID;
import static integrationTest.api.data.DataIT.SPECIFICATION_STEERING_WHEEL;
import static integrationTest.api.data.DataIT.SPECIFICATION_STEERING_WHEEL_DESCRIPTION;
import static integrationTest.api.data.DataIT.SPECIFICATION_STEERING_WHEEL_ID;
import static integrationTest.api.data.DataIT.SPECIFICATION_SUSPENSION;
import static integrationTest.api.data.DataIT.SPECIFICATION_SUSPENSION_DESCRIPTION;
import static integrationTest.api.data.DataIT.SPECIFICATION_SUSPENSION_ID;
import static integrationTest.api.data.DataIT.SPECIFICATION_TANK_CAPACITY;
import static integrationTest.api.data.DataIT.SPECIFICATION_TANK_CAPACITY_DESCRIPTION;
import static integrationTest.api.data.DataIT.SPECIFICATION_TANK_CAPACITY_ID;
import static integrationTest.api.data.DataIT.SPECIFICATION_WEIGHT;
import static integrationTest.api.data.DataIT.SPECIFICATION_WEIGHT_DESCRIPTION;
import static integrationTest.api.data.DataIT.SPECIFICATION_WEIGHT_ID;
import static integrationTest.api.data.DataIT.SPECIFICATION_WIDTH;
import static integrationTest.api.data.DataIT.SPECIFICATION_WIDTH_DESCRIPTION;
import static integrationTest.api.data.DataIT.SPECIFICATION_WIDTH_ID;
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

@AutoConfigureMockMvc
class SpecificationControllerIT extends AbstractWebIntegrationTest {

    @Container
    @ServiceConnection
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    private final static Faker faker = new Faker();
    private final static String ENDPOINT = "/integrationTest/api/v1/specification";
    private final static String ENDPOINT_ID = ENDPOINT + "/{id}";

    @BeforeAll
    static void beforeAll() {
        var flyway = Flyway.configure()
                .dataSource(postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword())
                .load();

        flyway.migrate();
    }

    @Test
    void shouldGetSpecificationByIdAndStatusCodeOK() throws Exception {
        mockMvc.perform(get(ENDPOINT_ID, SPECIFICATION_BRAKES_ID).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$._id").value(SPECIFICATION_BRAKES_ID))
                .andExpect(jsonPath("$.name").value(SPECIFICATION_BRAKES))
                .andExpect(jsonPath("$.description").value(SPECIFICATION_BRAKES_DESCRIPTION))
                .andExpect(jsonPath("$.created_at").value(CREATED_AT_FOUR));
    }

    @Test
    void shouldGetListSpecificationsAndStatusCodeOK() throws Exception {
        mockMvc.perform(get(ENDPOINT).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(jsonPath("$[*]._id").value(containsInAnyOrder(SPECIFICATION_ENGINE_ID, SPECIFICATION_EXCHANGE_ID, SPECIFICATION_STEERING_WHEEL_ID, SPECIFICATION_BRAKES_ID, SPECIFICATION_SUSPENSION_ID, SPECIFICATION_TANK_CAPACITY_ID, SPECIFICATION_WEIGHT_ID, SPECIFICATION_LENGTH_ID, SPECIFICATION_HEIGHT_ID, SPECIFICATION_WIDTH_ID)))
                .andExpect(jsonPath("$[*].name").value(containsInAnyOrder(SPECIFICATION_ENGINE, SPECIFICATION_EXCHANGE, SPECIFICATION_STEERING_WHEEL, SPECIFICATION_BRAKES, SPECIFICATION_SUSPENSION, SPECIFICATION_TANK_CAPACITY, SPECIFICATION_WEIGHT, SPECIFICATION_LENGTH, SPECIFICATION_HEIGHT, SPECIFICATION_WIDTH)))
                .andExpect(jsonPath("$[*].description").value(containsInAnyOrder(SPECIFICATION_ENGINE_DESCRIPTION, SPECIFICATION_EXCHANGE_DESCRIPTION, SPECIFICATION_STEERING_WHEEL_DESCRIPTION, SPECIFICATION_BRAKES_DESCRIPTION, SPECIFICATION_SUSPENSION_DESCRIPTION, SPECIFICATION_TANK_CAPACITY_DESCRIPTION, SPECIFICATION_WEIGHT_DESCRIPTION, SPECIFICATION_LENGTH_DESCRIPTION, SPECIFICATION_HEIGHT_DESCRIPTION, SPECIFICATION_WIDTH_DESCRIPTION)));
    }

    @Test
    void shouldGetPagetSpecificationAndStatusCodeOK() throws Exception {
        mockMvc.perform(get(ENDPOINT)
                        .param("page", "2")
                        .param("size", "3")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(3)))
                .andExpect(jsonPath("$.content[*]._id").value(containsInAnyOrder(SPECIFICATION_WEIGHT_ID, SPECIFICATION_LENGTH_ID, SPECIFICATION_HEIGHT_ID)))
                .andExpect(jsonPath("$.content[*].name").value(containsInAnyOrder(SPECIFICATION_WEIGHT, SPECIFICATION_LENGTH, SPECIFICATION_HEIGHT)))
                .andExpect(jsonPath("$.content[*].description").value(containsInAnyOrder(SPECIFICATION_WEIGHT_DESCRIPTION, SPECIFICATION_LENGTH_DESCRIPTION, SPECIFICATION_HEIGHT_DESCRIPTION)))
                .andExpect(jsonPath("$.totalElements").value(10))
                .andExpect(jsonPath("$.totalPages").value(4));
    }

    @Test
    void shoulSaveSpecificationAndStatusCodeCreated() throws Exception {
        var name = faker.harryPotter().character();
        var description = faker.harryPotter().location();
        var request = new SpecificationRequest(name, description);

        mockMvc.perform(post(ENDPOINT).contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$._id").isNotEmpty())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.description").value(description))
                .andExpect(jsonPath("$.created_at").isNotEmpty());
    }

    @Test
    void shouldUpdateSpecificationAndStatusCodeAccepted() throws Exception {
        var name = faker.harryPotter().character();
        var description = faker.harryPotter().location();
        var request = new SpecificationRequest(name, description);

        mockMvc.perform(put(ENDPOINT_ID, SPECIFICATION_BRAKES_ID).contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isAccepted())
                .andDo(print())
                .andExpect(jsonPath("$._id").value(SPECIFICATION_BRAKES_ID))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.description").value(description));
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