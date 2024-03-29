package com.songify.song.domain.service;

import com.songify.song.domain.model.Song;
import com.songify.song.domain.repository.SongRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@Transactional
public class SongUpdater {

    private final SongRepository songRepository;

    public SongUpdater(SongRepository songRepository) {
        this.songRepository = songRepository;
    }


    public void updateById(Long id, Song newSong) {
        log.info("Updating song with id: " + id + " to new song: " + newSong.getArtist() + ", " + newSong.getName());
        songRepository.updateById(id, newSong);
    }
}