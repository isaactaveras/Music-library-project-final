package com.ironhack.MusicLibrary.controller;

import com.ironhack.MusicLibrary.dtos.ArtistDTO;
import com.ironhack.MusicLibrary.model.Artist;
import com.ironhack.MusicLibrary.repository.ArtistControllerInterface;
import com.ironhack.MusicLibrary.repository.ArtistRepository;
import com.ironhack.MusicLibrary.service.ArtistService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/artists")
@Validated
public class ArtistController implements ArtistControllerInterface {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private ArtistService artistService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Artist createArtist(@Valid @RequestBody ArtistDTO artistDTO) {
        return artistService.createArtist(artistDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Artist updateArtist(@PathVariable(name = "id") Long artistId, @Valid @RequestBody ArtistDTO artistDTO) {
        return artistService.updateArtist(artistId, artistDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Artist getArtistById(@PathVariable(name = "id") Long artistId) {
        return artistRepository.findById(artistId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Artist not found"));
    }

    @DeleteMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public Artist deleteArtist(@PathVariable(name = "id") Long artistId) {
        return artistService.deleteArtist(artistId);
    }

}
