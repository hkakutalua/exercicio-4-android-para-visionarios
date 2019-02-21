package com.buka.exercicio4.api;

import com.buka.exercicio4.models.DeezerChart;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DeezerApi {
    @GET("chart/tracks")
    Call<DeezerChart> getTopTracks();
}
