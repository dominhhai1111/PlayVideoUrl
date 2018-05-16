package com.example.admin.playvideourl;

import android.content.Intent;
import android.os.AsyncTask;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

import retrofit2.Call;

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
    CommentAdapter commentAdapter;
    List<Comment> commentList = new ArrayList<>();

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

        lvComment = findViewById(R.id.lvComments);
        commentAdapter = new CommentAdapter(this, commentList);
        lvComment.setAdapter(commentAdapter);

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

        ReadCommentAsynctask readCommentAsynctask = new ReadCommentAsynctask();
        readCommentAsynctask.execute();
    }

    public class ReadCommentAsynctask extends AsyncTask<Void, List<com.example.admin.playvideourl.Comment>, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL_GET_COMMENT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            APIService apiService = retrofit.create(APIService.class);
            Call<List<com.example.admin.playvideourl.Comment>> call = apiService.getCommentsByFilmId(film.getId());
            call.enqueue(new Callback<List<com.example.admin.playvideourl.Comment>>() {
                @Override
                public void onResponse(Call<List<com.example.admin.playvideourl.Comment>> call, Response<List<com.example.admin.playvideourl.Comment>> response) {
                    List<com.example.admin.playvideourl.Comment> comments = response.body();
                    Log.d("call-comment", "123");
                    publishProgress(comments);
                }

                @Override
                public void onFailure(Call<List<com.example.admin.playvideourl.Comment>> call, Throwable t) {
                    Log.e(TAG, "onFailure: " + t.getMessage());
                }
            });
            return null;
        }

        @Override
        protected void onProgressUpdate(List<Comment>... values) {
            super.onProgressUpdate(values);
            commentList.clear();
            commentList.addAll(values[0]);
            commentAdapter.notifyDataSetChanged();
        }
    }
}
