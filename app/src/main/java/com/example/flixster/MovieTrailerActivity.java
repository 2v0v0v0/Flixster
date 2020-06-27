package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


import static com.example.flixster.MoreDetailsActivity.MOVIE_NAME;
import static com.example.flixster.MoreDetailsActivity.VID_KEY;
import static com.example.flixster.MoreDetailsActivity.VID_NAME;

public class MovieTrailerActivity extends YouTubeBaseActivity {
    TextView tvTitle ;
    TextView tvVideoName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_trailer);
        //Movie's Title
        tvTitle = (TextView)findViewById(R.id.tvTitle);
        tvTitle.setText(getIntent().getStringExtra(MOVIE_NAME));

        //
        tvVideoName = (TextView)findViewById(R.id.tvVideoName);
        tvVideoName.setText(getIntent().getStringExtra(VID_NAME));

        // Load movie ID
        final String videoId = getIntent().getStringExtra(VID_KEY);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            /*View tvVideoNameGone = findViewById(R.id.tvVideoName);
            tvVideoNameGone.setVisibility(View.GONE);
            View tvTitleGone = findViewById(R.id.tvTitle);
            tvTitleGone.setVisibility(View.GONE);
            View buttonGone = findViewById(R.id.button);
            buttonGone.setVisibility(View.GONE);
            View barGone = findViewById(R.id.bar);
            barGone.setVisibility(View.GONE);*/
        }

        // resolve the player view from the layout
        YouTubePlayerView playerView = (YouTubePlayerView) findViewById(R.id.player);

        // initialize with API key stored in secrets.xml
        String apiKey = getString(R.string.youtube_api_key);
        playerView.initialize(getString(R.string.youtube_api_key), new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                YouTubePlayer youTubePlayer, boolean b) {
                // do any work here to cue video, play video, etc.
                youTubePlayer.cueVideo(videoId);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                YouTubeInitializationResult youTubeInitializationResult) {
                // log the error
                Log.e("MovieTrailerActivity", "Error initializing YouTube player");
            }
        });
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){

            /*View tvVideoNameGone = findViewById(R.id.tvVideoName);
            tvVideoNameGone.setVisibility(View.GONE);
            View tvTitleGone = findViewById(R.id.tvTitle);
            tvTitleGone.setVisibility(View.GONE);
            View buttonGone = findViewById(R.id.button);
            buttonGone.setVisibility(View.GONE);
            View barGone = findViewById(R.id.bar);
            barGone.setVisibility(View.GONE);*/
        }
    }
    public void goBack (View v) {
        Intent i = new Intent(MovieTrailerActivity.this, MainActivity.class);
        Toast.makeText(getApplicationContext(),"Main", Toast.LENGTH_SHORT).show();
        startActivity(i);
    }
}