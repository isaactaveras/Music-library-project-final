package com.ironhack.MusicLibrary.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import javax.sound.midi.Track;
import java.util.List;

@Entity
public class MusicLibrary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private List<Album> albums;
    private List<Song> songs;
    private List<Artist> artists;
    private List<Genre> genres;
}
