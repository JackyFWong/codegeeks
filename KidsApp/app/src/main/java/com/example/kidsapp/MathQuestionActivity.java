package com.example.kidsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import java.util.Map;

public class MathQuestionActivity extends AppCompatActivity {

    private MathDatabase mMathDb;
    private Map<Integer, MathQuestion> mQuestions;
    private TextView mQuestionText;
    private GridView mAnswerButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_question);

        // get all questions from math database
        mMathDb = MathDatabase = MathDatabase.getInstance();
        mQuestions = mMathDb.getQuestions();

        mQuestionText = findViewById(R.id.question_text);


    }
}