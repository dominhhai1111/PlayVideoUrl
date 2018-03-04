package com.example.admin.playvideourl;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

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

public class CheckLoginAsyncTask extends AsyncTask<Void, LoginResult, Void> {
    LoginActivity loginActivity;
    String URL_CHECK_LOGIN= "https://dominhhaiapps.000webhostapp.com/users/";

    public CheckLoginAsyncTask(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_CHECK_LOGIN)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<LoginResult> call = apiService.checkLogin("a", "b");
        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                LoginResult loginResult = response.body();
                publishProgress(loginResult);
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Log.e(TAG, "Call failed");
            }
        });
        return null;
    }

    @Override
    protected void onProgressUpdate(LoginResult... values) {
        super.onProgressUpdate(values);
        Toast.makeText(this.loginActivity, values[0].getMessage(), Toast.LENGTH_LONG).show();
    }
}
