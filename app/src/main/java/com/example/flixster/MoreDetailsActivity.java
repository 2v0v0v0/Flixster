package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class MoreDetailsActivity extends AppCompatActivity {

    TextView tvTitle;
    RatingBar rbRating;
    ImageView ivPoster;
    TextView tvRating;
    TextView tvOverview;
    TextView tvPopularity;
    TextView tvReleaseDate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_details);
    }
}