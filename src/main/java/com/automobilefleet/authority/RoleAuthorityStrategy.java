package com.automobilefleet.authority;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface RoleAuthorityStrategy {

    List<GrantedAuthority> getAuthorities();

}
