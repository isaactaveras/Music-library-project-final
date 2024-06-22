package com.ironhack.MusicLibrary.service;

import com.ironhack.MusicLibrary.dtos.PlayListDTO;
import com.ironhack.MusicLibrary.model.PlayList;
import com.ironhack.MusicLibrary.repository.PlayListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PlayListService {

    @Autowired
    private PlayListRepository playListRepository;

    public PlayList createPlayList(PlayListDTO playListDTO) {
        PlayList playList = new PlayList();
        playList.setName(playListDTO.getName());
        playList.setSongs(playListDTO.getSongs());
        playList.setUser(playListDTO.getUser());
        return playListRepository.save(playList);
    }

    public PlayList updatePlayList(Long playListId, PlayListDTO playListDTO) {
        PlayList playList = playListRepository.findById(playListId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "PlayList with id " + playListId + " not found"));
        playList.setName(playListDTO.getName());
        playList.setSongs(playListDTO.getSongs());
        playList.setUser(playListDTO.getUser());
        return playListRepository.save(playList);
    }

    public PlayList deletePlayList(Long playListId) {
        PlayList foundPlayList = playListRepository.findById(playListId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "PlayList with id " + playListId + " not found"));
        playListRepository.deleteById(playListId);
        return foundPlayList;
    }
}
