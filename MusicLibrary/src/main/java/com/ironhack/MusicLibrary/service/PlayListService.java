package com.ironhack.MusicLibrary.service;

import com.ironhack.MusicLibrary.dtos.PlayListDTO;
import com.ironhack.MusicLibrary.model.Album;
import com.ironhack.MusicLibrary.model.PlayList;
import com.ironhack.MusicLibrary.model.Song;
import com.ironhack.MusicLibrary.model.User;
import com.ironhack.MusicLibrary.repository.PlayListRepository;
import com.ironhack.MusicLibrary.repository.SongRepository;
import com.ironhack.MusicLibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayListService {

    @Autowired
    private PlayListRepository playListRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SongRepository songRepository;

    public PlayList createPlayList(PlayListDTO playListDTO) {
        List<Long> songIds = playListDTO.getSongs().stream().map(Song::getId).collect(Collectors.toList());
        List<Song> songs = songRepository.findAllById(songIds);

        if (songs.size() != songIds.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some songs do not exist");
        }

        // Buscar el usuario por su ID en la base de datos
        User user = userRepository.findById(playListDTO.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        PlayList playList = new PlayList();
        playList.setName(playListDTO.getName());
        playList.setSongs(songs);

        // Asociar el usuario encontrado con la lista de reproducción
        playList.getUsers().add(user);

        return playListRepository.save(playList);
    }

    public PlayList updatePlayList(Long playListId, PlayListDTO playListDTO) {
        PlayList playList = playListRepository.findById(playListId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "PlayList with id " + playListId + " not found"));

        // Verificar que todas las canciones existen
        List<Long> songIds = new ArrayList<>();
        if(playListDTO.getSongs() != null) {
            songIds = playListDTO.getSongs().stream().map(Song::getId).collect(Collectors.toList());
        }
        List<Song> songs = songRepository.findAllById(songIds);

        if (songs.size() != songIds.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some songs do not exist");
        }

        playList.setName(playListDTO.getName());
        playList.setSongs(songs);
        return playListRepository.save(playList);
    }

    public void deletePlayList(Long playListId) {
        playListRepository.findById(playListId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "PlayList with id " + playListId + " not found"));
        playListRepository.deleteById(playListId);
    }

    public PlayList findById(Long playlistId) {
        Optional<PlayList> playList = playListRepository.findById(playlistId);
        if (playList.isPresent()) {
            return playList.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "PlayList id not found.");
        }
    }
}
