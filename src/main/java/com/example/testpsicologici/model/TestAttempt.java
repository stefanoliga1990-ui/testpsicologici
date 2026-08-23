package com.example.testpsicologici.model;

import java.util.Arrays;

public class TestAttempt {

    private final int[] answers;
    private boolean completionRecorded;

    public TestAttempt(int questionCount) {
        this.answers = new int[questionCount];
    }

    public void answer(int questionIndex, int value) {
        answers[questionIndex] = value;
    }

    public int answerAt(int questionIndex) {
        return answers[questionIndex];
    }

    public boolean isComplete() {
        return Arrays.stream(answers).allMatch(answer -> answer >= 1 && answer <= 5);
    }

    public synchronized boolean markCompletionRecorded() {
        if (!isComplete() || completionRecorded) {
            return false;
        }
        completionRecorded = true;
        return true;
    }

    public int score() {
        return Arrays.stream(answers).sum();
    }
}
