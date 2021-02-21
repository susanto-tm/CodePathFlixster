package com.example.flixster.viewholders;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flixster.DetailActivityRegular;
import com.example.flixster.MainActivity;
import com.example.flixster.R;
import com.example.flixster.databinding.LayoutRegularBinding;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

public class RegularViewHolder extends RecyclerView.ViewHolder {

    public RelativeLayout regularContainer;
    public ImageView ivRegular;
    public TextView tvTitle;
    public TextView tvOverview;

    public final LayoutRegularBinding binding;
    private Context context;

    private final int REGULAR = 0;

    public RegularViewHolder(@NonNull LayoutRegularBinding binding, Context context) {
        super(binding.getRoot());

        this.binding = binding;
        this.context = context;

        ivRegular = this.binding.ivRegular;
        tvTitle = this.binding.tvTitle;
        tvOverview = this.binding.tvOverview;
        regularContainer = this.binding.regularContainer;
    }

    public void bind(Movie movie) {
        binding.setMovie(movie);
        binding.executePendingBindings();

        regularContainer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivityRegular.class);
                intent.putExtra("movie", Parcels.wrap(movie));
                intent.putExtra("viewType", REGULAR);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation((Activity) context, tvTitle, "movieTitle");
                context.startActivity(intent, options.toBundle());
            }
        });

    }
}
