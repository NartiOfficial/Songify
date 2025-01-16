package com.songify.infrastructure.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class JwtController {

    private final JwtTokenGenerator jwtTokenGenerator;

    @PostMapping("/token")
    public ResponseEntity<JwtResponseDto> authenticateAndGenerateToken(@RequestBody RegisterRequestDto registerRequestDto) {
        String token = jwtTokenGenerator.authenticateAndGenerateToken(registerRequestDto.username(), registerRequestDto.password());
        return ResponseEntity.ok(JwtResponseDto.builder()
                .token(token)
                .build());
    }
}
