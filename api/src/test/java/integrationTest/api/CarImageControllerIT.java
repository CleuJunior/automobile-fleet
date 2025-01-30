package integrationTest.api;

import com.automobilefleet.api.dto.request.CarImageRequest;
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

import static integrationTest.api.data.DataIT.CAR_IMAGE_LINK_PLACEHOLD;
import static integrationTest.api.data.DataIT.CIVIC_CAR_ID;
import static integrationTest.api.data.DataIT.CIVIC_IMAGE_ID;
import static integrationTest.api.data.DataIT.COROLLA_CAR_ID;
import static integrationTest.api.data.DataIT.COROLLA_IMAGE_ID;
import static integrationTest.api.data.DataIT.CREATED_AT_FOUR;
import static integrationTest.api.data.DataIT.CREATED_AT_SIX;
import static integrationTest.api.data.DataIT.FOUR_EIGHT_EIGHT_CAR_ID;
import static integrationTest.api.data.DataIT.FOUR_EIGHT_EIGHT_IMAGE_ID;
import static integrationTest.api.data.DataIT.GOL_CAR_ID;
import static integrationTest.api.data.DataIT.GOL_IMAGE_ID;
import static integrationTest.api.data.DataIT.KWID_CAR_ID;
import static integrationTest.api.data.DataIT.KWID_IMAGE_ID;
import static integrationTest.api.data.DataIT.MARCH_CAR_ID;
import static integrationTest.api.data.DataIT.MARCH_IMAGE_ID;
import static integrationTest.api.data.DataIT.MT_ZERO_IMAGE_ID;
import static integrationTest.api.data.DataIT.MT_ZERO_NINE_CAR_ID;
import static integrationTest.api.data.DataIT.MUSTANG_CAR_ID;
import static integrationTest.api.data.DataIT.MUSTANG_IMAGE_ID;
import static integrationTest.api.data.DataIT.ONIX_CAR_ID;
import static integrationTest.api.data.DataIT.ONIX_IMAGE_ID;
import static integrationTest.api.data.DataIT.SERIE_THREE_CAR_ID;
import static integrationTest.api.data.DataIT.SERIE_THREE_IMAGE_ID;
import static java.util.UUID.fromString;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CarImageControllerIT extends AbstractWebIntegrationTest {

    @Container
    @ServiceConnection
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    private final static Faker faker = new Faker();
    private final static String ENDPOINT = "/integrationTest/api/v1/car_images";
    private final static String ENDPOINT_ID = ENDPOINT + "/{id}";

    @BeforeAll
    static void beforeAll() {
        var flyway = Flyway.configure()
                .dataSource(postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword())
                .load();

        flyway.migrate();
    }

    @Test
    void shouldGetImagedByIdAndStatusCodeOK() throws Exception {
        mockMvc.perform(get(ENDPOINT_ID, COROLLA_IMAGE_ID).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$._id").value(COROLLA_IMAGE_ID))
                .andExpect(jsonPath("$.car_id").value(COROLLA_CAR_ID))
                .andExpect(jsonPath("$.image").value(CAR_IMAGE_LINK_PLACEHOLD))
                .andExpect(jsonPath("$.created_at").value(CREATED_AT_SIX));
    }

    @Test
    void shouldGetListImagesAndStatusCodeOK() throws Exception {
        mockMvc.perform(get(ENDPOINT).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(jsonPath("$[*]._id").value(containsInAnyOrder(SERIE_THREE_IMAGE_ID, ONIX_IMAGE_ID, MT_ZERO_IMAGE_ID, KWID_IMAGE_ID, MARCH_IMAGE_ID, COROLLA_IMAGE_ID, MUSTANG_IMAGE_ID, GOL_IMAGE_ID, FOUR_EIGHT_EIGHT_IMAGE_ID, CIVIC_IMAGE_ID)))
                .andExpect(jsonPath("$[*].car_id").value(containsInAnyOrder(SERIE_THREE_CAR_ID, ONIX_CAR_ID, MT_ZERO_NINE_CAR_ID, KWID_CAR_ID, MARCH_CAR_ID, COROLLA_CAR_ID, MUSTANG_CAR_ID, GOL_CAR_ID, FOUR_EIGHT_EIGHT_CAR_ID, CIVIC_CAR_ID)));
    }

    @Test
    void shouldGetPagetImageAndStatusCodeOK() throws Exception {
        mockMvc.perform(get(ENDPOINT)
                        .param("page", "1")
                        .param("size", "4")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(4)))
                .andExpect(jsonPath("$.content[*]._id").value(containsInAnyOrder(MARCH_IMAGE_ID, COROLLA_IMAGE_ID, MUSTANG_IMAGE_ID, GOL_IMAGE_ID)))
                .andExpect(jsonPath("$.content[*].car_id").value(containsInAnyOrder(MARCH_CAR_ID, COROLLA_CAR_ID, MUSTANG_CAR_ID, GOL_CAR_ID)))
                .andExpect(jsonPath("$.totalElements").value(10))
                .andExpect(jsonPath("$.totalPages").value(3));
    }

    @Test
    void shoulSaveImageAndStatusCodeCreated() throws Exception {
        var linkImage = faker.buffy().quotes();
        var carId = fromString(FOUR_EIGHT_EIGHT_CAR_ID);
        var request = new CarImageRequest(carId, linkImage);

        mockMvc.perform(post(ENDPOINT).contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$._id").isNotEmpty())
                .andExpect(jsonPath("$.car_id").value(FOUR_EIGHT_EIGHT_CAR_ID))
                .andExpect(jsonPath("$.image").value(linkImage))
                .andExpect(jsonPath("$.created_at").isNotEmpty());
    }

    @Test
    void shouldUpdateImageAndStatusCodeAccepted() throws Exception {
        var linkImage = faker.buffy().quotes();
        var carId = fromString(KWID_CAR_ID);
        var request = new CarImageRequest(carId, linkImage);

        mockMvc.perform(put(ENDPOINT_ID, KWID_IMAGE_ID).contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$._id").value(KWID_IMAGE_ID))
                .andExpect(jsonPath("$.car_id").value(KWID_CAR_ID))
                .andExpect(jsonPath("$.image").value(linkImage))
                .andExpect(jsonPath("$.created_at").value(CREATED_AT_FOUR));

    }

    @Test
    void shouldDeleteImageAndStatusCodeNoContent() throws Exception {
        mockMvc.perform(delete(ENDPOINT_ID, KWID_IMAGE_ID).contentType(APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}