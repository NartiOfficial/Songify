package com.songify.infrastructure.security.jwt;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
class JwtController {

    private final JwtTokenGenerator jwtTokenGenerator;

    @PostMapping("/token")
    public ResponseEntity<JwtResponseDto> authenticateAndGenerateToken(@RequestBody RegisterRequestDto registerRequestDto, HttpServletResponse response) throws NoSuchAlgorithmException {
        String token = jwtTokenGenerator.authenticateAndGenerateToken(registerRequestDto.username(), registerRequestDto.password());
        Cookie cookie = new Cookie("accessToken", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60);
        response.addCookie(cookie);
        return ResponseEntity.ok().body((JwtResponseDto.builder()
                .token(token)
                .build())
        );
    }
}
