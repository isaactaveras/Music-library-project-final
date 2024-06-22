package com.ironhack.MusicLibrary.controller;

import com.ironhack.MusicLibrary.dtos.AlbumDTO;
import com.ironhack.MusicLibrary.model.Album;
import com.ironhack.MusicLibrary.repository.AlbumControllerInterface;
import com.ironhack.MusicLibrary.repository.AlbumRepository;
import com.ironhack.MusicLibrary.service.AlbumService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/albums")
@Validated
public class AlbumController implements AlbumControllerInterface {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private AlbumService albumService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Album createAlbum(@Valid @RequestBody AlbumDTO albumDTO) {
        return albumService.createAlbum(albumDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Album updateAlbum(@PathVariable(name = "id") Long albumId, @Valid @RequestBody AlbumDTO albumDTO) {
        return albumService.updateAlbum(albumId, albumDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Album getAlbumById(@PathVariable(name = "id") Long albumId) {
        return albumRepository.findById(albumId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Album not found"));
    }

    @DeleteMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public Album deleteAlbum(@PathVariable(name = "id") Long albumId) {
        return albumService.deleteAlbum(albumId);
    }

}
