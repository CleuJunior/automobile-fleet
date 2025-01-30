package com.automobilefleet.util;

import org.junit.jupiter.api.Test;

import static com.automobilefleet.util.TokenUtils.removeBearerStringFromToken;
import static org.assertj.core.api.BDDAssertions.then;

class TokenUtilsTest {

    @Test
    void shouldReturnTokenFromBearerString() {
        var result = removeBearerStringFromToken("Bearer 1234567890");

        then(result).isEqualTo("1234567890");
    }

    @Test
    void shouldReturnEmptyStringFromNullToken() {
        var result = removeBearerStringFromToken(null);

        then(result).isEqualTo("");
    }
}