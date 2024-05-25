package com.automobilefleet.config;


import com.automobilefleet.config.security.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import static java.util.Objects.isNull;
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

        var token = request.getHeader("Authorization").substring(7);
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

        if (isNull(exception)) {
            var method = request.getMethod();

            log.info("Response service: {} {} {} {}", status.value(), status.getReasonPhrase(), method, path);

        } else {
            log.error(exception.getMessage(), exception);
        }
    }
}
