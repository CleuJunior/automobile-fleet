package com.automobilefleet.config;


import com.automobilefleet.config.security.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import static com.automobilefleet.util.HeaderUtils.authenticationHeader;
import static com.automobilefleet.util.TokenUtils.removeBearerStringFromToken;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.springframework.http.HttpStatus.valueOf;

@Slf4j
@Component
@RequiredArgsConstructor
class LogInterceptor implements HandlerInterceptor {

    private final TokenService service;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler) {

        var token = removeBearerStringFromToken(authenticationHeader(request));

        if (isBlank(token)) {
            log.warn("Token is empty");
            return true;
        }

        var user = service.validateToken(token);
        log.info("Request method {} on the endpoint {} by the user {}", request.getMethod(), request.getRequestURI(), user);
        return true;
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request,
                                @NonNull HttpServletResponse response,
                                @NonNull Object handler,
                                Exception exception) {

        var path = request.getServletPath();
        var status = valueOf(response.getStatus());

        if (nonNull(exception)) {
            log.error(exception.getMessage(), exception);
        }

        var method = request.getMethod();
        log.info("Response service: {} {} {} {}", status.value(), status.getReasonPhrase(), method, path);
    }
}
