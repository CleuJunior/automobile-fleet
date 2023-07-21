package com.automobilefleet.services;

import com.automobilefleet.api.request.UserRequest;
import com.automobilefleet.entities.User;
import com.automobilefleet.enums.RoleType;
import com.automobilefleet.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public User saveUser(UserRequest request) {
        User user = new User(
                request.getUsername(),
                new BCryptPasswordEncoder().encode(request.getPassword()),
                RoleType.ROLE_USER
        );

        this.repository.save(user);
        return user;
    }
}
