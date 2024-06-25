package integrationTest.api;

import com.automobilefleet.api.dto.request.RentalRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import integrationTest.api.config.AbstractWebIntegrationTest;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.time.LocalDate;

import static integrationTest.api.data.DataIT.END_DATE;
import static integrationTest.api.data.DataIT.LUANA_ID;
import static integrationTest.api.data.DataIT.RAIMUNDA_ID;
import static integrationTest.api.data.DataIT.RENTAL_ID;
import static integrationTest.api.data.DataIT.SERIE_THREE_CAR_ID;
import static integrationTest.api.data.DataIT.START_DATE;
import static integrationTest.api.data.DataIT.TOTAL;
import static java.util.UUID.fromString;
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


class RentalControllerIT extends AbstractWebIntegrationTest {

    @Container
    @ServiceConnection
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    private final static Faker faker = new Faker();
    private final static String ENDPOINT = "/integrationTest/api/v1/rental";
    private final static String ENDPOINT_ID = ENDPOINT + "/{id}";

    @BeforeAll
    static void beforeAll() {
        var flyway = Flyway.configure()
                .dataSource(postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword())
                .load();

        flyway.migrate();
    }

    @Test
    void shouldRentalByIdAndStatusCodeOK() throws Exception {
        mockMvc.perform(get(ENDPOINT_ID, RENTAL_ID).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$._id").value(RENTAL_ID))
                .andExpect(jsonPath("$.start_date").value(START_DATE))
                .andExpect(jsonPath("$.end_date").value(END_DATE))
                .andExpect(jsonPath("$.car._id").value(SERIE_THREE_CAR_ID))
                .andExpect(jsonPath("$.customer._id").value(RAIMUNDA_ID))
                .andExpect(jsonPath("$.total").value(TOTAL));
    }

    @Test
    void shouldGetListRentalAndStatusCodeOK() throws Exception {
        mockMvc.perform(get(ENDPOINT).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(jsonPath("$.[0]._id").value(RENTAL_ID));
    }

    @Test
    void shoulSaveRentalAndStatusCodeCreated() throws Exception {
        var carId = fromString(SERIE_THREE_CAR_ID);
        var customerId = fromString(LUANA_ID);
        var startDate = LocalDate.now();
        var endDate = startDate.plusDays(3);
        var request = new RentalRequest(carId, customerId, startDate, endDate);

        mockMvc.perform(post(ENDPOINT).contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$._id").isNotEmpty())
                .andExpect(jsonPath("$.car._id").value(SERIE_THREE_CAR_ID))
                .andExpect(jsonPath("$.customer._id").value(LUANA_ID))
                .andExpect(jsonPath("$.total").value("296.31"));
    }

    @Test
    void shouldUpdateRentalAndStatusCodeAccepted() throws Exception {
        var carId = fromString(SERIE_THREE_CAR_ID);
        var customerId = fromString(LUANA_ID);
        var startDate = LocalDate.now();
        var endDate = startDate.plusDays(10);
        var request = new RentalRequest(carId, customerId, startDate, endDate);

        mockMvc.perform(put(ENDPOINT_ID, RENTAL_ID).contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$._id").value(RENTAL_ID))
                .andExpect(jsonPath("$.car._id").value(SERIE_THREE_CAR_ID))
                .andExpect(jsonPath("$.customer._id").value(LUANA_ID))
                .andExpect(jsonPath("$.total").value("987.7"));
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