package com.example.movieapiconnect;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PageFragment extends Fragment {

    MovieAPIService movieAPIService;
    String url;
    GetMovieData getMovieData;

    public PageFragment(String url) {
        this.url = url;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMovieData = new GetMovieData(url);
        getMovieData.GetData();


//        movieAPIService = RetrofitManager.getInstance().getAPI();
//        Call<ArrayList<MovieModel>> call = movieAPIService.getMovieInfoById("/film/index.json/");
//        call.enqueue(new Callback<ArrayList<MovieModel>>() {
//
//            @Override
//            public void onResponse(Call<ArrayList<MovieModel>> call, Response<ArrayList<MovieModel>> response) {
//                String name = response.body().get(1).getName();
//                Log.e("name", name);
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<MovieModel>> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page, container, false);
    }
}