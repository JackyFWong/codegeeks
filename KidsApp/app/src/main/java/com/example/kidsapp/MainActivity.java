package com.example.kidsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    // private Button mathButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onEnglishReadingClick(View view) {
        Intent intent = new Intent(this, EnglishReadingQuestionActivity.class);
        startActivity(intent);
    }

    public void onEnglishSoundClick(View view) {
        Intent intent = new Intent(this, EnglishSoundQuestionActivity.class);
        startActivity(intent);
    }

    public void onMathClick(View view) {
        Intent intent = new Intent(this, MathQuestionActivity.class);
        startActivity(intent);
    }

    public void onScienceClick(View view) {
        Intent intent = new Intent(this, ScienceQuestionActivity.class);
        startActivity(intent);
    }

    public void onStoryClick(View view) {
        Intent intent = new Intent(this, StoryActivity.class);
        startActivity(intent);
    }

    public void onVideoClick(View view) {
        Intent intent = new Intent(this, VideoActivity.class);
        startActivity(intent);
    }

    public void onProfileClick(View view) {
        Intent intent = new Intent(this, VideoActivity.class);
        startActivity(intent);
    }
}