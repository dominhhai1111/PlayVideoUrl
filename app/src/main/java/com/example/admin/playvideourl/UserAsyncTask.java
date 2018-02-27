package com.example.admin.playvideourl;

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
 * Created by admin on 2/27/2018.
 */

public class UserAsyncTask extends AsyncTask<Void, List<User>, Void> {
    LoginActivity loginActivity;
    String URL_GET_USER = "https://dominhhaiapps.000webhostapp.com/users/";
    
    @Override
    protected Void doInBackground(Void... voids) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_GET_USER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List<User>> call = apiService.getAllUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> Users = response.body();
                publishProgress(Users);
                Log.d(TAG, "Users connect - abc");
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
        return null;
    }

    @Override
    protected void onProgressUpdate(List<User>[] values) {
        super.onProgressUpdate(values);
    }
}
