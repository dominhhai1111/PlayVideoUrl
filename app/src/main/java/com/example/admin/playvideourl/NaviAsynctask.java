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
 * Created by admin on 4/4/2018.
 */

public class NaviAsynctask extends AsyncTask<Void, List<Category>, Void> {
    NavigationDrawerFragment navigationDrawerFragment;
    String URL_GET_CAT = "https://dominhhaiapps.000webhostapp.com/films/getAllCats/";

    public NaviAsynctask(NavigationDrawerFragment navigationDrawerFragment) {
        this.navigationDrawerFragment = navigationDrawerFragment;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_GET_CAT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List<Category>> call = apiService.getAllCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                List<Category> categories= response.body();
                publishProgress(categories);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
        return null;
    }

    @Override
    protected void onProgressUpdate(List<Category>[] values) {
        super.onProgressUpdate(values);
        this.navigationDrawerFragment.categories.clear();
        // quan trong, ko phai addAll se co ko update
        this.navigationDrawerFragment.categories.addAll(values[0]);
        this.navigationDrawerFragment.categories.add(new Category("0", "Yêu thích"));
        this.navigationDrawerFragment.naviAdapter.notifyDataSetChanged();
    }
}
