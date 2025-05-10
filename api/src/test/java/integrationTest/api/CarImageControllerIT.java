package integrationTest.api;

import com.automobilefleet.api.dto.request.CarImageRequest;
import com.automobilefleet.api.dto.response.CarImageResponse;
import integrationTest.api.config.AbstractWebIntegrationTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static integrationTest.api.data.DataIT.CAR_IMAGE_LINK_PLACEHOLD;
import static integrationTest.api.data.DataIT.COROLLA_CAR_ID;
import static integrationTest.api.data.DataIT.COROLLA_IMAGE_ID;
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
import static integrationTest.api.fixture.CarImageFixture.deleteCarImage;
import static integrationTest.api.fixture.CarImageFixture.getCarImageById;
import static integrationTest.api.fixture.CarImageFixture.getCarImagesList;
import static integrationTest.api.fixture.CarImageFixture.getCarImagesPaged;
import static integrationTest.api.fixture.CarImageFixture.postCarImage;
import static integrationTest.api.fixture.CarImageFixture.putCarImage;
import static java.util.UUID.fromString;
import static org.assertj.core.api.BDDAssertions.then;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.text.IsEmptyString.emptyString;

//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CarImageControllerIT extends AbstractWebIntegrationTest {

    @Test
    void shouldGetImagedByIdAndStatusCodeOK() {
        getCarImageById(COROLLA_IMAGE_ID)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("_id", is(COROLLA_IMAGE_ID))
                .body("car_id", is(COROLLA_CAR_ID))
                .body("image", is(CAR_IMAGE_LINK_PLACEHOLD));
    }

    @Test
    void shouldGetListImagesAndStatusCodeOK() {
        getCarImagesList()
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("_id", hasItem(COROLLA_IMAGE_ID))
                .body("_id", hasItem(ONIX_IMAGE_ID))
                .body("_id", hasItem(MT_ZERO_IMAGE_ID))
                .body("car_id", hasItem(SERIE_THREE_CAR_ID))
                .body("car_id", hasItem(ONIX_CAR_ID))
                .body("car_id", hasItem(MT_ZERO_NINE_CAR_ID));
    }

    @Test
    void shouldGetPagedImageAndStatusCodeOK() {
        getCarImagesPaged(1, 4)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("content._id", hasItem(COROLLA_IMAGE_ID))
                .body("content._id", hasItem(MUSTANG_IMAGE_ID))
                .body("content._id", hasItem(GOL_IMAGE_ID))
                .body("content.car_id", hasItem(COROLLA_CAR_ID))
                .body("content.car_id", hasItem(MUSTANG_CAR_ID))
                .body("content.car_id", hasItem(GOL_CAR_ID))
                .body("pageable.pageNumber", is(1))
                .body("pageable.pageSize", is(4));
    }

    @Test
    void shouldSaveImageAndStatusCodeCreated() {
        var linkImage = faker.buffy().episodes();
        var carId = fromString(FOUR_EIGHT_EIGHT_CAR_ID);
        var request = new CarImageRequest(carId, linkImage);

        var response = postCarImage(request)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(CarImageResponse.class);

        then(response.id()).isNotNull();
        then(response.carId()).isEqualTo(carId);
        then(response.linkImage()).isEqualTo(linkImage);
    }

    @Test
    void shouldUpdateImageAndStatusCodeAccepted() {
        var linkImage = faker.buffy().celebrities();
        var carId = fromString(KWID_CAR_ID);
        var request = new CarImageRequest(carId, linkImage);

        var response = putCarImage(KWID_IMAGE_ID, request)
                .then()
                .statusCode(HttpStatus.ACCEPTED.value())
                .extract()
                .as(CarImageResponse.class);

        then(response.id().toString()).isEqualTo(KWID_IMAGE_ID);
        then(response.carId()).isEqualTo(carId);
        then(response.linkImage()).isEqualTo(linkImage);
    }

    @Test
    void shouldDeleteImageAndStatusCodeNoContent() {
        deleteCarImage(FOUR_EIGHT_EIGHT_IMAGE_ID)
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value())
                .header("Content-Type", nullValue())
                .body(emptyString());
    }
}