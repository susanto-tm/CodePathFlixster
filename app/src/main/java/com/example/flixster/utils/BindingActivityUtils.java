package com.example.flixster.utils;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.example.flixster.R;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class BindingActivityUtils {
    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .placeholder(R.drawable.placeholder)
                .transform(new RoundedCornersTransformation(30, 10))
                .error(R.drawable.error)
                .into(view);
    }
}
