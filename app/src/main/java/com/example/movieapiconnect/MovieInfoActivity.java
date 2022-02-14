package com.example.movieapiconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.concurrent.Executors;

public class MovieInfoActivity extends AppCompatActivity {

    TextView movieName, movieAbout;
    String name;
    String imageURL;
    ImageView imageView;
    AsyncTask<String, Void, Bitmap> downloadImageTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);
        movieName = findViewById(R.id.movie_name);
        movieAbout = findViewById(R.id.movie_about);
        imageView = findViewById(R.id.imageView);
        imageURL = "https://firebasestorage.googleapis.com/v0/b/movie-6701f.appspot.com/o/" + getIntent().getStringExtra("poster") + "?alt=media";
        name = getIntent().getStringExtra("name");
        movieName.setText(name);
        movieAbout.setText(getIntent().getStringExtra("about"));
        downloadImageTask = new DownloadImageTask(imageView).execute(imageURL);

    }

}