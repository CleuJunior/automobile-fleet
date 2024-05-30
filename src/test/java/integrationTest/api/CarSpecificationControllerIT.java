package integrationTest.api;

import com.automobilefleet.api.dto.request.CarSpecificationRequest;
import integrationTest.api.config.AbstractWebIntegrationTest;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import static integrationTest.api.data.DataIT.CAR_SPECIFICATION_BMW_ID;
import static integrationTest.api.data.DataIT.CAR_SPECIFICATION_FERRARI_ID;
import static integrationTest.api.data.DataIT.CAR_SPECIFICATION_MUSTANG;
import static integrationTest.api.data.DataIT.CAR_SPECIFICATION_ONIX_ID;
import static integrationTest.api.data.DataIT.COROLLA_CAR_ID;
import static integrationTest.api.data.DataIT.MARCH_CAR_ID;
import static integrationTest.api.data.DataIT.MUSTANG_CAR_ID;
import static integrationTest.api.data.DataIT.SPECIFICATION_HEIGHT_ID;
import static integrationTest.api.data.DataIT.SPECIFICATION_TANK_CAPACITY_ID;
import static integrationTest.api.data.DataIT.SPECIFICATION_WIDTH_ID;
import static com.automobilefleet.utils.JsonMapper.asJsonString;
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

class CarSpecificationControllerIT extends AbstractWebIntegrationTest {

    @Container
    @ServiceConnection
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");
    @Autowired
    private MockMvc mockMvc;
    private final static String ENDPOINT = "/integrationTest/api/v1/car_specification";
    private final static String ENDPOINT_ID = ENDPOINT + "/{id}";

    @BeforeAll
    static void beforeAll() {
        var flyway = Flyway.configure()
                .dataSource(postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword())
                .load();

        flyway.migrate();
    }

    @Test
    void shouldGetCarSpecificationdByIdAndStatusCodeOK() throws Exception {
        mockMvc.perform(get(ENDPOINT_ID, CAR_SPECIFICATION_MUSTANG).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$._id").value(CAR_SPECIFICATION_MUSTANG))
                .andExpect(jsonPath("$.car._id").value(COROLLA_CAR_ID))
                .andExpect(jsonPath("$.specification._id").value(SPECIFICATION_TANK_CAPACITY_ID));
    }

    @Test
    void shouldGetListCarSpecificationsAndStatusCodeOK() throws Exception {
        mockMvc.perform(get(ENDPOINT).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(jsonPath("$.[0]._id").value(CAR_SPECIFICATION_ONIX_ID))
                .andExpect(jsonPath("$.[9]._id").value(CAR_SPECIFICATION_BMW_ID));
    }

    @Test
    void shoulSaveCarSpecificationAndStatusCodeCreated() throws Exception {
        var carId = fromString(MUSTANG_CAR_ID);
        var specificationId = fromString(SPECIFICATION_WIDTH_ID);
        var request = new CarSpecificationRequest(carId, specificationId);

        mockMvc.perform(post(ENDPOINT).contentType(APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$._id").isNotEmpty())
                .andExpect(jsonPath("$.car._id").value(MUSTANG_CAR_ID))
                .andExpect(jsonPath("$.specification._id").value(SPECIFICATION_WIDTH_ID));
    }

    @Test
    void shouldUpdateCarSpecificationAndStatusCodeAccepted() throws Exception {
        var carId = fromString(MARCH_CAR_ID);
        var specificationId = fromString(SPECIFICATION_HEIGHT_ID);
        var request = new CarSpecificationRequest(carId, specificationId);

        mockMvc.perform(put(ENDPOINT_ID, CAR_SPECIFICATION_FERRARI_ID).contentType(APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$._id").value(CAR_SPECIFICATION_FERRARI_ID))
                .andExpect(jsonPath("$.car._id").value(MARCH_CAR_ID))
                .andExpect(jsonPath("$.specification._id").value(SPECIFICATION_HEIGHT_ID));
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