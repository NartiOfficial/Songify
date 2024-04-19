package com.songify.domain.crud;

import com.songify.domain.crud.dto.AlbumDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

@Service
@Transactional
@AllArgsConstructor
class AlbumAdder {

    private final SongRetriever songRetriever;
    private final AlbumRepository albumRepository;

    AlbumDto addAlbum(final Long songId, final String title, final Instant instant) {
        Song song = songRetriever.findSongById(songId);
        Album album = new Album();
        album.setTitle(title);
        album.addSong(song);
        album.setReleaseDate(instant);
        Album savedAlbum = albumRepository.save(album);
        return new AlbumDto(savedAlbum.getId(), savedAlbum.getTitle());
    }


    Album addAlbum(final String title, final Instant instant) {
        Album album = new Album();
        album.setTitle(title);
        album.setReleaseDate(instant);
        return albumRepository.save(album);
    }
}
