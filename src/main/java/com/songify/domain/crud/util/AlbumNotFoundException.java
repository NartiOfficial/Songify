package com.songify.domain.crud.util;

public class AlbumNotFoundException extends RuntimeException {
    public AlbumNotFoundException(final String message) {
        super(message);
    }
}
