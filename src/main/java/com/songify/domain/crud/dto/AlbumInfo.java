package com.songify.domain.crud.dto;

import java.time.Instant;
import java.util.Set;

public interface AlbumInfo {
    Long getId();

    String getTitle();

    Instant getReleaseDate();

    Set<SongInfo> getSongs();

    Set<ArtistInfo> getArtists();

    interface SongInfo {
        Long getId();

        String getName();

        Instant getReleaseDate();

        Long getDuration();

        GenreInfo getGenre();

        interface GenreInfo {
            long getId();

            String getName();
        }
    }

    interface ArtistInfo {
        long getId();

        String getName();
    }
}