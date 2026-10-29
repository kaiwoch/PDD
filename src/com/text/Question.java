package com.text;

import java.util.ArrayList;
import java.util.Arrays;

public class Question {
    private final int questionId;
    private final String questionText;
    private final ArrayList<Answer> answers;
    private final int[] correctAnswerIds;
    private final String description;

    public static class Answer {
        private final int answerId;
        private final String answerText;

        public Answer(int answerId, String answerText) {
            this.answerId = answerId;
            this.answerText = answerText;
        }
    }

    public Question(int questionId, String questionText, String[] _answers,
                    String description, int[] correctAnswerIds) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.description = description;
        this.correctAnswerIds = Arrays.copyOf(correctAnswerIds, correctAnswerIds.length);
        answers = new ArrayList<Answer>();

        for (int i = 0; i < _answers.length; i++) {
            answers.add(new Answer(i + 1, _answers[i]));
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
        return Arrays.copyOf(correctAnswerIds, correctAnswerIds.length);
    }

    public int getAnswerLength() {
        return answers.size();
    }

    public Answer getAnswer(int id) {
        for (Answer answer : answers) {
            if (answer.answerId == id) {
                return answer;
            }
        }
        return null;
    }

    public boolean isCorrectAnswer(int[] userAnswer) {
        int[] sortedUserAnswers = Arrays.copyOf(userAnswer, userAnswer.length);
        int[] correctAnswers = getCorrectAnswerIds();

        Arrays.sort(sortedUserAnswers);
        Arrays.sort(correctAnswers);

        return Arrays.equals(correctAnswers, sortedUserAnswers);
    }

    public void printCorrectAnswersDescriptions() {
        for (int answerId : correctAnswerIds) {
            Answer answer = getAnswer(answerId);

            if (answer != null) {
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
                System.out.printf("Пояснение для правильного ответа: %d%n", answerId);
                System.out.println(description);
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
            }
        }
    }
}