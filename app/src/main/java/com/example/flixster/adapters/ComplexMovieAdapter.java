package com.example.flixster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.flixster.R;
import com.example.flixster.models.Movie;
import com.example.flixster.viewholders.PopularViewHolder;
import com.example.flixster.viewholders.RegularViewHolder;

import java.util.List;

public class ComplexMovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Movie> movies;

    private final int REGULAR = 0, POPULAR = 1;

    public ComplexMovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case REGULAR:
                View regularView = inflater.inflate(R.layout.layout_regular, parent, false);
                viewHolder = new RegularViewHolder(regularView);
                break;
            case POPULAR:
                View popularView = inflater.inflate(R.layout.layout_popular, parent, false);
                viewHolder = new PopularViewHolder(popularView);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case REGULAR:
                RegularViewHolder regularView = (RegularViewHolder) holder;
                bindRegularViewHolder(regularView, position);
                break;
            case POPULAR:
                PopularViewHolder popularView = (PopularViewHolder) holder;
                bindPopularViewHolder(popularView, position);
                break;
        }
    }

    private void bindRegularViewHolder(RegularViewHolder regularView, int position) {
        final Movie movie = movies.get(position);
        ImageView ivRegular = regularView.ivRegular;
        String imageUrl;

        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            imageUrl = movie.getBackdropPath();
        } else {
            imageUrl = movie.getPosterPath();
        }

        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(ivRegular);

        regularView.setTitle(movie.getTitle());
        regularView.setOverview(movie.getOverview());
    }

    private void bindPopularViewHolder(PopularViewHolder popularView, int position) {
        final Movie movie = movies.get(position);
        ImageView ivPopular = popularView.ivPopular;

        Glide.with(context).load(movie.getBackdropPath())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(ivPopular);
    }

    @Override
    public int getItemViewType(int position) {
        if (movies.get(position).getAverageRating() > 7.0) {
            return POPULAR;
        } else {
            return REGULAR;
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
