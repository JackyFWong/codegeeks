package com.example.kidsapp;

import java.util.ArrayList;
import java.util.List;

public class ScienceDatabase {

    private static ScienceDatabase mScienceDb;
    private List<ScienceQuestion> mQuestions;

    public static ScienceDatabase getInstance() {
        if (mScienceDb == null) {
            mScienceDb = new ScienceDatabase();
        }
        return mScienceDb;
    }

    private ScienceDatabase() {
        mQuestions = new ArrayList<>();

        ScienceQuestion question = new ScienceQuestion(
                0,
                "#FF0000",
                new ArrayList<String>() {{
                    add("Red");
                    add("Blue");
                    add("Green");
                }},
                0
        );
        addQuestion(question);

        question = new ScienceQuestion(
                1,
                "#ACACAC",
                new ArrayList<String>() {{
                    add("Yellow");
                    add("Black");
                    add("Gray");
                }},
                2
        );
        addQuestion(question);

        question = new ScienceQuestion(
                2,
                "#8F00FF",
                new ArrayList<String>() {{
                    add("Purple");
                    add("Orange");
                    add("White");
                }},
                0
        );
        addQuestion(question);
    }

    public void addQuestion(ScienceQuestion mQ) {
        if (mQuestions != null) {
            mQuestions.add(mQ);
        }
    }

    public List<ScienceQuestion> getQuestions() {
        return mQuestions;
    }
}
