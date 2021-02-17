package com.example.flixster.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flixster.R;
import com.example.flixster.databinding.LayoutRegularBinding;

public class RegularViewHolder extends RecyclerView.ViewHolder {

    public RelativeLayout regularContainer;
    public ImageView ivRegular;
    public TextView tvTitle;
    public TextView tvOverview;


    public RegularViewHolder(@NonNull LayoutRegularBinding binding) {
        super(binding.getRoot());
        ivRegular = binding.ivRegular;
        tvTitle = binding.tvTitle;
        tvOverview = binding.tvOverview;
        regularContainer = binding.regularContainer;
    }
}
