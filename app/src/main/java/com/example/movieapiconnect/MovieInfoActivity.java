package com.example.movieapiconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.InputStream;
import java.util.concurrent.Executors;

public class MovieInfoActivity extends AppCompatActivity {

    TextView movieName, movieAbout;
    ImageView imageView;
    DownloadImageTask downloadImageTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);
        movieName = findViewById(R.id.movie_name);
        movieAbout = findViewById(R.id.movie_about);
        imageView = findViewById(R.id.imageView);
        movieName.setText(getIntent().getStringExtra("name"));
        movieAbout.setText(getIntent().getStringExtra("about"));
        downloadImageTask = (DownloadImageTask) new DownloadImageTask(imageView).execute(getIntent().getStringExtra("poster"));
    }
}