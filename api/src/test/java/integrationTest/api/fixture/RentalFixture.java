package integrationTest.api.fixture;

import com.automobilefleet.api.dto.request.CategoryRequest;
import com.automobilefleet.api.dto.request.RentalRequest;
import integrationTest.api.utils.BodyBuilder;
import io.restassured.response.Response;
import lombok.experimental.UtilityClass;

import static integrationTest.api.data.DataIT.USER_SUPER_ID;
import static integrationTest.api.data.DataIT.USER_SUPER_NAME;
import static integrationTest.api.data.DataIT.USER_SUPER_ROLE;
import static integrationTest.api.utils.RequestSpecUtils.jwt;
import static integrationTest.api.utils.RequestSpecUtils.v1;
import static io.restassured.RestAssured.given;

@UtilityClass
public class RentalFixture {

    public static Response getRentalById(String rentalId) {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .pathParam("rentalId", rentalId)
                .when()
                .get("/rental/{rentalId}");
    }

    public static Response getRentalList() {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .when()
                .get("/rental");
    }

    public static Response getRentalPaged(int page, int size) {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .queryParam("page", page)
                .queryParam("size", size)
                .when()
                .get("/rental");
    }

    public static Response postRental(BodyBuilder body) {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .body(body.build())
                .when()
                .post("/rental");
    }

    public static Response postRental(RentalRequest request) {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .body(request)
                .when()
                .post("/rental");
    }

    public static Response putRental(String rentalId, CategoryRequest request) {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .pathParam("rentalId", rentalId)
                .body(request)
                .when()
                .put("/rental/{rentalId}");
    }

    public static Response deleteRental(String rentalId) {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .pathParam("rentalId", rentalId)
                .when()
                .delete("/rental/{rentalId}");
    }
}
