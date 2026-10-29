package com.text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Question {
    private final int questionId;
    private final String questionText;

    private final ArrayList<Answer> answers;

    private final int[] correctAnswerIds;

    public static class Answer {
        private final int answerId;
        private final String answerText;
        private final String description;

        public Answer(int answerId, String answerText, String description) {
            this.answerId = answerId;
            this.answerText = answerText;
            this.description = description;
        }
    }

    public Question(int questionId, String questionText, String[] _answers, String[] description, int[] correctAnswerIds) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.correctAnswerIds = correctAnswerIds;
        answers = new ArrayList<Answer>();

        for (int i = 0; i < _answers.length; i++) {
            answers.add(new Answer(i + 1, _answers[i], description[i]));
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

    public int[] getCorrectAnswerIds() {
        return correctAnswerIds;
    }

    public int getAnswerLength() {
        return answers.size();
    }
}
