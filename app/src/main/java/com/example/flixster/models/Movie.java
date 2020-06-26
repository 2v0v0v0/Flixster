package com.example.flixster.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;

public class Movie {
    String backdropPath;
    String posterPath;
    String title;
    String overview;
    String rating;
    String popularity;
    String releaseDate;


    public Movie(JSONObject jsonObject) throws JSONException {
        backdropPath = jsonObject.getString("backdrop_path");
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        rating = jsonObject.getString("vote_average");
        popularity = jsonObject.getString("popularity");
        releaseDate = jsonObject.getString("release_date");


    }

    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for (int j = 0; j < movieJsonArray.length(); j++) {
            movies.add(new Movie(movieJsonArray.getJSONObject(j)));
        }
        return movies;
    }

    public String getBackdropPath(){
        return String.format("https://image.tmdb.org/t/p/w780/%s", backdropPath);
    }
    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getRating() {
        return rating;
    }

    public String getPopularity() {
        return popularity;
    }

    public String getReleaseDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM dd, yyyy");
        String formattedReleaseDate ="";

        try{
            formattedReleaseDate = simpleDateFormat.format(new SimpleDateFormat("yyyy-mm-dd").parse(releaseDate));
        }catch (ParseException e){
            Log.e("Movie.java", "formattedReleaseDate is null");
        }

        return formattedReleaseDate;
    }
}
