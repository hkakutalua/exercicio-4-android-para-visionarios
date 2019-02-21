package com.buka.exercicio4.models;

import java.util.List;

public class DeezerChart {
    private Tracks tracks;

    public List<Track> getTracks() {
        return tracks.getData();
    }

    class Tracks {
        private List<Track> data;

        public Tracks(List<Track> data) {
            this.data = data;
        }

        public List<Track> getData() {
            return data;
        }
    }
}
