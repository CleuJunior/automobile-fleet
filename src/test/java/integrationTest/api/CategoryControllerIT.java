package integrationTest.api;

import com.automobilefleet.api.dto.request.CategoryRequest;
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

import static integrationTest.api.data.DataIT.CATEGORY_LUXURY_CARS_DESCRIPTION;
import static integrationTest.api.data.DataIT.CATEGORY_LUXURY_CARS_ID;
import static integrationTest.api.data.DataIT.CATEGORY_LUXURY_CARS_NAME;
import static integrationTest.api.data.DataIT.CATEGORY_OLD_CARS_ID;
import static integrationTest.api.data.DataIT.CATEGORY_SPORTING_CAR_ID;
import static integrationTest.api.data.DataIT.CATEGORY_SUVs_ID;
import static integrationTest.api.data.DataIT.UPDATED_AT_FIVE;
import static integrationTest.api.data.DataIT.UPDATED_AT_SEVEN;
import static com.automobilefleet.utils.JsonMapper.asJsonString;
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


class CategoryControllerIT extends AbstractWebIntegrationTest {

    @Container
    @ServiceConnection
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");
    @Autowired
    private MockMvc mockMvc;
    private final static Faker faker = new Faker();
    private final static String ENDPOINT = "/integrationTest/api/v1/category";
    private final static String ENDPOINT_ID = ENDPOINT + "/{id}";

    @BeforeAll
    static void beforeAll() {
        var flyway = Flyway.configure()
                .dataSource(postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword())
                .load();

        flyway.migrate();
    }

    @Test
    void shouldCategoryByIdAndStatusCodeOK() throws Exception {
        mockMvc.perform(get(ENDPOINT_ID, CATEGORY_LUXURY_CARS_ID).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$._id").value(CATEGORY_LUXURY_CARS_ID))
                .andExpect(jsonPath("$.name").value(CATEGORY_LUXURY_CARS_NAME))
                .andExpect(jsonPath("$.description").value(CATEGORY_LUXURY_CARS_DESCRIPTION))
                .andExpect(jsonPath("$.created_at").value(UPDATED_AT_SEVEN));
    }

    @Test
    void shouldGetListCategoryAndStatusCodeOK() throws Exception {
        mockMvc.perform(get(ENDPOINT).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(jsonPath("$.[0]._id").value(CATEGORY_SUVs_ID))
                .andExpect(jsonPath("$.[9]._id").value(CATEGORY_OLD_CARS_ID));
    }

    @Test
    void shoulSaveCategoryAndStatusCodeCreated() throws Exception {
        var name = faker.leagueOfLegends().champion();
        var description = faker.leagueOfLegends().quote();
        var request = new CategoryRequest(name, description);

        mockMvc.perform(post(ENDPOINT).contentType(APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$._id").isNotEmpty())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.description").value(description))
                .andExpect(jsonPath("$.created_at").isNotEmpty());
    }

    @Test
    void shouldUpdateBrandAndStatusCodeAccepted() throws Exception {
        var name = faker.harryPotter().character();
        var description = faker.harryPotter().quote();
        var request = new CategoryRequest(name, description);

        mockMvc.perform(put(ENDPOINT_ID, CATEGORY_SPORTING_CAR_ID).contentType(APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$._id").value(CATEGORY_SPORTING_CAR_ID))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.description").value(description))
                .andExpect(jsonPath("$.created_at").value(UPDATED_AT_FIVE));
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