package com.automobilefleet.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleType  {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_ADMIN");

    private final String name;
}
