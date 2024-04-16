package com.songify.domain.crud;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Log4j2
@Transactional
class SongDeleter {
    private final SongRepository songRepository;
    private final SongRetriever songRetriever;

    SongDeleter(SongRepository songRepository, SongRetriever songRetriever) {
        this.songRepository = songRepository;
        this.songRetriever = songRetriever;
    }

    void deleteById(Long id) {
        songRetriever.existsById(id);
        log.info("Deleting song by id: {}", id);
        songRepository.deleteAllById(id);
    }

    void deleteAllSongsById(final Set<Long> songsIds) {
        songRepository.deleteByIdIn(songsIds);
    }
}
