package com.example.flixster.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.flixster.DetailActivity;
import com.example.flixster.MainActivity;
import com.example.flixster.R;
import com.example.flixster.models.Movie;
import com.example.flixster.viewholders.PopularViewHolder;
import com.example.flixster.viewholders.RegularViewHolder;

import org.parceler.Parcels;

import java.util.List;

public class ComplexMovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final List<Movie> movies;

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
        bindContainerClickListener(regularView, regularView.regularContainer, movie);
    }

    private void bindPopularViewHolder(PopularViewHolder popularView, int position) {
        final Movie movie = movies.get(position);
        ImageView ivPopular = popularView.ivPopular;

        Glide.with(context).load(movie.getBackdropPath())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(ivPopular);

        bindContainerClickListener(popularView, popularView.popularContainer, movie);
    }

    private void bindContainerClickListener(RecyclerView.ViewHolder holder, RelativeLayout viewContainer, Movie movie) {
        viewContainer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("movie", Parcels.wrap(movie));
                if (holder.getItemViewType() == REGULAR) {
                    RegularViewHolder regularHolder = (RegularViewHolder) holder;
//                    Pair<View, String> p1 = Pair.create(regularHolder.tvTitle, "movieTitle");
//                    Pair<View, String> p2 = Pair.create(regularHolder.tvOverview, "movieOverview");
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation((Activity) context, regularHolder.tvTitle, "movieTitle");
                    context.startActivity(intent, options.toBundle());
                } else {
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context);
                    context.startActivity(intent, options.toBundle());
                }
            }
        });
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
