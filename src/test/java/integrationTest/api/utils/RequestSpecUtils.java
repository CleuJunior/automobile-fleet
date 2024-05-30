package integrationTest.api.utils;

import com.auth0.jwt.JWT;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC256;
import static java.lang.String.format;

public class RequestSpecUtils {

    private static String SECRET = "BE1B44F8953BB2978C54FAA622EA1";

    public static final String v1 = "/api/v1";

    public static void onPort(int port) {
        RestAssured.port = port;
    }

    public static void basePath(String path, String baseUri) {
        RestAssured.baseURI = baseUri;
        RestAssured.basePath = path;
    }

    public static void basePath(String path, int port) {
        RestAssured.port = port;
        RestAssured.basePath = path;
    }

    public static RequestSpecification jwt(String userId) {
        return new RequestSpecBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", bearerJwt(userId))
                .build();
    }

    public static RequestSpecification jwtAdmin() {
        return new RequestSpecBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI5ZDYzZTIzMy03MjljLTQ2Y2YtODBkNC0wMDMyZmU1NTkxMjMiLCJ1c2VybmFtZSI6ImNsZXUiLCJyb2xlIjoiU1VQRVIiLCJleHAiOjE3MTY4MjIyMjJ9.dDhuc1sNSUriLgzH4NBr1sF4KtjaFkYkYKhywY0wiPk")
                .build();
    }

    public static String token(String userId) {
        return JWT
                .create()
                .withIssuedAt(new Date())
                .withClaim("userId", userId)
                .withClaim("username", userId)
                .withClaim("role", userId)
                .sign(HMAC256(SECRET));
    }

    private static String bearerJwt(String userId) {
        return format("Bearer %s", token(userId));
    }
}
