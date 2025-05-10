package integrationTest.api;

import com.automobilefleet.api.dto.request.SpecificationRequest;
import com.automobilefleet.api.dto.response.SpecificationResponse;
import integrationTest.api.config.AbstractWebIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static integrationTest.api.data.DataIT.SPECIFICATION_BRAKES;
import static integrationTest.api.data.DataIT.SPECIFICATION_BRAKES_DESCRIPTION;
import static integrationTest.api.data.DataIT.SPECIFICATION_BRAKES_ID;
import static integrationTest.api.data.DataIT.SPECIFICATION_ENGINE_ID;
import static integrationTest.api.data.DataIT.SPECIFICATION_EXCHANGE_ID;
import static integrationTest.api.data.DataIT.SPECIFICATION_HEIGHT_ID;
import static integrationTest.api.data.DataIT.SPECIFICATION_LENGTH_ID;
import static integrationTest.api.data.DataIT.SPECIFICATION_STEERING_WHEEL_ID;
import static integrationTest.api.data.DataIT.SPECIFICATION_SUSPENSION_ID;
import static integrationTest.api.data.DataIT.SPECIFICATION_WEIGHT_ID;
import static integrationTest.api.fixture.SpecificationFixture.getSpecificationById;
import static integrationTest.api.fixture.SpecificationFixture.getSpecificationList;
import static integrationTest.api.fixture.SpecificationFixture.getSpecificationPaged;
import static integrationTest.api.fixture.SpecificationFixture.postSpecification;
import static integrationTest.api.fixture.SpecificationFixture.putSpecification;
import static org.assertj.core.api.BDDAssertions.then;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

class SpecificationControllerIT extends AbstractWebIntegrationTest {

    @Test
    void shouldGetListSpecificationsAndStatusCodeOK() {
        getSpecificationList()
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("_id", hasItem(SPECIFICATION_ENGINE_ID))
                .body("_id", hasItem(SPECIFICATION_EXCHANGE_ID))
                .body("_id", hasItem(SPECIFICATION_STEERING_WHEEL_ID));
    }

    @Test
    void shouldGetSpecificationByIdAndStatusCodeOK() {
        getSpecificationById(SPECIFICATION_BRAKES_ID)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("_id", is(SPECIFICATION_BRAKES_ID))
                .body("name", is(SPECIFICATION_BRAKES))
                .body("description", is(SPECIFICATION_BRAKES_DESCRIPTION));
    }

    @Test
    void shouldGetPagedSpecificationAndStatusCodeOK() {
        getSpecificationPaged(2, 3)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("content._id", hasItem(SPECIFICATION_WEIGHT_ID))
                .body("content._id", hasItems(SPECIFICATION_LENGTH_ID))
                .body("content._id", hasItems(SPECIFICATION_HEIGHT_ID))
                .body("pageable.pageNumber", is(2))
                .body("pageable.pageSize", is(3));
    }

    @Test
    void shouldSaveSpecificationAndStatusCodeCreated() {
        var name = faker.harryPotter().character();
        var description = faker.harryPotter().location();
        var request = new SpecificationRequest(name, description);

        var response = postSpecification(request)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(SpecificationResponse.class);

        then(response.id()).isNotNull();
        then(response.name()).isEqualTo(request.name());
        then(response.description()).isEqualTo(request.description());
        then(response.createdAt()).isNotNull();
    }

    @Test
    void shouldUpdateSpecificationAndStatusCodeAccepted() {
        var name = faker.harryPotter().character();
        var description = faker.harryPotter().location();
        var request = new SpecificationRequest(name, description);

        var response = putSpecification(SPECIFICATION_SUSPENSION_ID, request)
                .then()
                .statusCode(HttpStatus.ACCEPTED.value())
                .extract()
                .as(SpecificationResponse.class);

        then(response.id().toString()).isEqualTo(SPECIFICATION_SUSPENSION_ID);
        then(response.name()).isEqualTo(request.name());
        then(response.description()).isEqualTo(request.description());
    }
}