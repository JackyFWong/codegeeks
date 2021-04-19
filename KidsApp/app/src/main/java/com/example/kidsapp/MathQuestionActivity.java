package com.example.kidsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MathQuestionActivity extends AppCompatActivity {

    private MathDatabaseNew mMathDb;
    private List<MathQuestion> mQuestions;
    private TextView mQuestionText;
    private LinearLayout mAnswerButtons;
    private List<Button> answerButtons;
    private int questionIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_question);

        mMathDb = MathDatabaseNew.getInstance();

        mQuestions = mMathDb.getQuestions();

        mQuestionText = findViewById(R.id.question_text);
        mAnswerButtons = findViewById(R.id.answers_layout);

        questionIndex = 0;

        setQuestion(questionIndex);
        setButtons(questionIndex);
    }

    private void setQuestion(int questionId) {
        Log.d("MATH", mQuestions.get(questionId).getQuestion());
        mQuestionText.setText(mQuestions.get(questionId).getQuestion());
    }

    private void setButtons(int questionId) {
        mAnswerButtons.removeAllViews();
        for (int i = 0; i < mQuestions.get(questionId).getAnswers().size(); i++) {
            Button button = new Button(this);
            button.setText(String.valueOf(mQuestions.get(questionId).getAnswers().get(i)));
            button.setId(i);
            button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mQuestions.get(questionId).compareAnswer(v.getId())) {
                        correctAnswerSelected();
                    }
                    else {
                        wrongAnswerSelected();
                    }
                }
            });
            button.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            mAnswerButtons.addView(button);
        }
    }

    private void correctAnswerSelected() {
        FragmentManager manager = getSupportFragmentManager();
        CorrectFragment dialog = new CorrectFragment();
        dialog.show(manager, "correctDialog");
        onNextQuestion();
    }

    private void wrongAnswerSelected() {
        FragmentManager manager = getSupportFragmentManager();
        IncorrectFragment dialog = new IncorrectFragment();
        dialog.show(manager, "incorrectDialog");
    }

    public void onNextQuestion() {
        if (questionIndex >= mQuestions.size() - 1) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else {
            questionIndex++;
            setQuestion(questionIndex);
            setButtons(questionIndex);
        }
    }
}