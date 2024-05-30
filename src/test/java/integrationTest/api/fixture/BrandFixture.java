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
public class BrandFixture {

    public static Response getBrandById(String brandId) {
        return given()
                .spec(jwtAdmin())
                .basePath(v1)
                .pathParam("brandId", brandId)
                .when()
                .get("/brand/{brandId}");
    }

    public static Response getBrandList() {
        return given()
                .spec(jwtAdmin())
                .basePath(v1)
                .when()
                .get("/brand");
    }

    public static Response getBrandPaged(int page, int size) {
        return given()
                .spec(jwtAdmin())
                .basePath(v1)
                .queryParam("page", page)
                .queryParam("size", size)
                .when()
                .get("/brand");
    }

    public static Response postBrand(BodyBuilder body) {
        return given()
                .spec(jwtAdmin())
                .basePath(v1)
                .body(body.build())
                .when()
                .post("/brand");
    }

    public static Response postBrand(BrandRequest request) {
        return given()
                .spec(jwtAdmin())
                .basePath(v1)
                .body(request)
                .when()
                .post("/brand");
    }

    public static Response putBrand(String brandId, BrandRequest request) {
        return given()
                .spec(jwtAdmin())
                .basePath(v1)
                .pathParam("brandId", brandId)
                .body(request)
                .when()
                .put("/brand/{brandId}");
    }
}
