package com.automobilefleet.authority;

import com.automobilefleet.entities.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public class SuperUserAuthorityStrategy implements RoleAuthorityStrategy {

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority(Role.SUPER.name()),
                new SimpleGrantedAuthority(Role.ADMIN.name()),
                new SimpleGrantedAuthority(Role.USER.name())
        );
    }
}

