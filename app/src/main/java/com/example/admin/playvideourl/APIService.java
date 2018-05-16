package com.example.admin.playvideourl;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by admin on 2/23/2018.
 */

public interface APIService {
    public static final String URL_USER= "https://dominhhaiapps.000webhostapp.com/users/";

    @GET("getAllFilms")
    Call<List<Film>> getAllFilms();

    @GET("getAllCategories")
    Call<List<Category>> getAllCategories();

    @GET("getFilmsByCategory")
    Call<List<Film>> getFilmsByCategory(@Path("cat_id") String cat_id);

    @GET("getCommentsByFilmId")
    Call<List<Comment>> getCommentsByFilmId();

    @GET("checkLogin/{email}/{password}")
    Call<LoginResult> checkLogin(@Path("email") String email, @Path("password") String password);

    @GET("addFilmToFavorite/{userId}/{filmId}")
    Call<Integer> addFavorite(@Path("userId") String userId, @Path("filmId") String filmId);

    public static final Retrofit retrofit_login = new Retrofit.Builder()
            .baseUrl(URL_USER)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @FormUrlEncoded
    @POST("checkRegisterUserAccount")
    Call<RegisterResult> checkRegisterUserAccount(@Field("email") String email, @Field("password") String password);

    public static final Retrofit retrofit_sign_up= new Retrofit.Builder()
            .baseUrl(URL_USER)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
