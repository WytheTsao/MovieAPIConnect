package com.example.movieapiconnect;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.InputStream;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    private ImageView bmImage;
    private int imageFlag;
    private ProgressBar progressBar;

    public DownloadImageTask(ImageView bmImage, int imageFlag, ProgressBar progressBar) {
        this.bmImage = bmImage;
        this.imageFlag = imageFlag;
        this.progressBar = progressBar;
    }

    public Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        String imageURL = "https://firebasestorage.googleapis.com/v0/b/movie-6701f.appspot.com/o/" + urldisplay + "?alt=media";
        Bitmap bitmap = null;
        try {
            InputStream in = new java.net.URL(imageURL).openStream();
            bitmap = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return bitmap;
    }

    public void onPostExecute(Bitmap result) {
        switch (imageFlag) {
            case 0:
                if (result != null) {
                    bmImage.setImageBitmap(getResizedBitmap(result, 130, 200));
                }
                break;
            case 1:
                bmImage.setImageBitmap(result);
        }
        if (progressBar != null) {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    public void onProgressUpdate() {
        progressBar.setVisibility(View.VISIBLE);
    }


    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }
}
