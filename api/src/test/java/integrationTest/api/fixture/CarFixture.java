package integrationTest.api.fixture;

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
public class CarFixture {

    public static Response getCarInfoById(String carId) {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .pathParam("carId", carId)
                .when()
                .get("/cars/info/{carId}");
    }

    public static Response getCarById(String carId) {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .pathParam("carId", carId)
                .when()
                .get("/cars/{carId}");
    }

    public static Response getCarList() {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .when()
                .get("/cars");
    }

    public static Response getCarListAvailable() {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .when()
                .get("/cars/available");
    }

    public static Response getCarByBrandName(String brandName) {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .queryParam("brandName", brandName)
                .when()
                .get("/cars");
    }

    public static Response getCarPaged(int page, int size) {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .queryParam("page", page)
                .queryParam("size", size)
                .when()
                .get("/cars");
    }

    public static Response postCar(BodyBuilder body) {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .body(body.build())
                .when()
                .post("/cars");
    }

    public static Response postCar(CarRequest request) {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .body(request)
                .when()
                .post("/cars");
    }

    public static Response putCar(String carId, CarRequest request) {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .pathParam("carId", carId)
                .body(request)
                .when()
                .put("/cars/{carId}");
    }

    public static Response deleteCustomer(String customerId) {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .pathParam("customerId", customerId)
                .when()
                .delete("/customer/{customerId}");
    }
}
