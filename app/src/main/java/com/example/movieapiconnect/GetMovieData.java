package com.example.movieapiconnect;

import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetMovieData {

    MovieAPIService movieAPIService;
    String movieData;
    String url;

    public GetMovieData(String url) {
        this.url = url;
    }
    
    public String GetData() {
        movieAPIService = RetrofitManager.getInstance().getAPI();
        Call<ArrayList<MovieModel>> call = movieAPIService.getMovie();
        call.enqueue(new Callback<ArrayList<MovieModel>>() {

            @Override
            public void onResponse(Call<ArrayList<MovieModel>> call, Response<ArrayList<MovieModel>> response) {
                movieData = response.body().get(1).getName();
                Log.e("name", movieData);
            }

            @Override
            public void onFailure(Call<ArrayList<MovieModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return movieData;
    }
}
