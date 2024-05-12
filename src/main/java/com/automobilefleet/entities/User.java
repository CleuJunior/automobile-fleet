//package com.automobilefleet.entities;
//
//import com.automobilefleet.enums.RoleType;
//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.NonNull;
//import lombok.RequiredArgsConstructor;
//import lombok.Setter;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//import java.io.Serializable;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.List;
//import java.util.UUID;
//
//@Entity
//@Table(name = "user_entity")
//@NoArgsConstructor
//@RequiredArgsConstructor
//@Getter @Setter
//public class User implements UserDetails, Serializable {
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "user_id")
//    @Setter(AccessLevel.NONE)
//    private UUID id;
//
//    @Column(nullable = false, unique = true)
//    @NonNull
//    private String username;
//
//    @Column(nullable = false)
//    @NonNull
//    private String password;
//
//    @Enumerated(EnumType.STRING)
//    @NonNull
//    private RoleType role;
//
//    @OneToOne
//    private Customer customer;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        if (this.role.equals(RoleType.ROLE_ADMIN)){
//            return List.of(
//                    new SimpleGrantedAuthority(RoleType.ROLE_ADMIN.getName()),
//                    new SimpleGrantedAuthority(RoleType.ROLE_USER.getName())
//            );
//        }
//        return Collections.singletonList(new SimpleGrantedAuthority(RoleType.ROLE_USER.getName()));
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//}
