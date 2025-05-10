package integrationTest.api;

import com.automobilefleet.api.dto.request.CarSpecificationRequest;
import com.automobilefleet.api.dto.response.CarSpecificationResponse;
import integrationTest.api.config.AbstractWebIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static integrationTest.api.data.DataIT.CAR_SPECIFICATION_BMW_ID;
import static integrationTest.api.data.DataIT.CAR_SPECIFICATION_KWID_ID;
import static integrationTest.api.data.DataIT.CAR_SPECIFICATION_MUSTANG;
import static integrationTest.api.data.DataIT.CAR_SPECIFICATION_ONIX_ID;
import static integrationTest.api.data.DataIT.CIVIC_CAR_ID;
import static integrationTest.api.data.DataIT.COROLLA_CAR_ID;
import static integrationTest.api.data.DataIT.COROLLA_CAR_NAME;
import static integrationTest.api.data.DataIT.MUSTANG_CAR_ID;
import static integrationTest.api.data.DataIT.SPECIFICATION_HEIGHT_ID;
import static integrationTest.api.data.DataIT.SPECIFICATION_TANK_CAPACITY;
import static integrationTest.api.data.DataIT.SPECIFICATION_TANK_CAPACITY_DESCRIPTION;
import static integrationTest.api.data.DataIT.SPECIFICATION_TANK_CAPACITY_ID;
import static integrationTest.api.data.DataIT.SPECIFICATION_WIDTH_ID;
import static integrationTest.api.fixture.CarSpecificationFixture.geCarSpecificationById;
import static integrationTest.api.fixture.CarSpecificationFixture.geCarSpecificationInfoById;
import static integrationTest.api.fixture.CarSpecificationFixture.getCarSpecificationList;
import static integrationTest.api.fixture.CarSpecificationFixture.postCarSpecification;
import static integrationTest.api.fixture.CarSpecificationFixture.putCarSpecification;
import static org.assertj.core.api.BDDAssertions.then;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

class CarSpecificationControllerIT extends AbstractWebIntegrationTest {

    @Test
    void shouldGetListCarSpecificationsAndStatusCodeOK() {
        getCarSpecificationList()
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("_id", hasItem(CAR_SPECIFICATION_ONIX_ID))
                .body("_id", hasItem(CAR_SPECIFICATION_BMW_ID));
    }

    @Test
    void shouldGetCarSpecificationByIdAndStatusCodeOK() {
        geCarSpecificationById(CAR_SPECIFICATION_MUSTANG)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("_id", is(CAR_SPECIFICATION_MUSTANG))
                .body("car._id", is(COROLLA_CAR_ID))
                .body("specification._id", is(SPECIFICATION_TANK_CAPACITY_ID));
    }

    @Test
    void shouldGetCarSpecificationInfoByIdAndStatusCodeOK() {
        geCarSpecificationInfoById(CAR_SPECIFICATION_MUSTANG)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", is(CAR_SPECIFICATION_MUSTANG))
                .body("carName", is(COROLLA_CAR_NAME))
                .body("specificationName", is(SPECIFICATION_TANK_CAPACITY))
                .body("specificationDescription", is(SPECIFICATION_TANK_CAPACITY_DESCRIPTION));
    }

    @Test
    void shouldSaveCarSpecificationAndStatusCodeCreated() {
        var carId = UUID.fromString(MUSTANG_CAR_ID);
        var specificationId = UUID.fromString(SPECIFICATION_WIDTH_ID);
        var request = new CarSpecificationRequest(carId, specificationId);

        var response = postCarSpecification(request)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(CarSpecificationResponse.class);

        then(response.id()).isNotNull();
        then(response.car().id()).isEqualTo(request.carId());
        then(response.specification().id()).isEqualTo(request.specificationId());
    }

    @Test
    void shouldUpdateCarSpecificationAndStatusCodeAccepted() {
        var carId = UUID.fromString(CIVIC_CAR_ID);
        var specificationId = UUID.fromString(SPECIFICATION_HEIGHT_ID);
        var request = new CarSpecificationRequest(carId, specificationId);

        var response = putCarSpecification(CAR_SPECIFICATION_KWID_ID, request)
                .then()
                .statusCode(HttpStatus.ACCEPTED.value())
                .extract()
                .as(CarSpecificationResponse.class);

        then(response.id().toString()).isEqualTo(CAR_SPECIFICATION_KWID_ID);
        then(response.car().id()).isEqualTo(request.carId());
        then(response.specification().id()).isEqualTo(request.specificationId());
    }
}