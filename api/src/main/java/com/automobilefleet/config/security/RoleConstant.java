package com.automobilefleet.config.security;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class RoleConstant {

    public static final String SUPER = "SUPER";
    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";
}
