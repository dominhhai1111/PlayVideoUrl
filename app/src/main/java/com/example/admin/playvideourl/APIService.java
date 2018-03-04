package com.example.admin.playvideourl;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by admin on 2/23/2018.
 */

public interface APIService {
    @GET("getAllFilms")
    Call<List<Film>> getAllFilms();

    @GET("checkLogin/{email}/{password}")
    Call<LoginResult> checkLogin(@Path("email") String email, @Path("password") String password);
}
