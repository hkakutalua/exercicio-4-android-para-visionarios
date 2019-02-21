package com.buka.exercicio4.models;

import java.util.List;

public class Track {
    private int id;
    private String title;
    private int duration;
    private int position;
    private Artist artist;
    private Album album;

    public Track(int id, String title, int duration, int position, Artist artist, Album album) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.position = position;
        this.artist = artist;
        this.album = album;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public int getPosition() {
        return position;
    }

    public Artist getArtist() {
        return artist;
    }

    public String getAlbumTitle() {
        return album.getTitle();
    }

    public String getAlbumCover() {
        return album.getCover();
    }
}
