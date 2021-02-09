package com.example.flixster.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flixster.R;

public class RegularViewHolder extends RecyclerView.ViewHolder {

    public ImageView ivRegular;
    public TextView tvTitle;
    public TextView tvOverview;

    public RegularViewHolder(@NonNull View itemView) {
        super(itemView);
        ivRegular = (ImageView) itemView.findViewById(R.id.ivRegular);
        tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        tvOverview = (TextView) itemView.findViewById(R.id.tvOverview);
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setOverview(String overview) {
        tvOverview.setText(overview);
    }
}
