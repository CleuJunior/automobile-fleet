package com.automobilefleet;

import com.automobilefleet.exceptions.PasswordMatchException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class Validations {

    private final PasswordEncoder passwordEncoder;

    public void passwordMatchValidation(String requestPassword, String currentPassword) {
        if (!passwordEncoder.matches(requestPassword, currentPassword)) {
            log.warn("Wrong password");
            throw new PasswordMatchException("password.does.not.match");
        }
    }
}
