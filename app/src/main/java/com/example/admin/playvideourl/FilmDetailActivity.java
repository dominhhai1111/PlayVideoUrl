package com.example.admin.playvideourl;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class FilmDetailActivity extends AppCompatActivity {
    String URL_GET_COMMENT = "";
    TextView txtName;
    TextView txtTime;
    TextView txtLike;
    TextView txtDetail;
    ImageView imgPoster;
    Button btnWatch;
    Button btnAddToFavorite;
    ListView lvComment;
    String film_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);
        Intent intent1 = getIntent();
        film_url = intent1.getStringExtra("film_url");

        btnWatch = findViewById(R.id.btnWatch);
        btnWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(FilmDetailActivity.this, MainActivity.class);
                intent2.putExtra("film_url", film_url);
                startActivity(intent2);
            }
        });
    }

    public class ReadCommentAsynctask extends AsyncTask<Void, List<com.example.admin.playvideourl.Comment>, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL_GET_COMMENT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            APIService apiService = retrofit.create(APIService.class);
            Call<List<com.example.admin.playvideourl.Comment>> call = apiService.getCommentsByFilmId();
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
    }
}
