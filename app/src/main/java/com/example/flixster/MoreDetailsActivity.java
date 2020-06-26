package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixster.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import okhttp3.Headers;

import static com.example.flixster.MainActivity.API_KEY;
import static com.example.flixster.MainActivity.MOVIE_ID;
import static com.example.flixster.MainActivity.MOVIE_TITLE;
import static com.example.flixster.MainActivity.MOVIE_OVERVIEW;
import static com.example.flixster.MainActivity.MOVIE_RATING;
import static com.example.flixster.MainActivity.MOVIE_POPULARITY;
import static com.example.flixster.MainActivity.MOVIE_RELEASE_DATE;
import static com.example.flixster.MainActivity.MOVIE_BACKDROP;

public class MoreDetailsActivity extends AppCompatActivity {
    public static final String TAG = "MoreDetailsActivity";

    private TextView tvTitle;
    private RatingBar rbRating;
    private TextView tvRating;
    private ImageView ivPoster;
    private TextView tvOverview;
    private TextView tvPopularity;


    List<String> trailers;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_details);
        String id = getIntent().getStringExtra(MOVIE_ID);
        String trailerUrl = String.format("https://api.themoviedb.org/3/movie/%s/trailers?api_key=%s", id, API_KEY);
        //Variables
        {

        tvTitle = findViewById(R.id.tvTitle);
        ivPoster = findViewById(R.id.ivPoster);
        tvOverview= findViewById(R.id.tvOverview);
        tvRating = findViewById(R.id.tvRating);
        rbRating = findViewById(R.id.rbRating);
        tvPopularity= findViewById(R.id.tvPopularity);}

        //View Binding
        {
            tvTitle.setText(getIntent().getStringExtra(MOVIE_TITLE));


            String ratingS = getIntent().getStringExtra(MOVIE_RATING);
            float rating = Float.parseFloat(ratingS);
            tvRating.setText("Rating: " + ratingS + "/10");

            rbRating.setRating(rating / 2);

            tvOverview.setText("Overview: " + getIntent().getStringExtra(MOVIE_OVERVIEW));

            String popularityDate = String.format("Popularity: " + getIntent().getStringExtra(MOVIE_POPULARITY) + "\nRelease Date: " + getIntent().getStringExtra(MOVIE_RELEASE_DATE));
            tvPopularity.setText(popularityDate);
        }

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(trailerUrl, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess");
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results = jsonObject.getJSONArray("youtube");
                    //results.getJSONObject();
                    /*Log.i(TAG, "Results: " + results.toString());
                    trailers.addAll(jsonObject.getString("source"));
                    Log.i(TAG, "Trailers: " + trailers.size());
                    //String youtubeSource = jsonObject.getString("source");*/
                } catch (JSONException e) {
                    Log.e(TAG, "Hit json exception", e);
                }

            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d(TAG, "onFaliure");
            }
        });

        //String imageUrl = getIntent().getStringExtra(MOVIE_POSTER);
        String imageUrl = getIntent().getStringExtra(MOVIE_BACKDROP);
        int loadImage = R.drawable.flicks_backdrop_placeholder;
        Glide.with(this).load(imageUrl).placeholder(loadImage)
                .transform(new RoundedCornersTransformation(30, 10)).into(ivPoster);

    }

}