package com.example.kidsapp;

import java.util.ArrayList;
import java.util.List;

public class ScienceQuestion {
    private int id;
    private String question;
    private List<String> answers;
    private int correctAnswerIndex;

    public ScienceQuestion() {
        id = -1;
        question = "";
        answers = new ArrayList<String>();
        correctAnswerIndex = -1;
    }

    public ScienceQuestion(int i, String q, List<String> a, int c) {
        id = i;
        question = q;
        answers = a;
        correctAnswerIndex = c;
    }

    public int getId() {
        return id;
    }

    public void setId(int i) {
        id = i;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String q) {
        question = q;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<String> a) {
        this.answers = a;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public void setCorrectAnswerIndex(int i) {
        correctAnswerIndex = i;
    }

    public boolean compareAnswer(int userAnswer) {
        return userAnswer == correctAnswerIndex;
    }
}
