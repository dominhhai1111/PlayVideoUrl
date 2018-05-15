package com.example.admin.playvideourl;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;


public class AddToFavoriteAsyncTask extends AsyncTask<Void, Integer, Void>{
    FilmDetailActivity filmDetailActivity;
    String userId;
    String filmId;
    String URL_ADD_FAV = "http://dominhhaiapps.000webhostapp.com/favorites/";

    public AddToFavoriteAsyncTask(FilmDetailActivity filmDetailActivity, String userId, String filmId) {
        this.filmDetailActivity = filmDetailActivity;
        this.userId = userId;
        this.filmId = filmId;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(this.URL_ADD_FAV)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<Integer> call = apiService.addFavorite(this.userId, this.filmId);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Integer isAdded = response.body();
                Log.d("add:", URL_ADD_FAV + userId + filmId + response.body().toString());
                publishProgress(isAdded);
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
}
