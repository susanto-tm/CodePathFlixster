package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixster.databinding.ActivityYoutubePlayerBinding;
import com.example.flixster.models.Movie;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.parceler.Parcels;

import okhttp3.Headers;

public class YoutubePlayerActivity extends YouTubeBaseActivity {

    public static final String YOUTUBE_API = "AIzaSyC3O-6Ol5ZCmTzxN-kSNWtneP2e1uDjWoo";
    public static final String MOVIE_API = "https://api.themoviedb.org/3/movie/%d/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

    YouTubePlayerView player;

    private ActivityYoutubePlayerBinding binding;
    private Movie movie;

    private final int REGULAR = 0, POPULAR = 1;

    private int movieViewType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_youtube_player);

        player = binding.player;

        movie = Parcels.unwrap(getIntent().getParcelableExtra("movie"));

        movieViewType = getIntent().getIntExtra("viewType", REGULAR);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(String.format(MOVIE_API, movie.getMovieId()), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                try {
                    JSONArray results = json.jsonObject.getJSONArray("results");
                    if (results.length() > 0) {
                        String youtubeKey = results.getJSONObject(0).getString("key");
                        Log.d("YoutubePlayerActivity", youtubeKey);
                        initializeYoutube(youtubeKey);
                    }
                } catch (JSONException e) {
                    Log.e("YoutubePlayerActivity", "Failed to parse JSON", e);
                }
            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {

            }
        });

    }

    private void initializeYoutube(String youtubeKey) {
        player.initialize(YOUTUBE_API, new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
                youTubePlayer.setFullscreen(true);

                youTubePlayer.setOnFullscreenListener(new YouTubePlayer.OnFullscreenListener() {

                    @Override
                    public void onFullscreen(boolean isFullscreen) {
                        if (isFullscreen) {
                            youTubePlayer.loadVideo(youtubeKey);
                            youTubePlayer.play();
                        } else {
                            int restoreMillis = youTubePlayer.getCurrentTimeMillis();
                            Log.d("YoutubeActivity", "Current Millis: " + restoreMillis);
                            youTubePlayer.setFullscreen(false);

                            Intent intent;
                            if (movieViewType == POPULAR) {
                                intent = new Intent(YoutubePlayerActivity.this, DetailActivity.class);
                                intent.putExtra("restoreMillis", restoreMillis);
                            }
                            else {
                                intent = new Intent(YoutubePlayerActivity.this, DetailActivityRegular.class);
                            }
                            intent.putExtra("movie", Parcels.wrap(movie));
                            intent.putExtra("viewType", getIntent().getIntExtra("viewType", 0));
                            youTubePlayer.release();
                            startActivity(intent);
                        }
                    }
                });
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }
}