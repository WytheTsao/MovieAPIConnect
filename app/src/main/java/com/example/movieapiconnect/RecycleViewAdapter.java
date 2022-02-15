package com.example.movieapiconnect;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.Viewholder> {

    private CardView cardView;
    private ArrayList<MovieModel> movieModelList;
    private View view;
    DownloadImageTask downloadImageTask;
    public static final String BUNDLE_STRING_NAME = "name";
    public static final String BUNDLE_STRING_ABOUT = "about";
    public static final String BUNDLE_STRING_POSTER = "poster";

    RecyclerView recyclerView;


    public RecycleViewAdapter(ArrayList<MovieModel> movieModelList, RecyclerView recyclerView) {
        this.movieModelList = movieModelList;
        this.recyclerView = recyclerView;
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        private TextView movieName, movieEngName, movieType, movieComeOutDay, movieRunTime;
        private ImageView moviePoster;
        private ProgressBar progressBar;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            moviePoster = itemView.findViewById(R.id.movie_poster);
            movieName = itemView.findViewById(R.id.movie_name);
            progressBar = itemView.findViewById(R.id.progress_bar);

            movieEngName = itemView.findViewById(R.id.movie_eng_name);
            movieType = itemView.findViewById(R.id.movie_type);
            movieComeOutDay = itemView.findViewById(R.id.movie_come_out_day);
            movieRunTime = itemView.findViewById(R.id.movie_run_time);
        }
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_view1, parent, false);
        Viewholder viewHolder = new Viewholder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.movieName.setText(movieModelList.get(position).getName());
        downloadImageTask = (DownloadImageTask) new DownloadImageTask(holder.moviePoster, 0, holder.progressBar).execute(movieModelList.get(position).getPoster());
        recyclerView.addOnScrollListener(new CustomerScrollListener(downloadImageTask, movieModelList.get(position).getPoster()));


        holder.movieEngName.setText(movieModelList.get(position).getEngName());
        holder.movieRunTime.setText(movieModelList.get(position).getRunTime());
        holder.movieType.setText(movieModelList.get(position).getType());
        holder.movieComeOutDay.setText(movieModelList.get(position).getComeOutDate());

//        if (movieModelList.get(position).getComeOutDate().isEmpty()) {
//            holder.movieComeOutDay.setVisibility(View.INVISIBLE);
//        } else {
//            holder.movieComeOutDay.setText(movieModelList.get(position).getComeOutDate());
//        }

        cardView.setOnLongClickListener(v -> {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString(BUNDLE_STRING_NAME, movieModelList.get(holder.getAdapterPosition()).getName());
            bundle.putString(BUNDLE_STRING_ABOUT, movieModelList.get(holder.getAdapterPosition()).getAbout());
            bundle.putString(BUNDLE_STRING_POSTER, movieModelList.get(holder.getAdapterPosition()).getPoster());
            intent.setClass(view.getContext(), MovieInfoActivity.class);
            intent.putExtras(bundle);
            view.getContext().startActivity(intent);
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return (movieModelList == null) ? 0 : movieModelList.size();
    }


}
