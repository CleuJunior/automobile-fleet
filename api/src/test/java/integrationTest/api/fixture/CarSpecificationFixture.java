package integrationTest.api.fixture;

import com.automobilefleet.api.dto.request.CarSpecificationRequest;
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
public class CarSpecificationFixture {

    public static Response getCarSpecificationList() {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .when()
                .get("/carSpecification");
    }

    public static Response geCarSpecificationById(String carSpecificationId) {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .pathParam("carSpecificationId", carSpecificationId)
                .when()
                .get("/carSpecification/{carSpecificationId}");
    }

    public static Response geCarSpecificationInfoById(String carSpecificationId) {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .pathParam("carSpecificationId", carSpecificationId)
                .when()
                .get("/carSpecification/{carSpecificationId}/info");
    }

    public static Response postCarSpecification(BodyBuilder body) {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .body(body.build())
                .when()
                .post("/carSpecification");
    }

    public static Response postCarSpecification(CarSpecificationRequest request) {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .body(request)
                .when()
                .post("/carSpecification");
    }

    public static Response putCarSpecification(String carSpecificationId, CarSpecificationRequest request) {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .pathParam("carSpecificationId", carSpecificationId)
                .body(request)
                .when()
                .put("/carSpecification/{carSpecificationId}");
    }
}
