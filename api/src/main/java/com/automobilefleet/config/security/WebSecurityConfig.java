package com.automobilefleet.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static com.automobilefleet.config.security.RoleConstant.ADMIN;
import static com.automobilefleet.config.security.RoleConstant.SUPER;
import static com.automobilefleet.config.security.SecurityEndpointsConstant.AUTH_ENDPOINT;
import static com.automobilefleet.config.security.SecurityEndpointsConstant.BRAND_ENDPOINT;
import static com.automobilefleet.config.security.SecurityEndpointsConstant.CAR_ENDPOINT;
import static com.automobilefleet.config.security.SecurityEndpointsConstant.CAR_SPECIFICATION_ENDPOINT;
import static com.automobilefleet.config.security.SecurityEndpointsConstant.CATEGORY_ENDPOINT;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final SecurityFilter securityFilter;

    private static final AntPathRequestMatcher[] AUTHENTICATED_AS_ADMIN =
            {
                    antMatcher(POST, BRAND_ENDPOINT),
                    antMatcher(PUT, BRAND_ENDPOINT),
                    antMatcher(POST, CAR_ENDPOINT),
                    antMatcher(PUT, CAR_ENDPOINT),
                    antMatcher(POST, CAR_SPECIFICATION_ENDPOINT),
                    antMatcher(PUT, CAR_SPECIFICATION_ENDPOINT),
                    antMatcher(POST, CATEGORY_ENDPOINT),
                    antMatcher(PUT, CATEGORY_ENDPOINT),
            };

    private static final AntPathRequestMatcher[] AUTHENTICATED_AS_SUPER =
            {
                    antMatcher(DELETE, BRAND_ENDPOINT),
                    antMatcher(DELETE, CAR_ENDPOINT),
                    antMatcher(DELETE, CAR_SPECIFICATION_ENDPOINT),
                    antMatcher(DELETE, CATEGORY_ENDPOINT),
            };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authorizeHttpRequests(getAuthorizationManagerRequestMatcherRegistryCustomizer())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    private Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry>
    getAuthorizationManagerRequestMatcherRegistryCustomizer() {

        return authorize -> authorize
                .requestMatchers(AUTHENTICATED_AS_ADMIN).hasAuthority(ADMIN)
                .requestMatchers(AUTHENTICATED_AS_SUPER).hasAuthority(SUPER)
                .requestMatchers(antMatcher(POST, AUTH_ENDPOINT)).permitAll()
                .anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}