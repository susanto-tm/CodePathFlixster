package com.example.flixster.viewholders;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flixster.R;
import com.example.flixster.YoutubePlayerActivity;
import com.example.flixster.databinding.LayoutPopularBinding;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

public class PopularViewHolder extends RecyclerView.ViewHolder {

    public RelativeLayout popularContainer;
    public ImageView ivPopular;

    public LayoutPopularBinding binding;
    private Context context;

    private final int POPULAR = 1;

    public PopularViewHolder(@NonNull LayoutPopularBinding binding, Context context) {
        super(binding.getRoot());

        this.binding = binding;
        this.context = context;

        popularContainer = this.binding.popularContainer;
        ivPopular = this.binding.ivPopular;
    }

    public void bind(Movie movie) {
        binding.setMovie(movie);
        binding.executePendingBindings();

        popularContainer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, YoutubePlayerActivity.class);
                intent.putExtra("movie", Parcels.wrap(movie));
                intent.putExtra("viewType", POPULAR);
                context.startActivity(intent);
            }
        });
    }
}
