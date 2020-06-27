package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.bumptech.glide.Glide;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixster.databinding.ActivityMoreDetailsBinding;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import okhttp3.Headers;



import static com.example.flixster.MainActivity.MOVIE_ID;
import static com.example.flixster.MainActivity.MOVIE_TITLE;
import static com.example.flixster.MainActivity.MOVIE_OVERVIEW;
import static com.example.flixster.MainActivity.MOVIE_RATING;
import static com.example.flixster.MainActivity.MOVIE_POPULARITY;
import static com.example.flixster.MainActivity.MOVIE_RELEASE_DATE;
import static com.example.flixster.MainActivity.MOVIE_BACKDROP;



public class MoreDetailsActivity extends AppCompatActivity {
    public static final String TAG = "MoreDetailsActivity";
    public static final String VID_NAME = "Viedo's name";
    public static final String VID_KEY = "Video's key";
    public static final String MOVIE_NAME= "Movie's name";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMoreDetailsBinding binding = ActivityMoreDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);



        String id = getIntent().getStringExtra(MOVIE_ID);



        //View Binding

        binding.tvTitle.setText(getIntent().getStringExtra(MOVIE_TITLE));


        String ratingS = getIntent().getStringExtra(MOVIE_RATING);
        float rating = Float.parseFloat(ratingS);
        binding.tvRating.setText("Rating: " + ratingS + "/10");

        binding.rbRating.setRating(rating / 2);

        binding.tvOverview.setText("Overview: " + getIntent().getStringExtra(MOVIE_OVERVIEW));

        String popularityDate = String.format("Popularity: " + getIntent().getStringExtra(MOVIE_POPULARITY) + "\nRelease Date: " + getIntent().getStringExtra(MOVIE_RELEASE_DATE));
        binding.tvPopularity.setText(popularityDate);



        String imageUrl = getIntent().getStringExtra(MOVIE_BACKDROP);
        int loadImage = R.drawable.flicks_backdrop_placeholder;
        Glide.with(this).load(imageUrl).placeholder(loadImage)
                .transform(new RoundedCornersTransformation(30, 10)).into(binding.ivPoster);
        final String movieTitle = getIntent().getStringExtra(MOVIE_TITLE);

        String movieVideosURL = String.format("https://api.themoviedb.org/3/movie/%s/videos?api_key=%s&language=en-US",
                getIntent().getStringExtra(MOVIE_ID),getString(R.string.tmdb_api_key));
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(movieVideosURL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess");
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results = jsonObject.getJSONArray("results");
                    int i = 0;
                    JSONObject trailer = results.getJSONObject(i);
                    String key = trailer.getString("key");
                    int arrayLength = results.length();
                    while (key==null&&i<arrayLength){
                        i++;
                        trailer = results.getJSONObject(i);
                        key = trailer.getString("key");
                    }
                    final String vidName = trailer.getString("name");
                    final String workingKey = key;
                    //If movie's doesn't have trailer
                    if(key!= null){
                        binding.ivPoster.setOnClickListener(new View.OnClickListener(){
                            public void onClick(View v) {
                                //create new activity
                                Intent i = new Intent(MoreDetailsActivity.this, MovieTrailerActivity.class);
                                //pass data
                                i.putExtra(VID_NAME,vidName );
                                i.putExtra(VID_KEY,workingKey);
                                i.putExtra(MOVIE_NAME, movieTitle);
                                //display the activity
                                Toast.makeText(getApplicationContext(),"Movie Trailer", Toast.LENGTH_SHORT).show();
                                startActivity(i);
                                Log.v(TAG, " click");
                            }
                        });
                    }

                    Log.i(TAG, "Results: " + results.length());

                } catch (JSONException e) {
                    Log.e(TAG, "Hit json exception", e);
                }

            }


            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d(TAG, "onFaliure");
            }
        });




    }

}