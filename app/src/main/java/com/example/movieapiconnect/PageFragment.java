package com.example.movieapiconnect;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PageFragment extends Fragment {

    MovieAPIService movieAPIService;
    String url;
    RecycleViewAdapter recycleViewAdapter;
    RecyclerView recyclerView;
    ArrayList<MovieModel> movieModelArrayList;
    ProgressBar progressBar;
    RetrofitManager retrofitManager;

    public PageFragment(String url) {
        this.url = url;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        movieModelArrayList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        progressBar.setVisibility(View.VISIBLE);
        retrofitManager = new RetrofitManager(getContext());
        movieAPIService = retrofitManager.retrofitManager().create(MovieAPIService.class);
        Call<ArrayList<MovieModel>> call = movieAPIService.getMovieInfoById(url);
        call.enqueue(new Callback<ArrayList<MovieModel>>() {
            @Override
            public void onResponse(Call<ArrayList<MovieModel>> call, Response<ArrayList<MovieModel>> response) {
                movieModelArrayList = response.body();
                recycleViewAdapter = new RecycleViewAdapter(movieModelArrayList, recyclerView);
                recyclerView.setAdapter(recycleViewAdapter);
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ArrayList<MovieModel>> call, Throwable t) {
                t.printStackTrace();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        recyclerView = view.findViewById(R.id.movie_list_view);
        progressBar = view.findViewById(R.id.progress_bar);
        return view;
    }
}