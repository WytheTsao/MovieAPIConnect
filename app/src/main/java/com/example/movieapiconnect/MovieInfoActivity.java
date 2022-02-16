package com.example.movieapiconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.ActionBar;
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
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class MovieInfoActivity extends YouTubeBaseActivity {

    TextView movieName, movieAbout;
    ImageView imageView;
    DownloadImageTask downloadImageTask;
    ProgressBar progressBar;
    YouTubePlayerView youTubePlayerView;
    public static final String BUNDLE_STRING_NAME = "name";
    public static final String BUNDLE_STRING_ABOUT = "about";
    public static final String BUNDLE_STRING_POSTER = "poster";
    public static final String BUNDLE_LIST_VIDEO = "videolist";
    public static final String API_KEY = "AIzaSyBp486m8ULRpEvLV0T_8PZSSJo814Y9qGQ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> MovieInfoActivity.super.onBackPressed());
        movieName = findViewById(R.id.movie_name);
        movieAbout = findViewById(R.id.movie_about);
        imageView = findViewById(R.id.imageView);
        progressBar = findViewById(R.id.progress_bar);
        youTubePlayerView = findViewById(R.id.youTube_player);
        youTubePlayerView.initialize(API_KEY, youTubeListener);
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

    public String replaceURL(String url) {
        return url.replace("https://www.youtube.com/embed/", "");
    }

    public YouTubePlayer.OnInitializedListener youTubeListener = new YouTubePlayer.OnInitializedListener() {
        @Override
        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
            if (!b) {
                ArrayList<String> youTubeList;
                youTubeList = getIntent().getStringArrayListExtra(BUNDLE_LIST_VIDEO);
                youTubePlayer.loadVideo(replaceURL(youTubeList.get(0)));
                youTubePlayer.play();
            }
        }

        @Override
        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

        }
    };

}