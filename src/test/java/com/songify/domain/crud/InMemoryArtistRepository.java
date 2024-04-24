package com.songify.domain.crud;

import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

class InMemoryArtistRepository implements ArtistRepository {
    private Map<Long, Artist> db = new HashMap<>();
    AtomicInteger index = new AtomicInteger(0);

    @Override
    public Artist save(final Artist artist) {
        long index = this.index.getAndIncrement();
        db.put(index, artist);
        artist.setId((long) index);
        return artist;
    }

    @Override
    public Set<Artist> findAll(final Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Artist> findById(final Long artistId) {
        return Optional.empty();
    }

    @Override
    public int deleteById(final Long id) {
        return 0;
    }
}
