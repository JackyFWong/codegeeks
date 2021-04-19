package com.example.kidsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
    }

    public void pig_player(View view) {
        VideoView videoView = findViewById(R.id.video_view);
        videoView.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.pigs);
        MediaController mediaController=new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

    }

    public void lion_player(View view) {
        VideoView videoView = (VideoView)findViewById(R.id.video_view);
        videoView.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.lion);
        MediaController mediaController=new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
    }
}