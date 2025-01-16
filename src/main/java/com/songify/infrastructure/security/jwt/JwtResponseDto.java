package com.songify.infrastructure.security.jwt;

import lombok.Builder;

@Builder
record JwtResponseDto(String token) {
}
