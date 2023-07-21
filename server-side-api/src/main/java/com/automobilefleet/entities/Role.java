package com.automobilefleet.entities;

import com.automobilefleet.enums.RoleType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "role_entity")
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter @Setter
public class Role implements GrantedAuthority, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id", nullable = false)
    @Getter
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    @NonNull
    private RoleType roleType;

    @Override
    public String getAuthority() {
        return this.roleType.name();
    }
}