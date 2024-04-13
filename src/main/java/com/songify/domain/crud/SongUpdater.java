package com.songify.domain.crud;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@Transactional
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class SongUpdater {

    private final SongRepository songRepository;
    private final SongRetriever songRetriever;

    void updateById(Long id, Song newSong) {
        songRetriever.existsById(id);
        log.info("Updating song with id: " + id + " to new song: " + newSong.getName());
        songRepository.updateById(id, newSong);
    }

//              Dirty checking version
//     void updateById(Long id, Song newSong) {
//        Song songById = songRetriever.findById(id);
//        songById.setName(newSong.getName());
//        songById.setArtist(newSong.getArtist());
//        log.info("Updating song with id: " + id + " to new song: " + newSong.getArtist() + ", " + newSong.getName());
//    }
//
//     Song updatePartiallyById(Long id, Song songFromRequest) {
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