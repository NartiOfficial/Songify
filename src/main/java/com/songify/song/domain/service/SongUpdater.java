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
    private final SongRetriever songRetriever;

    public SongUpdater(SongRepository songRepository, SongRetriever songRetriever) {
        this.songRepository = songRepository;
        this.songRetriever = songRetriever;
    }

    public void updateById(Long id, Song newSong) {
        songRetriever.existsById(id);
        log.info("Updating song with id: " + id + " to new song: " + newSong.getArtist() + ", " + newSong.getName());
        songRepository.updateById(id, newSong);
    }

    public Song updatePartiallyById(Long id, Song songFromRequest) {
        Song songFromDatabase = songRetriever.findById(id);
        Song.SongBuilder builder = Song.builder();
        if (songFromRequest.getName() != null) {
            builder.name(songFromRequest.getName());
        } else {
            builder.name(songFromDatabase.getName());
        }
        if (songFromRequest.getArtist() != null) {
            builder.artist(songFromRequest.getArtist());
        } else {
            builder.artist(songFromDatabase.getArtist());
        }
        Song toSave = builder.build();
        updateById(id, toSave);
        return toSave;
    }

//              Dirty checking version
//    public void updateById(Long id, Song newSong) {
//        Song songById = songRetriever.findById(id);
//        songById.setName(newSong.getName());
//        songById.setArtist(newSong.getArtist());
//        log.info("Updating song with id: " + id + " to new song: " + newSong.getArtist() + ", " + newSong.getName());
//    }
//
//    public Song updatePartiallyById(Long id, Song songFromRequest) {
//        Song songFromDatabase = songRetriever.findById(id);
//        if (songFromRequest.getName() != null) {
//            songFromDatabase.setName(songFromRequest.getName());
//        }
//        if (songFromRequest.getArtist() != null) {
//            songFromDatabase.setArtist(songFromRequest.getArtist());
//        }
//        log.info("Updating song with id: " + id + " to new song: " + songFromRequest.getArtist() + ", " + songFromRequest.getName());
//        return songFromDatabase;
//    }
}