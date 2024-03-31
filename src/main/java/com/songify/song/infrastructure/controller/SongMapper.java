package com.songify.song.infrastructure.controller;

import com.songify.song.domain.model.Song;
import com.songify.song.infrastructure.controller.dto.request.CreateSongRequestDto;
import com.songify.song.infrastructure.controller.dto.request.PartiallyUpdateSongRequestDto;
import com.songify.song.infrastructure.controller.dto.request.UpdateSongRequestDto;
import com.songify.song.infrastructure.controller.dto.response.*;

import java.util.List;

import org.springframework.http.HttpStatus;

public class SongMapper {

    public static SongDto mapFromSongToSongDto(Song song){
        return new SongDto(song.getId(), song.getName(), song.getArtist());
    }

    public static Song mapFromCreateSongRequestDtoToSong(CreateSongRequestDto dto) {
        return new Song(dto.songName(), dto.artist());
    }

    public static Song mapFromUpdateSongRequestDtoToSong(UpdateSongRequestDto dto) {
        return new Song(dto.songName(), dto.artist());
    }

    public static Song mapFromPartiallyUpdateSongRequestDtoToSong(PartiallyUpdateSongRequestDto dto) {
        return new Song(dto.songName(), dto.artist());
    }

    public static CreateSongResponseDto mapFromSongToCreateSongResponseDto(Song song) {
        SongDto songDto = SongMapper.mapFromSongToSongDto(song);
        return new CreateSongResponseDto(songDto);
    }

    public static DeleteSongResponseDto mapFromSongToDeleteSongResponseDto(Long id) {
        return new DeleteSongResponseDto("You deleted song with id: " + id, HttpStatus.OK);
    }

    public static UpdateSongResponseDto mapFromSongToUpdateSongResponseDto(Song song) {
        return new UpdateSongResponseDto(song.getName(), song.getArtist());
    }

    public static PartiallyUpdateSongResponseDto mapFromSongToPartiallyUpdateSongResponseDto(Song song) {
        SongDto songDto = SongMapper.mapFromSongToSongDto(song);
        return new PartiallyUpdateSongResponseDto(songDto);
    }

    public static GetSongResponseDto mapFromSongToGetSongResponseDto(Song song) {
        SongDto songDto = SongMapper.mapFromSongToSongDto(song);
        return new GetSongResponseDto(songDto);
    }

    public static GetAllSongsResponseDto mapFromSongToGetAllSongsResponseDto(List<Song> songs) {
        List<SongDto> songDtos = songs.stream()
                .map(song -> mapFromSongToSongDto(song)).toList();
        return new GetAllSongsResponseDto(songDtos);
    }
}
