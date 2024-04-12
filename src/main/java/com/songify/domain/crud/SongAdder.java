package com.songify.domain.crud;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@Transactional
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class SongAdder {

    private final SongRepository songRepository;

    Song addSong(Song song) {
        log.info("Adding new song: " + song);
        return songRepository.save(song);
    }
}
