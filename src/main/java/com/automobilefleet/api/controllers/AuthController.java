package com.automobilefleet.api.controllers;

import com.automobilefleet.api.dto.request.LoginRequest;
import com.automobilefleet.api.dto.request.RegisterRequest;
import com.automobilefleet.api.dto.response.UserTokenResponse;
import com.automobilefleet.config.security.TokenService;
import com.automobilefleet.entities.User;
import com.automobilefleet.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest body) {
        var user = repository.findByUsername(body.username()).orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = tokenService.generateToken(user);
            return ResponseEntity.ok(new UserTokenResponse(user.getUsername(), token));
        }
        return ResponseEntity.badRequest().build();
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequest body) {
        var user = repository.findByUsername(body.email());

        if (user.isEmpty()) {
            var newUser = new User();
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setUsername(body.username());
            repository.save(newUser);

            var token = tokenService.generateToken(newUser);
            return ResponseEntity.ok(new UserTokenResponse(newUser.getUsername(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}
