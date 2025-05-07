package com.automobilefleet.config.security;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class SecurityEndpointsConstant {

    public static final String BRAND_ENDPOINT = "/api/v1/brand/**";
    public static final String CAR_ENDPOINT = "/api/v1/cars/**";
    public static final String CAR_SPECIFICATION_ENDPOINT = "/api/v1/car_specification/**";
    public static final String CUSTOMER_ENDPOINT = "/api/v1/customer/**";
    public static final String CATEGORY_ENDPOINT = "/api/v1/category/**";
    public static final String AUTH_ENDPOINT = "/api/v1/auth/**";
}
