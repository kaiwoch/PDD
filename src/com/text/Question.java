package com.text;

import java.util.ArrayList;

public class Question {
    private int questionId;
    private String questionText;

    private final ArrayList<Answer> answers;

    private int correctAnswerId;

    public static class Answer {
        private int answerId;
        private String answerText;

        public Answer(int answerId, String answerText) {
            this.answerId = answerId;
            this.answerText = answerText;
        }
    }

    public Question(int questionId, String questionText, String[] arrayAnswers, int correctAnswerId) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.correctAnswerId = correctAnswerId;
        answers = new ArrayList<Answer>();

        for (int i = 0; i < arrayAnswers.length; i++) {
            answers.add(new Answer(i + 1, arrayAnswers[i]));
        }
    }

    public void printQuestion() {
        System.out.println("===========================================");
        System.out.println("Вопрос номер: " + questionId);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(questionText);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
        for (Answer answer : answers) {
            System.out.println(answer.answerId + ". " + answer.answerText);
        }
        System.out.println("===========================================");
    }

    public int getCorrectAnswerId() {
        return correctAnswerId;
    }
}
