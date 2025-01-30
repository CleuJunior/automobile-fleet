package integrationTest.api.fixture;

import com.automobilefleet.api.dto.request.BrandRequest;
import integrationTest.api.utils.BodyBuilder;
import io.restassured.response.Response;
import lombok.NoArgsConstructor;

import static integrationTest.api.utils.RequestSpecUtils.jwtAdmin;
import static integrationTest.api.utils.RequestSpecUtils.v1;
import static io.restassured.RestAssured.given;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CustomerFixture {

    public static Response getCustomerById(String brandId) {
        return given()
                .spec(jwtAdmin())
                .basePath(v1)
                .pathParam("customerId", brandId)
                .when()
                .get("/customer/{customerId}");
    }

    public static Response getBrandList() {
        return given()
                .spec(jwtAdmin())
                .basePath(v1)
                .when()
                .get("/customer");
    }

    public static Response getBrandPaged(int page, int size) {
        return given()
                .spec(jwtAdmin())
                .basePath(v1)
                .queryParam("page", page)
                .queryParam("size", size)
                .when()
                .get("/customer");
    }

    public static Response postBrand(BodyBuilder body) {
        return given()
                .spec(jwtAdmin())
                .basePath(v1)
                .body(body.build())
                .when()
                .post("/customer");
    }

    public static Response postBrand(BrandRequest request) {
        return given()
                .spec(jwtAdmin())
                .basePath(v1)
                .body(request)
                .when()
                .post("/customer");
    }

    public static Response putBrand(String brandId, BrandRequest request) {
        return given()
                .spec(jwtAdmin())
                .basePath(v1)
                .pathParam("customerId", brandId)
                .body(request)
                .when()
                .put("/customer/{brandId}");
    }
}
