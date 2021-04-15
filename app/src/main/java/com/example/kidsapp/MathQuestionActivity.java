package com.example.kidsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MathQuestionActivity extends AppCompatActivity {

    private MathDatabase mMathDb;
    private Map<Integer, MathQuestion> mQuestions;
    private TextView mQuestionText;
    private LinearLayout mAnswerButtons;
    private List<Button> answerButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_question);

        // get all questions from math database
        mMathDb = new MathDatabase(this);
        // needs to be updated to dynamically create all MathQuestions
        List<MathQuestion> questions = mMathDb.getQuestions();
        for (int i = 0; i < questions.size(); i++) {
            mQuestions.put(i, questions.get(i));
        }

        mQuestionText = findViewById(R.id.question_text);
        mAnswerButtons = findViewById(R.id.answers_layout);
        // answerButtons = new ArrayList<Button>();

        setQuestion(0);
        setButtons(0);
    }

    private void setQuestion(int questionId) {
        Log.d("MATH", mQuestions.get(questionId).getQuestion());
        mQuestionText.setText(mQuestions.get(questionId).getQuestion());
    }

    private void setButtons(int questionId) {
        for (int i = 0; i < mQuestions.size(); i++) {
            Button button = new Button(this);
            button.setText(mQuestions.get(questionId).getAnswers().get(i));
            button.setId(i);
            button.setOnClickListener(new View.OnClickListener() {
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
                    LinearLayout.LayoutParams.MATCH_PARENT, 0, 1
            ));
            mAnswerButtons.addView(button);
        }
    }

    private void correctAnswerSelected() {
        // pop up congratulating user
    }

    private void wrongAnswerSelected() {
        // pop up apologizing to user
    }
}