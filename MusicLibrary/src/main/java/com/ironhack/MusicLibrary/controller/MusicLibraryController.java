package com.ironhack.MusicLibrary.controller;

import com.ironhack.MusicLibrary.dtos.MusicLibraryDTO;
import com.ironhack.MusicLibrary.model.MusicLibrary;
import com.ironhack.MusicLibrary.repository.MusicLibraryControllerInterface;
import com.ironhack.MusicLibrary.repository.MusicLibraryRepository;
import com.ironhack.MusicLibrary.service.MusicLibraryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/music_libraries")
@Validated
public class MusicLibraryController implements MusicLibraryControllerInterface {

    @Autowired
    private MusicLibraryRepository musicLibraryRepository;

    @Autowired
    private MusicLibraryService musicLibraryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MusicLibrary createMusicLibrary(@Valid @RequestBody MusicLibraryDTO musicLibraryDTO) {
        return musicLibraryService.createMusicLibrary(musicLibraryDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MusicLibrary updateMusicLibrary(@PathVariable(name = "id") Long musicLibraryId, @Valid @RequestBody MusicLibraryDTO musicLibraryDTO) {
        return musicLibraryService.updateMusicLibrary(musicLibraryId, musicLibraryDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MusicLibrary> getAllMusicLibrary() {
        return musicLibraryRepository.findAll();
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MusicLibrary getMusicLibraryById(@PathVariable(name = "id") Long musicLibraryId) {
        return musicLibraryRepository.findById(musicLibraryId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Music Library Not Found"));
    }

    @DeleteMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public MusicLibrary deleteMusicLibrary(@PathVariable(name = "id") Long musicLibraryId) {
        return musicLibraryService.deleteMusicLibrary(musicLibraryId);
    }


}
