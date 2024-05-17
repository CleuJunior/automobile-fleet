package com.automobilefleet.entities;

import com.automobilefleet.authority.AdminAuthorityStrategy;
import com.automobilefleet.authority.RoleAuthorityStrategy;
import com.automobilefleet.authority.SuperUserAuthorityStrategy;
import com.automobilefleet.authority.UserAuthorityStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum Role {

    SUPER(new SuperUserAuthorityStrategy()),
    ADMIN(new AdminAuthorityStrategy()),
    USER(new UserAuthorityStrategy());

    private final RoleAuthorityStrategy authoritiesFromRole;

}
