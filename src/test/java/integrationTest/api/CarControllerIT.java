package integrationTest.api;

import com.automobilefleet.api.dto.request.CarRequest;
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

import static integrationTest.api.data.DataIT.BRAND_VOLKSWAGEN;
import static integrationTest.api.data.DataIT.CATEGORY_HATCH_ID;
import static integrationTest.api.data.DataIT.CATEGORY_SUVs_ID;
import static integrationTest.api.data.DataIT.CATEGORY_TRUCKS_ID;
import static integrationTest.api.data.DataIT.CIVIC_CAR_ID;
import static integrationTest.api.data.DataIT.CIVIC_CAR_NAME;
import static integrationTest.api.data.DataIT.COROLLA_AVAILABLE;
import static integrationTest.api.data.DataIT.COROLLA_CAR_ID;
import static integrationTest.api.data.DataIT.COROLLA_DAILY_RATE;
import static integrationTest.api.data.DataIT.COROLLA_LICENSE_PLATE;
import static integrationTest.api.data.DataIT.FERRARI_ID;
import static integrationTest.api.data.DataIT.GOL_CAR_DESCRIPTION;
import static integrationTest.api.data.DataIT.GOL_CAR_ID;
import static integrationTest.api.data.DataIT.GOL_CAR_NAME;
import static integrationTest.api.data.DataIT.KWID_CAR_ID;
import static integrationTest.api.data.DataIT.MT_ZERO_CAR_DESCRIPTION;
import static integrationTest.api.data.DataIT.MT_ZERO_DAILY_RATE;
import static integrationTest.api.data.DataIT.MT_ZERO_LICENSE_PLATE;
import static integrationTest.api.data.DataIT.MT_ZERO_NINE_CAR_ID;
import static integrationTest.api.data.DataIT.MT_ZERO_NINE_CAR_NAME;
import static integrationTest.api.data.DataIT.SERIE_THREE_CAR_ID;
import static integrationTest.api.data.DataIT.VOLKSWAGEN_ID;
import static integrationTest.api.data.DataIT.YAMAHA_ID;
import static com.automobilefleet.utils.JsonMapper.asJsonString;
import static java.util.UUID.fromString;
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


class CarControllerIT extends AbstractWebIntegrationTest {

    @Container
    @ServiceConnection
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");
    @Autowired
    private MockMvc mockMvc;
    private final static Faker faker = new Faker();
    private final static String ENDPOINT = "/integrationTest/api/v1/cars";
    private final static String ENDPOINT_ID = ENDPOINT + "/{id}";

    @BeforeAll
    static void beforeAll() {
        var flyway = Flyway.configure()
                .dataSource(postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword())
                .load();

        flyway.migrate();
    }

    @Test
    void shouldGetCarByIdAndStatusCodeOK() throws Exception {
        mockMvc.perform(get(ENDPOINT_ID, MT_ZERO_NINE_CAR_ID).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$._id").value(MT_ZERO_NINE_CAR_ID))
                .andExpect(jsonPath("$.name").value(MT_ZERO_NINE_CAR_NAME))
                .andExpect(jsonPath("$.description").value(MT_ZERO_CAR_DESCRIPTION))
                .andExpect(jsonPath("$.daily_rate").value(MT_ZERO_DAILY_RATE))
                .andExpect(jsonPath("$.license_plate").value(MT_ZERO_LICENSE_PLATE))
                .andExpect(jsonPath("$.brand._id").value(YAMAHA_ID))
                .andExpect(jsonPath("$.category._id").value(CATEGORY_HATCH_ID));
    }

    @Test
    void shouldGetListCarByAvailableNameAndStatusCodeOK() throws Exception {
        mockMvc.perform(get(ENDPOINT + "/available")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0]._id").value(SERIE_THREE_CAR_ID))
                .andExpect(jsonPath("$[1]._id").value(KWID_CAR_ID))
                .andExpect(jsonPath("$[2]._id").value(COROLLA_CAR_ID))
                .andExpect(jsonPath("$[3]._id").value(GOL_CAR_ID))
                .andExpect(jsonPath("$[4]._id").value(CIVIC_CAR_ID));
    }

    @Test
    void shouldGetListCarByBrandNameAndStatusCodeOK() throws Exception {
        mockMvc.perform(get(ENDPOINT)
                        .param("brand_name", BRAND_VOLKSWAGEN)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0]._id").value(GOL_CAR_ID))
                .andExpect(jsonPath("$[0].name").value(GOL_CAR_NAME))
                .andExpect(jsonPath("$[0].description").value(GOL_CAR_DESCRIPTION));
    }

    @Test
    void shouldGetListCarAndStatusCodeOK() throws Exception {
        mockMvc.perform(get(ENDPOINT).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(jsonPath("$[0]._id").value(SERIE_THREE_CAR_ID))
                .andExpect(jsonPath("$[9]._id").value(CIVIC_CAR_ID));
    }

    @Test
    void shouldGetPagetCarAndStatusCodeOK() throws Exception {
        mockMvc.perform(get(ENDPOINT)
                        .param("page", "3")
                        .param("size", "3")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[*]._id").value(containsInAnyOrder(CIVIC_CAR_ID)))
                .andExpect(jsonPath("$.content[*].name").value(containsInAnyOrder(CIVIC_CAR_NAME)))
                .andExpect(jsonPath("$.totalElements").value(10))
                .andExpect(jsonPath("$.totalPages").value(4));
    }

    @Test
    void shoulSaveCarAndStatusCodeCreated() throws Exception {
        var name = faker.harryPotter().book();
        var description = faker.harryPotter().quote();
        var dailyRate = 91.33;
        var available = true;
        var licensePlate = "XYT-3322";
        var brandId = fromString(FERRARI_ID);
        var categoryId = fromString(CATEGORY_SUVs_ID);
        var color = "purple";
        var request = new CarRequest(name, description, dailyRate, available, licensePlate, brandId, categoryId, color);

        mockMvc.perform(post(ENDPOINT).contentType(APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$._id").isNotEmpty())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.description").value(description))
                .andExpect(jsonPath("$.daily_rate").value(dailyRate))
                .andExpect(jsonPath("$.license_plate").value(licensePlate))
                .andExpect(jsonPath("$.brand._id").value(FERRARI_ID))
                .andExpect(jsonPath("$.category._id").value(CATEGORY_SUVs_ID))
                .andExpect(jsonPath("$.color").value(color))
                .andExpect(jsonPath("$.created_at").isNotEmpty());
    }

    @Test
    void shouldUpdateCarAndStatusCodeAccepted() throws Exception {
        var name = faker.harryPotter().book();
        var description = faker.harryPotter().quote();
        var brandId = fromString(VOLKSWAGEN_ID);
        var categoryId = fromString(CATEGORY_TRUCKS_ID);
        var color = "Light-blue";
        var request = new CarRequest(name, description, COROLLA_DAILY_RATE, COROLLA_AVAILABLE, COROLLA_LICENSE_PLATE, brandId, categoryId, color);

        mockMvc.perform(put(ENDPOINT_ID, COROLLA_CAR_ID).contentType(APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$._id").value(COROLLA_CAR_ID))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.description").value(description))
                .andExpect(jsonPath("$.brand._id").value(VOLKSWAGEN_ID))
                .andExpect(jsonPath("$.category._id").value(CATEGORY_TRUCKS_ID))
                .andExpect(jsonPath("$.color").value(color));
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