package com.example.admin.playvideourl;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by admin on 2/23/2018.
 */

public interface APIService {
    @GET("getAllFilms")
    Call<List<Film>> getAllFilms();

    @GET("getALlUsers")
    Call<List<User>> getAllUsers();
}
