package com.example.admin.playvideourl;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 25/2/2018.
 */

public class FilmAsynctask extends AsyncTask<Void, List<Film>, Void>{
    Main2Activity main2Activity;
    String cat_id;
    String URL_GET_FILM = "https://dominhhaiapps.000webhostapp.com/films/getAllFilms/";
    String URL_GET_FILM_CAT = "https://dominhhaiapps.000webhostapp.com/films/getFilmsByCategory/";
    String url;

    public FilmAsynctask(Main2Activity main2Activity, String cat_id) {
        this.main2Activity = main2Activity;
        this.cat_id = cat_id;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        if (!this.cat_id.isEmpty() && this.cat_id != ""){
            url = URL_GET_FILM_CAT + this.cat_id + "/";
        }else{
            url = URL_GET_FILM;
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List<Film>> call = apiService.getAllFilms();
        call.enqueue(new Callback<List<Film>>() {
            @Override
            public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                List<Film> films = response.body();
                publishProgress(films);
                Log.d(TAG, "Films connect - abc");
            }

            @Override
            public void onFailure(Call<List<Film>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
        return null;
    }

    @Override
    protected void onProgressUpdate(List<Film>[] values) {
        super.onProgressUpdate(values);
        this.main2Activity.filmsList.clear();
        // quan trong, ko phai addAll se co ko update
        this.main2Activity.filmsList.addAll(values[0]);
        this.main2Activity.filmAdapter.notifyDataSetChanged();
        Log.d(TAG, "update films: " + this.main2Activity.filmsList.get(0).getName());
    }
}
