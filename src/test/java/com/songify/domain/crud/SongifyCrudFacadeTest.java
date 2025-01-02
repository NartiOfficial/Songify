package com.songify.domain.crud;

import com.songify.domain.crud.dto.AlbumDto;
import com.songify.domain.crud.dto.AlbumInfo;
import com.songify.domain.crud.dto.AlbumRequestDto;
import com.songify.domain.crud.dto.ArtistDto;
import com.songify.domain.crud.dto.ArtistRequestDto;
import com.songify.domain.crud.dto.SongDto;
import com.songify.domain.crud.dto.SongLanguageDto;
import com.songify.domain.crud.dto.SongRequestDto;
import com.songify.domain.crud.util.AlbumNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertTrue;


class SongifyCrudFacadeTest {

    SongifyCrudFacade songifyCrudFacade = SongifyCrudFacadeConfiguration.createSongifyCrud(
            new InMemorySongRepository(),
            new InMemoryGenreRepository(),
            new InMemoryArtistRepository(),
            new InMemoryAlbumRepository()
    );

    @Test
    @DisplayName("Should add artist 'artist' with id: 0, when 'artist' was sent")
    public void should_add_artist_amigo_with_id_zero_when_amigo_was_sent() {
        // Given
        ArtistRequestDto artist = ArtistRequestDto.builder()
                .name("artist")
                .build();
        Set<ArtistDto> allArtists = songifyCrudFacade.findAllArtists(Pageable.unpaged());
        assertTrue(allArtists.isEmpty());
        // When
        ArtistDto response = songifyCrudFacade.addArtist(artist);
        // Then
        assertThat(response.id()).isEqualTo(0L);
        assertThat(response.name()).isEqualTo("artist");
        int size = songifyCrudFacade.findAllArtists(Pageable.unpaged()).size();
        assertThat(size).isEqualTo(1);
    }

    @Test
    @DisplayName("Should add artist 'artist' with id: 0, when 'artist' was sent")
    public void should_add_artist_shawn_mendes_with_id_zero_when_shawn_mendes_was_sent() {
        // Given
        ArtistRequestDto artist = ArtistRequestDto.builder()
                .name("artist")
                .build();
        Set<ArtistDto> allArtists = songifyCrudFacade.findAllArtists(Pageable.unpaged());
        assertTrue(allArtists.isEmpty());
        // When
        ArtistDto response = songifyCrudFacade.addArtist(artist);
        // Then
        assertThat(response.id()).isEqualTo(0L);
        assertThat(response.name()).isEqualTo("artist");
        int size = songifyCrudFacade.findAllArtists(Pageable.unpaged()).size();
        assertThat(size).isEqualTo(1);
    }

    @Test
    @DisplayName("Should throw exception ArtistNotFound, when id: 0")
    public void should_throw_exception_artist_not_found_when_id_was_zero() {
        // Given
        assertThat(songifyCrudFacade.findAllArtists(Pageable.unpaged())).isEmpty();
        // When
        Throwable throwable = catchThrowable(() -> songifyCrudFacade.deleteArtistByIdWithAlbumsAndSongs(0L));
        // Then
        assertThat(throwable).isInstanceOf(ArtistNotFoundException.class);
        assertThat(throwable.getMessage()).isEqualTo("Artist with id: 0 not found");
    }

    @Test
    @DisplayName("Should delete artist by id when he have no albums")
    public void should_delete_artist_by_id_when_he_have_no_albums() {
        // Given
        ArtistRequestDto artist = ArtistRequestDto.builder()
                .name("artist")
                .build();
        Long artistId = songifyCrudFacade.addArtist(artist).id();
        assertThat(songifyCrudFacade.findAlbumsByArtistId(artistId)).isEmpty();
        // When
        songifyCrudFacade.deleteArtistByIdWithAlbumsAndSongs(artistId);
        // Then
        assertThat(songifyCrudFacade.findAllArtists(Pageable.unpaged())).isEmpty();
    }

    @Test
    @DisplayName("Should delete artist with album and songs by id when artist had one album and he was the only artist in album")
    public void should_delete_artist_with_album_and_songs_by_id_when_artist_had_one_album_and_he_was_the_only_artist_in_album() {
        // Given
        ArtistRequestDto artist = ArtistRequestDto.builder()
                .name("artist")
                .build();
        Long artistId = songifyCrudFacade.addArtist(artist).id();
        SongRequestDto song = SongRequestDto.builder()
                .name("song")
                .language(SongLanguageDto.ENGLISH)
                .build();
        SongDto songDto = songifyCrudFacade.addSong(song);
        Long songId = songDto.id();
        AlbumDto albumDto = songifyCrudFacade.addAlbumWithSong(AlbumRequestDto
                .builder()
                .songId(songId)
                .title("album")
                .build());
        Long albumId = albumDto.id();
        songifyCrudFacade.addArtistToAlbum(artistId, albumId);
        assertThat(songifyCrudFacade.findAlbumsByArtistId(artistId)).size().isEqualTo(1);
        assertThat(songifyCrudFacade.countArtistByAlbumId(albumId)).isEqualTo(1);
        // When
        songifyCrudFacade.deleteArtistByIdWithAlbumsAndSongs(artistId);
        // Then
        assertThat(songifyCrudFacade.findAllArtists(Pageable.unpaged())).isEmpty();
        Throwable throwable = catchThrowable(() -> songifyCrudFacade.findSongDtoById(songId));
        assertThat(throwable).isInstanceOf(SongNotFoundException.class);
        assertThat(throwable.getMessage()).isEqualTo("Song with id: 0 not found");
        Throwable throwable2 = catchThrowable(() -> songifyCrudFacade.findAlbumById(albumId));
        assertThat(throwable2).isInstanceOf(AlbumNotFoundException.class);
        assertThat(throwable2.getMessage()).isEqualTo("Album with id: 0 not found");
    }

    @Test
    @DisplayName("Should add song")
    public void should_add_song(){
        // Given
        SongRequestDto song = SongRequestDto.builder()
                .name("song")
                .language(SongLanguageDto.ENGLISH)
                .build();
        assertThat(songifyCrudFacade.findAllSongs(Pageable.unpaged())).isEmpty();
        // When
        SongDto songDto = songifyCrudFacade.addSong(song);
        // Then
        List<SongDto> allSongs = songifyCrudFacade.findAllSongs(Pageable.unpaged());
        assertThat(allSongs)
                .extracting(SongDto::id)
                .containsExactly(0L);
    }

    @Test
    @DisplayName("Should add album with song")
    public void should_add_album_with_song(){
        // Given
        SongRequestDto songRequestDto = SongRequestDto.builder()
                .name("song")
                .language(SongLanguageDto.ENGLISH)
                .build();
        SongDto songDto = songifyCrudFacade.addSong(songRequestDto);
        AlbumRequestDto album = AlbumRequestDto
                .builder()
                .songId(songDto.id())
                .title("album")
                .build();
        assertThat(songifyCrudFacade.findAllAlbums()).isEmpty();
        // When
        AlbumDto albumDto = songifyCrudFacade.addAlbumWithSong(album);
        // Then
        assertThat(songifyCrudFacade.findAllAlbums()).isNotEmpty();
        AlbumInfo albumWithSong = songifyCrudFacade.findAlbumByIdWithArtistsAndSongs(albumDto.id());
        Set<AlbumInfo.SongInfo> songs = albumWithSong.getSongs();
        assertTrue(songs.stream().anyMatch(song -> song.getId().equals(songDto.id())));
    }

    @Test
    @DisplayName("Should add artist to album")
    public void should_add_artist_to_album(){
        // Given
        ArtistRequestDto shawnMendes = ArtistRequestDto.builder()
                .name("shawn mendes")
                .build();
        Long artistId = songifyCrudFacade.addArtist(shawnMendes).id();
        SongRequestDto song = SongRequestDto.builder()
                .name("song1")
                .language(SongLanguageDto.ENGLISH)
                .build();
        SongDto songDto = songifyCrudFacade.addSong(song);
        Long songId = songDto.id();
        AlbumDto albumDto = songifyCrudFacade.addAlbumWithSong(AlbumRequestDto
                .builder()
                .songId(songId)
                .title("album title 1")
                .build());
        Long albumId = albumDto.id();
        assertThat(songifyCrudFacade.findAlbumsByArtistId(artistId)).isEmpty();
        // When
        songifyCrudFacade.addArtistToAlbum(artistId, albumId);
        // Then
        Set<AlbumDto> albumsByArtistId = songifyCrudFacade.findAlbumsByArtistId(artistId);
        assertThat(albumsByArtistId)
                .extracting(AlbumDto::id)
                .containsExactly(0L);
    }

    @Test
    @DisplayName("Should return album by id")
    public void should_return_album_by_id(){
        // Given
        SongRequestDto song = SongRequestDto.builder()
                .name("song")
                .language(SongLanguageDto.ENGLISH)
                .build();
        SongDto songDto = songifyCrudFacade.addSong(song);
        Long songId = songDto.id();
        AlbumDto albumDto = songifyCrudFacade.addAlbumWithSong(AlbumRequestDto
                .builder()
                .songId(songId)
                .title("album")
                .build());
        Long albumId = albumDto.id();
        // When
        AlbumDto albumById = songifyCrudFacade.findAlbumById(albumId);
        // Then
        assertThat(albumById)
                .isEqualTo(new AlbumDto(albumId, "album"));
    }

    @Test
    @DisplayName("Should throw exception when album not found by id")
    public void should_throw_exception_when_album_not_found_by_id(){
        // Given
        assertThat(songifyCrudFacade.findAllAlbums()).isEmpty();
        // When
        Throwable throwable = catchThrowable(() -> songifyCrudFacade.findAlbumById(55L));
        // Then
        assertThat(throwable).isInstanceOf(AlbumNotFoundException.class);
        assertThat(throwable.getMessage()).isEqualTo("Album with id: 55 not found");
    }

    @Test
    @DisplayName("Should throw exception when song not found by id")
    public void should_throw_exception_when_song_not_found_by_id(){
        // Given
        assertThat(songifyCrudFacade.findAllSongs(Pageable.unpaged())).isEmpty();
        // When
        Throwable throwable = catchThrowable(() -> songifyCrudFacade.findSongDtoById(55L));
        // Then
        assertThat(throwable).isInstanceOf(SongNotFoundException.class);
        assertThat(throwable.getMessage()).isEqualTo("Song with id: 55 not found");
    }

    @Test
    @DisplayName("Should delete only artist from album by id when there were more than one artist in album")
    public void should_delete_only_artist_from_album_by_when_there_were_more_than_one_artist_in_album() {
        // Given
        ArtistRequestDto artist = ArtistRequestDto.builder()
                .name("artist")
                .build();
        ArtistRequestDto artist2 = ArtistRequestDto.builder()
                .name("artist2")
                .build();
        Long artistId = songifyCrudFacade.addArtist(artist).id();
        Long artistId2 = songifyCrudFacade.addArtist(artist2).id();
        SongRequestDto song = SongRequestDto.builder()
                .name("song")
                .language(SongLanguageDto.ENGLISH)
                .build();
        SongDto songDto = songifyCrudFacade.addSong(song);
        Long songId = songDto.id();
        AlbumDto albumDto = songifyCrudFacade.addAlbumWithSong(AlbumRequestDto
                .builder()
                .songId(songId)
                .title("album")
                .build());
        Long albumId = albumDto.id();
        songifyCrudFacade.addArtistToAlbum(artistId, albumId);
        songifyCrudFacade.addArtistToAlbum(artistId2, albumId);
        assertThat(songifyCrudFacade.countArtistByAlbumId(albumId)).isEqualTo(2);
        // When
        songifyCrudFacade.deleteArtistByIdWithAlbumsAndSongs(artistId);
        // Then
        AlbumInfo album = songifyCrudFacade.findAlbumByIdWithArtistsAndSongs(albumId);
        Set<AlbumInfo.ArtistInfo> artists = album.getArtists();
        assertThat(artists)
                .extracting("id")
                .containsOnly(artistId2);
    }

    @Test
    @DisplayName("Should delete only artist from album by id when there were more than one artist in album")
    public void should_delete_artist_with_albums_and_songs_by_id_when_artist_was_the_only_artist_in_albums() {
        // Given

        // When

        // Then
    }
}