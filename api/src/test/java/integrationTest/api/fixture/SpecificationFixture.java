package integrationTest.api.fixture;

import com.automobilefleet.api.dto.request.SpecificationRequest;
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
public class SpecificationFixture {

    public static Response getSpecificationList() {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .when()
                .get("/specification");
    }

    public static Response getSpecificationById(String specificationId) {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .pathParam("specificationId", specificationId)
                .when()
                .get("/specification/{specificationId}");
    }

    public static Response getSpecificationPaged(int page, int size) {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .queryParam("page", page)
                .queryParam("size", size)
                .when()
                .get("/specification");
    }

    public static Response postSpecification(BodyBuilder body) {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .body(body.build())
                .when()
                .post("/specification");
    }

    public static Response postSpecification(SpecificationRequest request) {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .body(request)
                .when()
                .post("/specification");
    }

    public static Response putSpecification(String specificationId, SpecificationRequest request) {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .pathParam("specificationId", specificationId)
                .body(request)
                .when()
                .put("/specification/{specificationId}");
    }
}
