package com.automobilefleet.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class HeaderUtils {

    public static String authenticationHeader(@NonNull HttpServletRequest request) {
        return request.getHeader("Authorization");
    }
}
