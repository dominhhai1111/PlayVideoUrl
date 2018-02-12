package com.example.admin.playvideourl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {
    ListView lvFilm;
    FilmAdapter filmAdapter;
    List<Film> films;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        films = setUpFilms();
        lvFilm = findViewById(R.id.lvFilm);
        filmAdapter = new FilmAdapter(this, films);
        lvFilm.setAdapter(filmAdapter);
    }

    private List<Film> setUpFilms(){
        List<Film> filmList = new ArrayList<>();
        filmList.add(new Film("THẦN SẤM 3: THỜI KHẮC TẬN THẾ", 180, "film.mp4"));
        filmList.add(new Film("THẦN SẤM 3: THỜI KHẮC TẬN THẾ", 180, "film.mp4"));
        return filmList;
    }
}
