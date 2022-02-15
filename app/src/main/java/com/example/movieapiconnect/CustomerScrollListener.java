package com.example.movieapiconnect;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomerScrollListener extends RecyclerView.OnScrollListener {
    DownloadImageTask downloadImageTask;
    ImageView bmImage;
    int imageFlag;
    String url;

    public CustomerScrollListener(DownloadImageTask downloadImageTask, String url) {
        this.downloadImageTask = downloadImageTask;
        this.url = url;
    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {


//            downloadImageTask.cancel(true);
//            downloadImageTask.execute(url);
        }
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
    }
}
