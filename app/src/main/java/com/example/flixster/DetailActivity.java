package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.TransitionSet;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixster.databinding.ActivityDetailBinding;
import com.example.flixster.models.Movie;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.parceler.Parcels;

import okhttp3.Headers;

public class DetailActivity extends YouTubeBaseActivity {

    public static final String YOUTUBE_API = "AIzaSyC3O-6Ol5ZCmTzxN-kSNWtneP2e1uDjWoo";
    public static final String MOVIE_API = "https://api.themoviedb.org/3/movie/%d/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

    private final int REGULAR = 0, POPULAR = 1;
    private int movieViewType;

    // Indicates first entered the activity from YoutubePlayerActivity
    private static boolean fullscreenStartActivity = true;

    private int currentMillis;

    private ActivityDetailBinding binding;

    TextView tvDetailTitle;
    TextView tvDetailOverview;
    RatingBar ratingBar;

    YouTubePlayerView youTubePlayerView;
    YouTubePlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        Movie movie = Parcels.unwrap(getIntent().getParcelableExtra("movie"));

        youTubePlayerView = binding.player;
        binding.setMovie(movie);

        currentMillis = getIntent().getIntExtra("restoreMillis", 0);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(String.format(MOVIE_API, movie.getMovieId()), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                try {
                    JSONArray results = json.jsonObject.getJSONArray("results");
                    if (results.length() > 0) {
                        String youtubeKey = results.getJSONObject(0).getString("key");
                        Log.d("DetailActivity", youtubeKey);
                        initializeYoutube(youtubeKey);
                    }
                } catch (JSONException e) {
                    Log.e("DetailActivity", "Failed to parse JSON", e);
                }
            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {

            }
        });
    }

    private void initializeYoutube(String youtubeKey) {
        youTubePlayerView.initialize(YOUTUBE_API, new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
                player = youTubePlayer;
                cueVideo(youtubeKey, wasRestored);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.e("DetailActivity", "onInitializationFailure");

            }
        });
    }

    private void cueVideo(String youtubeKey, boolean wasRestored) {
        if (!wasRestored) {
            player.loadVideo(youtubeKey, currentMillis);
        }
        player.play();
    }

    @Override
    public void onBackPressed() {
        // Both variables are static to override re-initialization
        // Set back to original values when exiting Activity
        fullscreenStartActivity = true;
        super.onBackPressed();
    }
}