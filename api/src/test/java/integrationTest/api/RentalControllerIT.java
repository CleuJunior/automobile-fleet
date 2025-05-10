package integrationTest.api;

import com.automobilefleet.api.dto.request.RentalRequest;
import com.automobilefleet.api.dto.response.RentalResponse;
import integrationTest.api.config.AbstractWebIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.UUID;

import static integrationTest.api.data.DataIT.FERNANDO_ID;
import static integrationTest.api.data.DataIT.FIESTA_CAR_ID;
import static integrationTest.api.data.DataIT.FOUR_EIGHT_EIGHT_CAR_ID;
import static integrationTest.api.data.DataIT.LOGAN_ID;
import static integrationTest.api.data.DataIT.LUANA_ID;
import static integrationTest.api.data.DataIT.RENTAL_488_ID;
import static integrationTest.api.data.DataIT.RENTAL_CIVIC_ID;
import static integrationTest.api.data.DataIT.RENTAL_GOL_ID;
import static integrationTest.api.data.DataIT.RENTAL_MT9_ID;
import static integrationTest.api.data.DataIT.RENTAL_ONIX_ID;
import static integrationTest.api.data.DataIT.RENTAL_SEDAN_ID;
import static integrationTest.api.fixture.RentalFixture.deleteRental;
import static integrationTest.api.fixture.RentalFixture.getRentalById;
import static integrationTest.api.fixture.RentalFixture.getRentalList;
import static integrationTest.api.fixture.RentalFixture.postRental;
import static integrationTest.api.fixture.RentalFixture.putRental;
import static org.assertj.core.api.BDDAssertions.then;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.text.IsEmptyString.emptyString;


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
        var carId = UUID.fromString(FIESTA_CAR_ID);
        var customerId = UUID.fromString(LUANA_ID);
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
    void shouldUpdateRentalAndStatusCodeAccepted() {
        var carId = UUID.fromString(LOGAN_ID);
        var customerId = UUID.fromString(LUANA_ID);
        var startDate = LocalDate.now();
        var endDate = startDate.plusDays(10);
        var request = new RentalRequest(carId, customerId, startDate, endDate);

        var response = putRental(RENTAL_CIVIC_ID, request)
                .then()
                .statusCode(HttpStatus.ACCEPTED.value())
                .extract()
                .as(RentalResponse.class);

        then(response.id().toString()).isEqualTo(RENTAL_CIVIC_ID);
        then(response.car().id()).isEqualTo(request.carId());
        then(response.customer().id()).isEqualTo(request.customerId());
        then(response.startDate()).isEqualTo(request.startDate());
        then(response.endDate()).isEqualTo(request.endDate());
        then(response.total()).isEqualTo(955.0);
    }

    @Test
    void shouldDeleteRentalAndStatusCodeNoContent() {
        deleteRental(RENTAL_GOL_ID)
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value())
                .header("Content-Type", nullValue())
                .body(emptyString());

        deleteRental(RENTAL_GOL_ID)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}