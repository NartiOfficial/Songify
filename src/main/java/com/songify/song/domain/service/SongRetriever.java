package com.songify.song.domain.service;

import com.songify.song.domain.model.Song;
import com.songify.song.domain.model.SongNotFoundException;
import com.songify.song.domain.repository.SongRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class SongRetriever {

    private final SongRepository songRepository;

    public SongRetriever(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> findAll() {
        log.info("Retrieving all songs");
        return songRepository.findAll();
    }

    public List<Song> findAllLimitedBy(Integer limit) {
        return songRepository.findAll().stream().limit(limit).toList();
    }

    public Song findById(Long id) {
        return songRepository.findSongById(id)
                .orElseThrow(() -> new SongNotFoundException("Song with id: " + id + " not found"));
    }
}
