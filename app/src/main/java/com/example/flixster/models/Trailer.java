package com.example.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Trailer {
    private String key;
    private String name;


    public Trailer(JSONObject jsonObject) throws JSONException {
        key = jsonObject.getString("key");
        name = jsonObject.getString("name");
    }

    public static List<Trailer> fromJsonArray(JSONArray trailerJsonArray) throws JSONException {
        List<Trailer> trailers = new ArrayList<>();
        for (int i = 0; i < trailerJsonArray.length(); i++) {
            trailers.add(new Trailer(trailerJsonArray.getJSONObject(i)));
        }
        return trailers;
    }

    public String getSource() {
        return key;
    }

    public String getName() {
        return name;
    }
}