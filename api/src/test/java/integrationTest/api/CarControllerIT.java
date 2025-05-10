package integrationTest.api;

import com.automobilefleet.api.dto.request.CarRequest;
import com.automobilefleet.api.dto.response.CarResponse;
import integrationTest.api.config.AbstractWebIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static integrationTest.api.data.DataIT.BRAND_CHEVROLET;
import static integrationTest.api.data.DataIT.BRAND_VOLKSWAGEN;
import static integrationTest.api.data.DataIT.CAMARO_CAR_ID;
import static integrationTest.api.data.DataIT.CATEGORY_ELETRIC_CARS_NAME;
import static integrationTest.api.data.DataIT.CATEGORY_HATCH_ID;
import static integrationTest.api.data.DataIT.CATEGORY_SUVs_ID;
import static integrationTest.api.data.DataIT.CIVIC_ID;
import static integrationTest.api.data.DataIT.COLOR_YELLOW;
import static integrationTest.api.data.DataIT.COROLLA_CAR_ID;
import static integrationTest.api.data.DataIT.COROLLA_CAR_NAME;
import static integrationTest.api.data.DataIT.FERRARI_ID;
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
import static integrationTest.api.data.DataIT.MUSTANG_CAR_ID;
import static integrationTest.api.data.DataIT.ONIX_CAR_DESCRIPTION;
import static integrationTest.api.data.DataIT.ONIX_CAR_ID;
import static integrationTest.api.data.DataIT.ONIX_CAR_NAME;
import static integrationTest.api.data.DataIT.ONIX_LICENSE_PLATE;
import static integrationTest.api.data.DataIT.SERIE_THREE_CAR_ID;
import static integrationTest.api.data.DataIT.SERIE_THREE_CAR_NAME;
import static integrationTest.api.data.DataIT.X5_CAR_ID;
import static integrationTest.api.data.DataIT.YAMAHA_ID;
import static integrationTest.api.fixture.CarFixture.getCarByBrandName;
import static integrationTest.api.fixture.CarFixture.getCarById;
import static integrationTest.api.fixture.CarFixture.getCarInfoById;
import static integrationTest.api.fixture.CarFixture.getCarList;
import static integrationTest.api.fixture.CarFixture.getCarListAvailable;
import static integrationTest.api.fixture.CarFixture.getCarPaged;
import static integrationTest.api.fixture.CarFixture.postCar;
import static integrationTest.api.fixture.CarFixture.putCar;
import static java.util.UUID.fromString;
import static org.assertj.core.api.BDDAssertions.then;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

class CarControllerIT extends AbstractWebIntegrationTest {

    @Test
    void shouldGetCarInfoByIdAndStatusCodeOK() {
        getCarInfoById(ONIX_CAR_ID)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", is(ONIX_CAR_ID))
                .body("name", is(ONIX_CAR_NAME))
                .body("description", is(ONIX_CAR_DESCRIPTION))
                .body("category", is(CATEGORY_ELETRIC_CARS_NAME))
                .body("brand", is(BRAND_CHEVROLET))
                .body("licensePlate", is(ONIX_LICENSE_PLATE))
                .body("color", is(COLOR_YELLOW));
    }

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
                .log().all()
                .body("_id", hasItem(GOLF_CAR_ID))
                .body("_id", hasItem(CAMARO_CAR_ID))
                .body("_id", hasItem(CIVIC_ID))
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


    @Test
    void shouldGetPagedCarAndStatusCodeOK() {
        getCarPaged(3, 3)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("content._id", hasItem(COROLLA_CAR_ID))
                .body("content._id", hasItem(X5_CAR_ID))
                .body("content._id", hasItem(MUSTANG_CAR_ID))
                .body("pageable.pageNumber", is(3))
                .body("pageable.pageSize", is(3));
    }

    @Test
    void shouldSaveCarAndStatusCodeCreated() {
        var name = faker.book().author();
        var description = faker.lorem().characters(9, 25, true);
        var dailyRate = faker.number().randomDouble(2, 98, 371);
        var available = faker.random().nextBoolean();
        var licensePlate = "XYT-3322";
        var brandId = fromString(FERRARI_ID);
        var categoryId = fromString(CATEGORY_SUVs_ID);
        var color = faker.color().name();
        var request = new CarRequest(name, description, dailyRate, available, licensePlate, brandId, categoryId, color);

        var response = postCar(request)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(CarResponse.class);

        then(response.id()).isNotNull();
        then(response.name()).isEqualTo(name);
        then(response.description()).isEqualTo(description);
        then(response.dailyRate()).isEqualTo(dailyRate);
        then(response.available()).isEqualTo(available);
        then(response.licensePlate()).isEqualTo(licensePlate);
        then(response.brand().id()).isEqualTo(brandId);
        then(response.category().id()).isEqualTo(categoryId);
        then(response.color()).isEqualTo(color);
        then(response.createdAt()).isNotNull();
    }

    @Test
    void shouldUpdateCarAndStatusCodeAccepted() {
        var name = faker.harryPotter().book();
        var description = faker.lorem().characters(9, 25, true);
        var dailyRate = faker.number().randomDouble(2, 98, 371);
        var available = faker.random().nextBoolean();
        var licensePlate = "XYT-3322";
        var brandId = fromString(FERRARI_ID);
        var categoryId = fromString(CATEGORY_SUVs_ID);
        var color = faker.color().name();

        var request = new CarRequest(name, description, dailyRate, available, licensePlate, brandId, categoryId, color);

        var response = putCar(HILUX_CAR_ID, request)
                .then()
                .statusCode(HttpStatus.ACCEPTED.value())
                .extract()
                .as(CarResponse.class);

        then(response.id().toString()).isEqualTo(HILUX_CAR_ID);
        then(response.name()).isEqualTo(name);
        then(response.description()).isEqualTo(description);
        then(response.dailyRate()).isEqualTo(dailyRate);
        then(response.available()).isEqualTo(available);
        then(response.licensePlate()).isEqualTo(licensePlate);
        then(response.brand().id()).isEqualTo(brandId);
        then(response.category().id()).isEqualTo(categoryId);
        then(response.color()).isEqualTo(color);
    }
}