package com.example.flixster.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flixster.R;
import com.example.flixster.databinding.LayoutPopularBinding;

public class PopularViewHolder extends RecyclerView.ViewHolder {

    public RelativeLayout popularContainer;
    public ImageView ivPopular;

    public PopularViewHolder(@NonNull LayoutPopularBinding binding) {
        super(binding.getRoot());
        popularContainer = binding.popularContainer;
        ivPopular = binding.ivPopular;
    }
}
