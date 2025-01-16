package com.songify.infrastructure.usercrud.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
class AuthController {

    private final UserDetailsManager userDetailsManager;

    @PostMapping("register")
    public ResponseEntity<RegisterUserResponseDto> register(@RequestBody RegisterUserRequestDto request) {
        String password = request.password();
        String username = request.username();
        UserDetails user = User.builder()
                .username(username)
                .password(password)
                .build();
        userDetailsManager.createUser(user);
        return ResponseEntity.ok(new RegisterUserResponseDto("Created user"));
    }
}
