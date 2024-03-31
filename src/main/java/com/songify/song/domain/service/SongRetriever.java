package com.songify.song.domain.service;

import com.songify.song.domain.model.Song;
import com.songify.song.domain.model.SongNotFoundException;
import com.songify.song.domain.repository.SongRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class SongRetriever {

    private final SongRepository songRepository;

    public SongRetriever(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> findAll(Pageable pageable) {
        log.info("Retrieving all songs");
        return songRepository.findAll(pageable);
    }

    public Song findById(Long id) {
        return songRepository.findSongById(id)
                .orElseThrow(() -> new SongNotFoundException("Song with id: " + id + " not found"));
    }

    public boolean existsById(Long id){
        if(!songRepository.existsById(id)) {
            throw new SongNotFoundException("Song with id: " + id + " not found");
        }
        return true;
    }
}
