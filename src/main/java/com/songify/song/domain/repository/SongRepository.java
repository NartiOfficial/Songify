package com.songify.song.domain.repository;

import com.songify.song.domain.model.Song;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface SongRepository extends Repository<Song, Long> {
    Song save(Song song);

    List<Song> findAll();

    Optional<Song> findSongById(Long id);

    void deleteAllById(Long id);
}
