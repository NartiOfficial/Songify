package com.songify.domain.crud.dto;

import lombok.Builder;

import java.time.Instant;
import java.util.Set;

@Builder
public record AlbumRequestDto(
        Long songId,
        String title,
        Instant releaseDate,
        Set<Long> songIds) {
}
