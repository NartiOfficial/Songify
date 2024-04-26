package com.songify.domain.crud;

import com.songify.domain.crud.dto.ArtistDto;
import com.songify.domain.crud.dto.ArtistRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;

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
    @DisplayName("Should add artist 'amigo' with id: 0, when amigo was sent")
    public void should_add_artist_amigo_with_id_zero_when_amigo_was_sent() {
        // Given
        ArtistRequestDto artist = ArtistRequestDto.builder()
                .name("amigo")
                .build();
        Set<ArtistDto> allArtists = songifyCrudFacade.findAllArtists(Pageable.unpaged());
        assertTrue(allArtists.isEmpty());
        // When
        ArtistDto response = songifyCrudFacade.addArtist(artist);
        // Then
        assertThat(response.id()).isEqualTo(0L);
        assertThat(response.name()).isEqualTo("amigo");
        int size = songifyCrudFacade.findAllArtists(Pageable.unpaged()).size();
        assertThat(size).isEqualTo(1);
    }

    @Test
    @DisplayName("Should add artist 'Shawn Mendes' with id: 0, when Shawn Mendes was sent")
    public void should_add_artist_shawn_mendes_with_id_zero_when_shawn_mendes_was_sent() {
        // Given
        ArtistRequestDto shawnMendes = ArtistRequestDto.builder()
                .name("shawn mendes")
                .build();
        Set<ArtistDto> allArtists = songifyCrudFacade.findAllArtists(Pageable.unpaged());
        assertTrue(allArtists.isEmpty());
        // When
        ArtistDto response = songifyCrudFacade.addArtist(shawnMendes);
        // Then
        assertThat(response.id()).isEqualTo(0L);
        assertThat(response.name()).isEqualTo("shawn mendes");
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
    @DisplayName("Should not throw exception ArtistNotFound, when id: 0")
    public void should_not_throw_exception_artist_not_found_when_id_was_zero() {
        // Given
        ArtistRequestDto artist = ArtistRequestDto.builder()
                .name("amigo")
                .build();
        ArtistDto artistDto = songifyCrudFacade.addArtist(artist);
        assertThat(songifyCrudFacade.findAllArtists(Pageable.unpaged())).isNotEmpty();
        // When
        songifyCrudFacade.deleteArtistByIdWithAlbumsAndSongs(artistDto.id());
        // Then
        assertThat(songifyCrudFacade.findAllArtists(Pageable.unpaged())).isEmpty();
    }
}