package integrationTest.api.fixture;

import com.automobilefleet.api.dto.request.CategoryRequest;
import com.automobilefleet.api.dto.request.CustomerRequest;
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
public class CategoryFixture {

    public static Response getCategoryById(String categoryId) {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .pathParam("categoryId", categoryId)
                .when()
                .get("/category/{categoryId}");
    }

    public static Response getCategoryList() {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .when()
                .get("/category");
    }

    public static Response getCategoryPaged(int page, int size) {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .queryParam("page", page)
                .queryParam("size", size)
                .when()
                .get("/customer");
    }

    public static Response postCategory(BodyBuilder body) {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .body(body.build())
                .when()
                .post("/category");
    }

    public static Response postCategory(CategoryRequest request) {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .body(request)
                .when()
                .post("/category");
    }

    public static Response putCategory(String categoryId, CategoryRequest request) {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .pathParam("categoryId", categoryId)
                .body(request)
                .when()
                .put("/category/{categoryId}");
    }

    public static Response deleteCategory(String categoryId) {
        return given()
                .spec(jwt(USER_SUPER_ID, USER_SUPER_NAME, USER_SUPER_ROLE))
                .basePath(v1)
                .pathParam("categoryId", categoryId)
                .when()
                .delete("/category/{categoryId}");
    }
}
