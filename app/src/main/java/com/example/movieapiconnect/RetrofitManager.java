package com.example.movieapiconnect;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private static RetrofitManager mInstance = new RetrofitManager();
    private String baseURL = "https://movie-6701f-default-rtdb.asia-southeast1.firebasedatabase.app/";
    private MovieAPIService movieAPIService;

    private RetrofitManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        movieAPIService = retrofit.create(MovieAPIService.class);
    }

    public static RetrofitManager getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitManager();
        }
        return mInstance;
    }

    public MovieAPIService getAPI() {
        return movieAPIService;
    }

}
