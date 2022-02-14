package com.example.movieapiconnect;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieAPIService {

    @GET("/film/index.json/")
    Call<ArrayList<MovieModel>> getMovie();

    @GET("/film/{id}/")
    Call<ArrayList<MovieModel>> getMovieInfoById(@Path("id") String id);
}
