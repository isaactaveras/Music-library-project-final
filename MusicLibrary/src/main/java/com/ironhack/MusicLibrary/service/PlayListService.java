package com.ironhack.MusicLibrary.service;

import com.ironhack.MusicLibrary.dtos.PlayListDTO;
import com.ironhack.MusicLibrary.model.PlayList;
import com.ironhack.MusicLibrary.model.Song;
import com.ironhack.MusicLibrary.repository.PlayListRepository;
import com.ironhack.MusicLibrary.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayListService {

    @Autowired
    private PlayListRepository playListRepository;

    @Autowired
    private SongRepository songRepository;

    public PlayList createPlayList(PlayListDTO playListDTO) {
        List<Long> songIds = playListDTO.getSongs().stream().map(Song::getId).collect(Collectors.toList());
        List<Song> songs = songRepository.findAllById(songIds);

        if (songs.size() != songIds.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some songs do not exist");
        }

        PlayList playList = new PlayList();
        playList.setName(playListDTO.getName());
        playList.setSongs(songs);
        return playListRepository.save(playList);
    }

    public PlayList updatePlayList(Long playListId, PlayListDTO playListDTO) {
        PlayList playList = playListRepository.findById(playListId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "PlayList with id " + playListId + " not found"));

        // Verificar que todas las canciones existen
        List<Long> songIds = playListDTO.getSongs().stream().map(Song::getId).collect(Collectors.toList());
        List<Song> songs = songRepository.findAllById(songIds);

        if (songs.size() != songIds.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some songs do not exist");
        }

        playList.setName(playListDTO.getName());
        playList.setSongs(songs);
        return playListRepository.save(playList);
    }

    public PlayList deletePlayList(Long playListId) {
        PlayList foundPlayList = playListRepository.findById(playListId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "PlayList with id " + playListId + " not found"));
        playListRepository.deleteById(playListId);
        return foundPlayList;
    }
}
