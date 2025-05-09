package integrationTest.api;

import com.automobilefleet.api.dto.request.RentalRequest;
import com.automobilefleet.api.dto.response.CategoryResponse;
import com.automobilefleet.api.dto.response.RentalResponse;
import integrationTest.api.config.AbstractWebIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

import static integrationTest.api.data.DataIT.CATEGORY_COUPE_ID;
import static integrationTest.api.data.DataIT.CIVIC_CAR_ID;
import static integrationTest.api.data.DataIT.FERNANDO_ID;
import static integrationTest.api.data.DataIT.FIESTA_CAR_ID;
import static integrationTest.api.data.DataIT.FOUR_EIGHT_EIGHT_CAR_ID;
import static integrationTest.api.data.DataIT.KWID_CAR_ID;
import static integrationTest.api.data.DataIT.LUANA_ID;
import static integrationTest.api.data.DataIT.MT_ZERO_NINE_CAR_ID;
import static integrationTest.api.data.DataIT.MUSTANG_CAR_ID;
import static integrationTest.api.data.DataIT.RENTAL_488_ID;
import static integrationTest.api.data.DataIT.RENTAL_MT9_ID;
import static integrationTest.api.data.DataIT.RENTAL_ONIX_ID;
import static integrationTest.api.data.DataIT.RENTAL_SEDAN_ID;
import static integrationTest.api.fixture.CategoryFixture.putCategory;
import static integrationTest.api.fixture.RentalFixture.getRentalById;
import static integrationTest.api.fixture.RentalFixture.getRentalList;
import static integrationTest.api.fixture.RentalFixture.postRental;
import static java.util.UUID.fromString;
import static org.assertj.core.api.BDDAssertions.then;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



class RentalControllerIT extends AbstractWebIntegrationTest {


    @Test
    void shouldGetListRentalAndStatusCodeOK() {
        getRentalList()
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("_id", hasItem(RENTAL_SEDAN_ID))
                .body("_id", hasItem(RENTAL_ONIX_ID))
                .body("_id", hasItem(RENTAL_MT9_ID));
    }

    @Test
    void shouldRentalByIdAndStatusCodeOK() {
        getRentalById(RENTAL_488_ID)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("_id", is(RENTAL_488_ID))
                .body("car._id", is(FOUR_EIGHT_EIGHT_CAR_ID))
                .body("customer._id", is(FERNANDO_ID));
    }

    @Test
    void shouldSaveRentalAndStatusCodeCreated() {
        var carId = fromString(FIESTA_CAR_ID);
        var customerId = fromString(LUANA_ID);
        var startDate = LocalDate.now();
        var endDate = startDate.plusDays(3);
        var request = new RentalRequest(carId, customerId, startDate, endDate);

        var response = postRental(request)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(RentalResponse.class);

        then(response.id()).isNotNull();
        then(response.car().id()).isEqualTo(request.carId());
        then(response.customer().id()).isEqualTo(request.customerId());
        then(response.startDate()).isEqualTo(request.startDate());
        then(response.endDate()).isEqualTo(request.endDate());
        then(response.total()).isEqualTo(226.5);
        then(response.createdAt()).isNotNull();
        then(response.updatedAt()).isNotNull();
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