package com.example.flixster;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixster.databinding.ActivityDetailRegularBinding;

import com.example.flixster.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.parceler.Parcels;

import okhttp3.Headers;

public class DetailActivityRegular extends AppCompatActivity {
    private final int REGULAR = 0;
    public static final String MOVIE_API = "https://api.themoviedb.org/3/movie/%d/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    private ActivityDetailRegularBinding binding;

    TextView tvDetailTitle;
    TextView tvDetailOverview;
    RatingBar ratingBar;
    ImageView ivVideoPlaceholder;
    RelativeLayout regularRelativeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_regular);

        tvDetailTitle = binding.tvDetailTitle;
        tvDetailOverview = binding.tvDetailOverview;
        ratingBar = binding.ratingBar;
        ivVideoPlaceholder = binding.ivVideoPlaceholder;
        regularRelativeContainer = binding.regularRelativeContainer;

        final Fade fadeEnter = new Fade();
        fadeEnter.addTarget(ratingBar);
        fadeEnter.addTarget(tvDetailOverview);

        final Fade fadeOut = new Fade();
        fadeOut.setDuration(500);
        fadeOut.addTarget(R.id.detailContainer);

        Explode explode = new Explode();
        explode.excludeTarget(R.id.ratingBar, true);

        TransitionSet enter = new TransitionSet();
        enter.addTransition(explode);
        enter.addTransition(fadeEnter);

        TransitionSet exit = new TransitionSet();
        exit.addTransition(fadeOut);
        exit.removeTransition(explode);

        getWindow().setEnterTransition(enter);
        getWindow().setExitTransition(exit);

        Movie movie = Parcels.unwrap(getIntent().getParcelableExtra("movie"));

        binding.setMovie(movie);

        regularRelativeContainer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivityRegular.this, YoutubePlayerActivity.class);
                intent.putExtra("movie", Parcels.wrap(movie));
                startActivity(intent);
                finish();
            }
        });

    }

}
