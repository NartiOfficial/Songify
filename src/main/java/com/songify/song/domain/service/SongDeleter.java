package com.songify.song.domain.service;

import com.songify.song.domain.repository.SongRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class SongDeleter {
    private final SongRepository songRepository;

    public SongDeleter(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public void deleteById(Long id) {
        log.info("Deleting song by id: " + id);
        songRepository.deleteAllById(id);
    }
}
