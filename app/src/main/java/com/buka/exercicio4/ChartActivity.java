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

// -- Passos a fazer na classe ChartViewModel
// TODO: Passo 1 - Crie uma classe de nome ChartViewModel que extenda de ViewModel
// TODO: Passo 2 - Instancie a classe DeezerApi no construtor do view model e guarde como campo privado
// TODO: Passo 4 - Remova todo código que faz referência à interface do usuário na AsyncTask
// TODO: Passo 5 - Crie o método público fetchChart(), neste método execute a AsyncTask
// TODO: Passo 6 - Crie um campo privado do tipo MutableLiveData<> que notifica à interface do usuário uma ArrayList de Track's (música)
// TODO: Passo 7 - Crie um getter para este MutableLiveData<>, o retorno do método tem de ser LiveData<>
// TODO: Passo 8 - No método onPostExecute da AsyncTask, use o MutableLiveData para notificar a interface sobre a lista de Track's (música)

public class ChartActivity extends AppCompatActivity {
    private static final String TAG = ChartActivity.class.getSimpleName();

    private DeezerApi deezerApi;
    private TracksAdapter tracksAdapter;
    private ProgressBar loadingProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        RecyclerView tracksRecyclerView = findViewById(R.id.recyclerview_tracks);
        loadingProgressBar = findViewById(R.id.progressbar_loading);

        tracksAdapter = new TracksAdapter();
        tracksRecyclerView.setAdapter(tracksAdapter);
        tracksRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // TODO: Passo 11 - Obtenha a instância do ChartViewModel

        // TODO: Passo 12 - Faça esta activity "observar" pelas mudanças que ocorrem no ChartViewModel
        // TODO: Passo 13 - Dentro do Observer, atualize o RecyclerView com a nova lista de músicas (tracks)

        // TODO: Passo 14 - Invoque o método fetchChart para realizar a requisição

        // TODO: Passo 9 - Remova o código que tenha a ver com o Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.deezer.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        deezerApi = retrofit.create(DeezerApi.class);

        // TODO: Passo 10 - Remova a instanciação e execução desta AsyncTask
        ChartRequestTask chartRequestTask = new ChartRequestTask();
        chartRequestTask.execute();
    }

    // TODO: Passo 3 - Mova a ChartRequestTask para dentro da classe ChartViewModel
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
