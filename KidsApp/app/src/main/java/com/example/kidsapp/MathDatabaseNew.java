package com.example.kidsapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MathDatabaseNew {

    private static MathDatabaseNew mMathDb;
    private List<MathQuestion> mQuestions;

    public static MathDatabaseNew getInstance() {
        if (mMathDb == null) {
            mMathDb = new MathDatabaseNew();
        }
        return mMathDb;
    }

    private MathDatabaseNew() {
        mQuestions = new ArrayList<>();

        MathQuestion question = new MathQuestion(
            0,
            "5 + 5 = ?",
            new ArrayList<Integer>() {{
                add(12);
                add(7);
                add(10);
                add(9);
            }},
            2
        );
        addQuestion(question);

        question = new MathQuestion(
                1,
                "3 * 2 = ?",
                new ArrayList<Integer>() {{
                    add(2);
                    add(3);
                    add(6);
                    add(9);
                }},
                2
        );
        addQuestion(question);

        question = new MathQuestion(
                1,
                "5 - 2 = ?",
                new ArrayList<Integer>() {{
                    add(1);
                    add(4);
                    add(2);
                    add(3);
                }},
                3
        );
        addQuestion(question);
    }

    public void addQuestion(MathQuestion mQ) {
        if (mQuestions != null) {
            mQuestions.add(mQ);
        }
    }

    public List<MathQuestion> getQuestions() {
        return mQuestions;
    }
}
