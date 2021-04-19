package com.example.kidsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ScienceQuestionActivity extends AppCompatActivity {

    private ScienceDatabase mScienceDb;
    private List<ScienceQuestion> mQuestions;
    private TextView mQuestionText;
    private View mQuestionColor;
    private LinearLayout mAnswerButtons;
    private int questionIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_science_question);

        mScienceDb = ScienceDatabase.getInstance();

        mQuestions = mScienceDb.getQuestions();

        mQuestionText = findViewById(R.id.science_question_text);
        mQuestionColor = findViewById(R.id.science_color_box);
        mAnswerButtons = findViewById(R.id.science_answers_layout);

        mQuestionText.setText("What color is this?");
        questionIndex = 0;

        setQuestion(questionIndex);
        setButtons(questionIndex);
    }

    private void setQuestion(int questionId) {
        mQuestionColor.setBackgroundColor(Color.parseColor(mQuestions.get(questionIndex).getQuestion()));
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