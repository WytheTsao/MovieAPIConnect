package com.example.movieapiconnect;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    public Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        String imageURL = "https://firebasestorage.googleapis.com/v0/b/movie-6701f.appspot.com/o/" + urldisplay + "?alt=media";
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(imageURL).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    public void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}
