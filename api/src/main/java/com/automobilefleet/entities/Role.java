package com.automobilefleet.entities;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;


@Getter
public enum Role {

    SUPER("SUPER", "ADMIN", "USER"),
    ADMIN("ADMIN", "USER"),
    USER("USER");

    private final List<String> permissions;

    Role(String... permissions) {
        this.permissions = List.of(permissions);
    }

    public List<SimpleGrantedAuthority> getAuthoritiesFromRole() {
        return permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }
}
