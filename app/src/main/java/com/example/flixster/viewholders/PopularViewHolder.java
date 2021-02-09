package com.example.flixster.viewholders;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flixster.R;

public class PopularViewHolder extends RecyclerView.ViewHolder {

    public ImageView ivPopular;

    public PopularViewHolder(@NonNull View itemView) {
        super(itemView);
        ivPopular = itemView.findViewById(R.id.ivPopular);
    }
}
