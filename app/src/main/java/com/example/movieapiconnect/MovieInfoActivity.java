package com.example.movieapiconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
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
    ProgressBar progressBar;
    public static final String BUNDLE_STRING_NAME = "name";
    public static final String BUNDLE_STRING_ABOUT = "about";
    public static final String BUNDLE_STRING_POSTER = "poster";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        movieName = findViewById(R.id.movie_name);
        movieAbout = findViewById(R.id.movie_about);
        imageView = findViewById(R.id.imageView);
        progressBar = findViewById(R.id.progress_bar);
        movieName.setText(getIntent().getStringExtra(BUNDLE_STRING_NAME));
        movieAbout.setText(getIntent().getStringExtra(BUNDLE_STRING_ABOUT));
        downloadImageTask = (DownloadImageTask) new DownloadImageTask(imageView, 1, progressBar).execute(getIntent().getStringExtra(BUNDLE_STRING_POSTER));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}