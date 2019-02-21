package com.buka.exercicio4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.buka.exercicio4.api.DeezerApi;
import com.buka.exercicio4.models.DeezerChart;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private DeezerApi deezerApi;
    private TracksAdapter tracksAdapter;
    private ProgressBar loadingProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView tracksRecyclerView = findViewById(R.id.recyclerview_tracks);
        loadingProgressBar = findViewById(R.id.progressbar_loading);

        tracksAdapter = new TracksAdapter();
        tracksRecyclerView.setAdapter(tracksAdapter);
        tracksRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.deezer.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        deezerApi = retrofit.create(DeezerApi.class);

        ChartRequestTask chartRequestTask = new ChartRequestTask();
        chartRequestTask.execute();
    }

    class ChartRequestTask extends AsyncTask<Void, Void, DeezerChart> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loadingProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected DeezerChart doInBackground(Void... voids) {
            try {
                Response<DeezerChart> response = deezerApi.getTopTracks().execute();
                if (response.isSuccessful()) {
                    return response.body();
                } else {
                    Log.e(TAG, response.message());
                    return null;
                }

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(DeezerChart deezerChart) {
            super.onPostExecute(deezerChart);
            loadingProgressBar.setVisibility(View.INVISIBLE);
            tracksAdapter.setTracks(deezerChart.getTracks());
        }
    }
}
