package com.text;

import java.util.ArrayList;
import java.util.Arrays;

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

        public void printDescription() {
            System.out.println(description);
        }
    }

    public Question(int questionId, String questionText, String[] _answers,
                    String[] description, int[] correctAnswerIds) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.correctAnswerIds = Arrays.copyOf(correctAnswerIds, correctAnswerIds.length);
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
                answer.printDescription();
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
            }
        }
    }
}