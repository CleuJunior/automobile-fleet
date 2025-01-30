package com.automobilefleet.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.automobilefleet.api.dto.response.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static com.auth0.jwt.algorithms.Algorithm.HMAC256;

@Service
@Slf4j
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    private static final String CLAIM_USER_ID = "userId";
    private static final String CLAIM_USERNAME = "username";
    private static final String CLAIM_ROLE = "role";

    public String generateToken(UserResponse user) {
        try {
            var token = JWT.create()
                    .withClaim(CLAIM_USER_ID, user.id().toString())
                    .withClaim(CLAIM_USERNAME, user.username())
                    .withClaim(CLAIM_ROLE, user.role().name())
                    .withExpiresAt(generateExpirationDate())
                    .sign(HMAC256(secret));

            log.info("Generated token: {}", token);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while authenticating");
        }
    }

    public String validateToken(String token) {
        try {
            return JWT.require(HMAC256(secret))
                    .build()
                    .verify(token)
                    .getClaim(CLAIM_USERNAME)
                    .asString();

        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.of("-03:00"));
    }
}
