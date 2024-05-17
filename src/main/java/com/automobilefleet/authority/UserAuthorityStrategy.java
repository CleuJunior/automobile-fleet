package com.automobilefleet.authority;

import com.automobilefleet.entities.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

import static java.util.Collections.singletonList;

public class UserAuthorityStrategy implements RoleAuthorityStrategy {
    @Override
    public List<GrantedAuthority> getAuthorities() {
        return singletonList(new SimpleGrantedAuthority(Role.USER.name()));
    }
}
