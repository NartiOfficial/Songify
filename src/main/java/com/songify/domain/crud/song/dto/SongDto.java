package com.songify.domain.crud.song.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
public record SongDto(
        Long id,
        String name
) {
}