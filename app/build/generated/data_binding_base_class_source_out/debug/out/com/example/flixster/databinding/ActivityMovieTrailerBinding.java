// Generated by view binder compiler. Do not edit!
package com.example.flixster.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.example.flixster.R;
import com.google.android.youtube.player.YouTubePlayerView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMovieTrailerBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final TextView bar;

  @NonNull
  public final Button button;

  @NonNull
  public final YouTubePlayerView player;

  @NonNull
  public final TextView tvTitle;

  @NonNull
  public final TextView tvVideoName;

  private ActivityMovieTrailerBinding(@NonNull RelativeLayout rootView, @NonNull TextView bar,
      @NonNull Button button, @NonNull YouTubePlayerView player, @NonNull TextView tvTitle,
      @NonNull TextView tvVideoName) {
    this.rootView = rootView;
    this.bar = bar;
    this.button = button;
    this.player = player;
    this.tvTitle = tvTitle;
    this.tvVideoName = tvVideoName;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMovieTrailerBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMovieTrailerBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_movie_trailer, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMovieTrailerBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.bar;
      TextView bar = rootView.findViewById(id);
      if (bar == null) {
        break missingId;
      }

      id = R.id.button;
      Button button = rootView.findViewById(id);
      if (button == null) {
        break missingId;
      }

      id = R.id.player;
      YouTubePlayerView player = rootView.findViewById(id);
      if (player == null) {
        break missingId;
      }

      id = R.id.tvTitle;
      TextView tvTitle = rootView.findViewById(id);
      if (tvTitle == null) {
        break missingId;
      }

      id = R.id.tvVideoName;
      TextView tvVideoName = rootView.findViewById(id);
      if (tvVideoName == null) {
        break missingId;
      }

      return new ActivityMovieTrailerBinding((RelativeLayout) rootView, bar, button, player,
          tvTitle, tvVideoName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
