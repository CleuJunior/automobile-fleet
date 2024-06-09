package com.automobilefleet.util;

import lombok.NoArgsConstructor;

import static java.util.Objects.isNull;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class TokenUtils {

    public static String removeBearerStringFromToken(String bearerToken) {
        return isNull(bearerToken) ? "" : bearerToken.substring(7);
    }
}
