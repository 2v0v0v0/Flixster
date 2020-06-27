package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixster.adapters.MovieAdapter;
import com.example.flixster.databinding.ActivityMainBinding;
import com.example.flixster.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;



public class MainActivity extends AppCompatActivity {


    public static final String NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=";
    public static final String TAG = "MainActivity";



    public static final String MOVIE_TITLE = "Movie's title";
    public static final String MOVIE_OVERVIEW = "Movie's overview";
    public static final String MOVIE_RATING = "Movie's rating";
    public static final String MOVIE_RELEASE_DATE = "Movie's release date";
    public static final String MOVIE_POPULARITY = "Movie's popularity";
    public static final String MOVIE_BACKDROP = "Movie's poster";
    public static final String MOVIE_ID = "Movie's id";

    MovieAdapter movieAdapter;

    List<Movie> movies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setTitle(R.string.main_activity);
        //RecyclerView rvMovies = findViewById(R.id.rvMovies);

        movies = new ArrayList<>();

        MovieAdapter.OnClickListener onclickListener = new MovieAdapter.OnClickListener(){
            @Override
            public void onMovieClicked(int position) {
                //create new activity
                Intent i = new Intent(MainActivity.this, MoreDetailsActivity.class);
                //pass data
                i.putExtra(MOVIE_TITLE, movies.get(position).getTitle());
                i.putExtra(MOVIE_OVERVIEW, movies.get(position).getOverview());
                i.putExtra(MOVIE_RATING, movies.get(position).getRating());
                i.putExtra(MOVIE_POPULARITY, movies.get(position).getPopularity());
                i.putExtra(MOVIE_RELEASE_DATE, movies.get(position).getReleaseDate());
                i.putExtra(MOVIE_BACKDROP, movies.get(position).getBackdropPath());
                i.putExtra(MOVIE_ID, movies.get(position).getId());
                //display the activity
                Toast.makeText(getApplicationContext(),"More Details", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        };




        //Create an adapter
        movieAdapter = new MovieAdapter(this, movies, onclickListener);

        //Set the adapter on the recycler view
        binding.rvMovies.setAdapter(movieAdapter);

        //Set a layout manager on the recycler view
        binding.rvMovies.setLayoutManager(new LinearLayoutManager(this));

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(NOW_PLAYING_URL+getString(R.string.tmdb_api_key), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess");
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results = jsonObject.getJSONArray("results");
                    Log.i(TAG, "Results: " + results.toString());
                    movies.addAll( Movie.fromJsonArray(results));
                    movieAdapter.notifyDataSetChanged();
                    Log.i(TAG, "Movies: " + movies.size());
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