package com.buka.exercicio4.models;

import com.google.gson.annotations.SerializedName;

public class Album {
    private int id;
    private String title;

    @SerializedName("cover_big")
    private String cover;

    public Album(int id, String title, String cover) {
        this.id = id;
        this.title = title;
        this.cover = cover;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCover() {
        return cover;
    }
}
