package integrationTest.api;

import integrationTest.api.config.AbstractWebIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static integrationTest.api.data.DataIT.BRAND_VOLKSWAGEN;
import static integrationTest.api.data.DataIT.CAMARO_CAR_ID;
import static integrationTest.api.data.DataIT.CATEGORY_HATCH_ID;
import static integrationTest.api.data.DataIT.COROLLA_CAR_ID;
import static integrationTest.api.data.DataIT.COROLLA_CAR_NAME;
import static integrationTest.api.data.DataIT.GOLF_CAR_ID;
import static integrationTest.api.data.DataIT.GOL_CAR_ID;
import static integrationTest.api.data.DataIT.HILUX_CAR_ID;
import static integrationTest.api.data.DataIT.KWID_CAR_ID;
import static integrationTest.api.data.DataIT.KWID_CAR_NAME;
import static integrationTest.api.data.DataIT.MARCH_CAR_ID;
import static integrationTest.api.data.DataIT.MT_ZERO_CAR_DESCRIPTION;
import static integrationTest.api.data.DataIT.MT_ZERO_DAILY_RATE;
import static integrationTest.api.data.DataIT.MT_ZERO_LICENSE_PLATE;
import static integrationTest.api.data.DataIT.MT_ZERO_NINE_CAR_ID;
import static integrationTest.api.data.DataIT.MT_ZERO_NINE_CAR_NAME;
import static integrationTest.api.data.DataIT.ONIX_CAR_ID;
import static integrationTest.api.data.DataIT.SERIE_THREE_CAR_ID;
import static integrationTest.api.data.DataIT.SERIE_THREE_CAR_NAME;
import static integrationTest.api.data.DataIT.YAMAHA_ID;
import static integrationTest.api.fixture.CarFixture.getCarByBrandName;
import static integrationTest.api.fixture.CarFixture.getCarById;
import static integrationTest.api.fixture.CarFixture.getCarList;
import static integrationTest.api.fixture.CarFixture.getCarListAvailable;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

class CarControllerIT extends AbstractWebIntegrationTest {

    @Test
    void shouldGetCarByIdAndStatusCodeOK() {
        getCarById(MT_ZERO_NINE_CAR_ID)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("_id", is(MT_ZERO_NINE_CAR_ID))
                .body("name", is(MT_ZERO_NINE_CAR_NAME))
                .body("description", is(MT_ZERO_CAR_DESCRIPTION))
                .body("daily_rate", is(MT_ZERO_DAILY_RATE))
                .body("license_plate", is(MT_ZERO_LICENSE_PLATE))
                .body("brand._id", is(YAMAHA_ID))
                .body("category._id", is(CATEGORY_HATCH_ID));
    }

    @Test
    void shouldGetListCarAndStatusCodeOK() {
        getCarList()
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("_id", hasItems(SERIE_THREE_CAR_ID, KWID_CAR_ID, COROLLA_CAR_ID))
                .body("name", hasItems(SERIE_THREE_CAR_NAME, KWID_CAR_NAME, COROLLA_CAR_NAME));
    }

    @Test
    void shouldGetListCarByAvailableAndStatusCodeOK() {
        getCarListAvailable()
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("_id", hasItems(GOLF_CAR_ID, CAMARO_CAR_ID, HILUX_CAR_ID))
                .body("_id", not(hasItem(SERIE_THREE_CAR_ID)))
                .body("_id", not(hasItem(ONIX_CAR_ID)))
                .body("_id", not(hasItem(MARCH_CAR_ID)));
    }

    @Test
    void shouldGetListCarByBrandNameAndStatusCodeOK() {
        getCarByBrandName(BRAND_VOLKSWAGEN)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("_id", hasItems(GOL_CAR_ID, GOLF_CAR_ID));
    }

//    @Test
//    void shouldGetListCarAndStatusCodeOK() throws Exception {
//        mockMvc.perform(get(ENDPOINT).contentType(APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
//                .andExpect(content().contentType(APPLICATION_JSON))
//                .andExpect(jsonPath("$", hasSize(10)))
//                .andExpect(jsonPath("$[0]._id").value(SERIE_THREE_CAR_ID))
//                .andExpect(jsonPath("$[9]._id").value(CIVIC_CAR_ID));
//    }
//
//    @Test
//    void shouldGetPagetCarAndStatusCodeOK() throws Exception {
//        mockMvc.perform(get(ENDPOINT)
//                        .param("page", "3")
//                        .param("size", "3")
//                        .contentType(APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
//                .andExpect(content().contentType(APPLICATION_JSON))
//                .andExpect(jsonPath("$.content", hasSize(1)))
//                .andExpect(jsonPath("$.content[*]._id").value(containsInAnyOrder(CIVIC_CAR_ID)))
//                .andExpect(jsonPath("$.content[*].name").value(containsInAnyOrder(CIVIC_CAR_NAME)))
//                .andExpect(jsonPath("$.totalElements").value(10))
//                .andExpect(jsonPath("$.totalPages").value(4));
//    }
//
//    @Test
//    void shoulSaveCarAndStatusCodeCreated() throws Exception {
//        var name = faker.harryPotter().book();
//        var description = faker.harryPotter().quote();
//        var dailyRate = 91.33;
//        var available = true;
//        var licensePlate = "XYT-3322";
//        var brandId = fromString(FERRARI_ID);
//        var categoryId = fromString(CATEGORY_SUVs_ID);
//        var color = "purple";
//        var request = new CarRequest(name, description, dailyRate, available, licensePlate, brandId, categoryId, color);
//
//        mockMvc.perform(post(ENDPOINT).contentType(APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(request)))
//                .andExpect(status().isCreated())
//                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
//                .andExpect(content().contentType(APPLICATION_JSON))
//                .andExpect(jsonPath("$._id").isNotEmpty())
//                .andExpect(jsonPath("$.name").value(name))
//                .andExpect(jsonPath("$.description").value(description))
//                .andExpect(jsonPath("$.daily_rate").value(dailyRate))
//                .andExpect(jsonPath("$.license_plate").value(licensePlate))
//                .andExpect(jsonPath("$.brand._id").value(FERRARI_ID))
//                .andExpect(jsonPath("$.category._id").value(CATEGORY_SUVs_ID))
//                .andExpect(jsonPath("$.color").value(color))
//                .andExpect(jsonPath("$.created_at").isNotEmpty());
//    }
//
//    @Test
//    void shouldUpdateCarAndStatusCodeAccepted() throws Exception {
//        var name = faker.harryPotter().book();
//        var description = faker.harryPotter().quote();
//        var brandId = fromString(VOLKSWAGEN_ID);
//        var categoryId = fromString(CATEGORY_TRUCKS_ID);
//        var color = "Light-blue";
//        var request = new CarRequest(name, description, COROLLA_DAILY_RATE, COROLLA_AVAILABLE, COROLLA_LICENSE_PLATE, brandId, categoryId, color);
//
//        mockMvc.perform(put(ENDPOINT_ID, COROLLA_CAR_ID).contentType(APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(request)))
//                .andExpect(status().isAccepted())
//                .andExpect(jsonPath("$._id").value(COROLLA_CAR_ID))
//                .andExpect(jsonPath("$.name").value(name))
//                .andExpect(jsonPath("$.description").value(description))
//                .andExpect(jsonPath("$.brand._id").value(VOLKSWAGEN_ID))
//                .andExpect(jsonPath("$.category._id").value(CATEGORY_TRUCKS_ID))
//                .andExpect(jsonPath("$.color").value(color));
//    }
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