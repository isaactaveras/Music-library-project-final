package com.ironhack.MusicLibrary.service;

import com.ironhack.MusicLibrary.dtos.SongDTO;
import com.ironhack.MusicLibrary.model.Album;
import com.ironhack.MusicLibrary.model.Artist;
import com.ironhack.MusicLibrary.model.Genre;
import com.ironhack.MusicLibrary.model.Song;
import com.ironhack.MusicLibrary.repository.AlbumRepository;
import com.ironhack.MusicLibrary.repository.ArtistRepository;
import com.ironhack.MusicLibrary.repository.GenreRepository;
import com.ironhack.MusicLibrary.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private AlbumRepository albumRepository;

    public Song createSong(SongDTO songDTO) {
        Artist artist = artistRepository.findById(songDTO.getArtistId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artist not found"));
        Genre genre = genreRepository.findById(songDTO.getGenreId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found"));
        Album album = albumRepository.findById(songDTO.getAlbumId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Album not found"));
        Song song = new Song();
        song.setTitle(songDTO.getTitle());
        song.setDuration(songDTO.getDuration());
        song.setArtist(artist);
        song.setAlbum(album);
        song.setGenre(genre);
        return songRepository.save(song);
    }

    public Song updateSong(Long sondId, SongDTO songDTO) {
        Artist artist = artistRepository.findById(songDTO.getArtistId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artist not found"));
        Genre genre = genreRepository.findById(songDTO.getGenreId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found"));
        Album album = albumRepository.findById(songDTO.getAlbumId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Album not found"));
        Song song = songRepository.findById(sondId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Song not found"));

        if(songDTO.getTitle() != null && !songDTO.getTitle().isEmpty()){
            song.setTitle(songDTO.getTitle());
        }
        if(songDTO.getDuration() != null) song.setDuration(songDTO.getDuration());
        if(songDTO.getGenreId() != null) song.setGenre(genre);
        if(songDTO.getArtistId() != null) song.setArtist(artist);
        if(songDTO.getAlbumId() != null) song.setAlbum(album);

        return songRepository.save(song);
    }

    public Song deleteSong(Long songId) {
        Song foundSong = songRepository.findById(songId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Song not found"));
        songRepository.deleteById(songId);
        return foundSong;
    }

    public Song findById(Long songId) {
        Optional<Song> song = songRepository.findById(songId);
        if (song.isPresent()) {
            return song.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Song id not found.");
        }
    }
}
