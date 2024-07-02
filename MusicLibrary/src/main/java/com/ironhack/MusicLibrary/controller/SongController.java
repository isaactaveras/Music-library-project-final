package com.ironhack.MusicLibrary.controller;

import com.ironhack.MusicLibrary.dtos.SongDTO;
import com.ironhack.MusicLibrary.model.Song;
import com.ironhack.MusicLibrary.repository.SongControllerInterface;
import com.ironhack.MusicLibrary.repository.SongRepository;
import com.ironhack.MusicLibrary.service.SongService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/songs")
@Validated
public class SongController implements SongControllerInterface {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private SongService songService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Song createSong(@Valid @RequestBody SongDTO songDTO) {
        return songService.createSong(songDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Song updateSong(@PathVariable(name = "id") Long songId, @Valid @RequestBody SongDTO songDTO) {
        return songService.updateSong(songId, songDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Song getSongById(@PathVariable(name = "id") Long songId) {
        return songRepository.findById(songId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Song not found"));
    }

    @DeleteMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Song deleteSong(@PathVariable(name = "id") Long songId) {
        return songService.deleteSong(songId);
    }

}
