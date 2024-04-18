package com.songify.infrastructure.crud.artist;

import jakarta.validation.constraints.NotEmpty;

record ArtistUpdateRequestDto(
        @NotEmpty(message = "newArtist must not be null")
        @NotEmpty(message = "newArtist must not be empty")
        String newArtistName
) {
}
