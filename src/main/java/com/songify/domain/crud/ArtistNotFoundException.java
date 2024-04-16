package com.songify.domain.crud;

class ArtistNotFoundException extends RuntimeException {
    ArtistNotFoundException(final String message) {
        super("Artist with id: " + message + " not found");
    }
}
