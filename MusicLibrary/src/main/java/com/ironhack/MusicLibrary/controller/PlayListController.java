package com.ironhack.MusicLibrary.controller;

import com.ironhack.MusicLibrary.dtos.PlayListDTO;
import com.ironhack.MusicLibrary.model.PlayList;
import com.ironhack.MusicLibrary.repository.PlayListControllerInterface;
import com.ironhack.MusicLibrary.repository.PlayListRepository;
import com.ironhack.MusicLibrary.service.PlayListService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/playlists")
@Validated
public class PlayListController implements PlayListControllerInterface {

    @Autowired
    private PlayListRepository playListRepository;

    @Autowired
    private PlayListService playListService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlayList createPlayList(@Valid @RequestBody PlayListDTO playListDTO) {
        return playListService.createPlayList(playListDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PlayList updatePlayList(@PathVariable(name = "id") Long playListId, @Valid @RequestBody PlayListDTO playListDTO)  {
        return playListService.updatePlayList(playListId, playListDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PlayList> getAllPlayLists() {
        return playListRepository.findAll();
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PlayList getPlayListById(@PathVariable(name = "id") Long playListId) {
        return playListRepository.findById(playListId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "PlayList with id " + playListId + " not found"));
    }

    @DeleteMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public PlayList deletePlayList(@PathVariable(name = "id") Long playListId) {
        return playListService.deletePlayList(playListId);
    }
}
