package com.songify.domain.crud;

import com.songify.domain.crud.dto.AlbumInfo;

class GenreInfoTestImpl implements AlbumInfo.SongInfo.GenreInfo {

    private final Genre genre;

    GenreInfoTestImpl(final Genre genre) {
        this.genre = genre;
    }

    @Override
    public long getId() {
        return genre.getId();
    }

    @Override
    public String getName() {
        return genre.getName();
    }
}
