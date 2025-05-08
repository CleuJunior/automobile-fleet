package integrationTest.api.fixture;

import com.automobilefleet.api.dto.request.CarImageRequest;
import com.automobilefleet.api.dto.request.CarRequest;
import integrationTest.api.utils.BodyBuilder;
import io.restassured.response.Response;
import lombok.NoArgsConstructor;

import static integrationTest.api.data.DataIT.USER_SUPER_ID;
import static integrationTest.api.data.DataIT.USER_SUPER_NAME;
import static integrationTest.api.data.DataIT.USER_SUPER_ROLE;
import static integrationTest.api.utils.RequestSpecUtils.jwt;
import static integrationTest.api.utils.RequestSpecUtils.v1;
import static io.restassured.RestAssured.given;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CarImageFixture {

    public static Response getCarImageById(String carImageId) {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .pathParam("carImageId", carImageId)
                .when()
                .get("/carImages/{carImageId}");
    }

    public static Response getCarImagesList() {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .when()
                .get("/carImages");
    }

    public static Response getCarImagesPaged(int page, int size) {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .queryParam("page", page)
                .queryParam("size", size)
                .when()
                .get("/carImages");
    }

    public static Response postCarImage(CarImageRequest request) {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .body(request)
                .when()
                .post("/carImages");
    }

    public static Response putCarImage(String carImageId, CarImageRequest request) {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .pathParam("carImageId", carImageId)
                .body(request)
                .when()
                .put("/carImages/{carImageId}");
    }

    public static Response deleteCarImage(String carImageId) {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .pathParam("carImageId", carImageId)
                .when()
                .delete("/carImages/{carImageId}");
    }
}
