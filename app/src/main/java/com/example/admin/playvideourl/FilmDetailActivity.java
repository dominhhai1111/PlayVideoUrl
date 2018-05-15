package com.example.admin.playvideourl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Comment;

import java.util.ArrayList;

import retrofit2.Call;

public class FilmDetailActivity extends AppCompatActivity {
    TextView txtName;
    TextView txtTime;
    TextView txtLike;
    TextView txtDetail;
    ImageView imgPoster;
    Button btnWatch;
    Button btnAddToFavorite;
    ListView lvComment;

    Film film;
    String userId;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);
        Intent intent1 = getIntent();
        film = (Film) intent1.getSerializableExtra("film");

        sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
        this.userId = sharedPreferences.getString("user_id", "");

        txtName = findViewById(R.id.txtName);
        txtName.setText(film.getName());
        txtTime = findViewById(R.id.txtTime);
        txtTime.setText(film.getTime());
        txtDetail = findViewById(R.id.txtDetail);
        txtDetail.setText(film.getDetail());

        btnWatch = findViewById(R.id.btnWatch);
        btnWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FilmDetailActivity.this, "watch", Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(FilmDetailActivity.this, MainActivity.class);
                intent2.putExtra("film_url", film.getUrl());
                startActivity(intent2);
            }
        });

        btnAddToFavorite = findViewById(R.id.btnAddToFavorite);
        btnAddToFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddToFavoriteAsyncTask addToFavoriteAsyncTask = new AddToFavoriteAsyncTask(
                        FilmDetailActivity.this,
                        userId,
                        film.getId());
                addToFavoriteAsyncTask.execute();
            }
        });
    }

}


