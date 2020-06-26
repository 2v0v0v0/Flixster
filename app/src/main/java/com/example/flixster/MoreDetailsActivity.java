package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.example.flixster.MainActivity.MOVIE_TITLE;
import static com.example.flixster.MainActivity.MOVIE_OVERVIEW;
import static com.example.flixster.MainActivity.MOVIE_RATING;
import static com.example.flixster.MainActivity.MOVIE_POPULARITY;
import static com.example.flixster.MainActivity.MOVIE_RELEASE_DATE;
import static com.example.flixster.MainActivity.MOVIE_BACKDROP;
public class MoreDetailsActivity extends AppCompatActivity {

    TextView tvTitle;
    RatingBar rbRating;
    TextView tvRating;
    ImageView ivPoster;
    TextView tvOverview;
    TextView tvPopularity;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_details);

        tvTitle = findViewById(R.id.tvTitle);
        ivPoster = findViewById(R.id.ivPoster);
        tvOverview= findViewById(R.id.tvOverview);
        tvRating = findViewById(R.id.tvRating);
        rbRating = findViewById(R.id.rbRating);
        tvPopularity= findViewById(R.id.tvPopularity);



        tvTitle.setText(getIntent().getStringExtra(MOVIE_TITLE));



        String ratingS = getIntent().getStringExtra(MOVIE_RATING);
        float rating = Float.parseFloat(ratingS);
        tvRating.setText("Rating: "+ ratingS+"/10");

        rbRating.setRating(rating/2);

        tvOverview.setText("Overview: "+getIntent().getStringExtra(MOVIE_OVERVIEW));

        String popularityDate = String.format("Popularity: " + getIntent().getStringExtra(MOVIE_POPULARITY)+"\nRelease Date: "+getIntent().getStringExtra(MOVIE_RELEASE_DATE));
        tvPopularity.setText(popularityDate);



        //String imageUrl = getIntent().getStringExtra(MOVIE_POSTER);
        String imageUrl = getIntent().getStringExtra(MOVIE_BACKDROP);
        int loadImage = R.drawable.flicks_backdrop_placeholder;
        Glide.with(this).load(imageUrl).placeholder(loadImage).transform(new RoundedCornersTransformation(30, 10)).into(ivPoster);

    }

}