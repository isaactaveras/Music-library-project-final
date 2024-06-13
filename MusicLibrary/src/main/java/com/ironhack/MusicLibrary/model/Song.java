package com.ironhack.MusicLibrary.model;

import jakarta.persistence.*;

@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private int duration;

    @ManyToOne
    @JoinColumn()
    private Artist artist;

    private Album album;
    private Genre genre;

}
