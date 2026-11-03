package com.text;

import java.util.ArrayList;

public class Question {
    private final long questionId;
    private final String questionText;
    private final ArrayList<Answer> answers;
    private final String category;
    private final String explanation;

    public static class Answer {
        private final long answerId;
        private final String answerText;
        private final boolean isCorrect;

        public Answer(long answerId, String answerText, boolean isCorrect) {
            this.answerId = answerId;
            this.answerText = answerText;
            this.isCorrect = isCorrect;
        }

        public long getAnswerId() {
            return answerId;
        }

        public String getAnswerText() {
            return answerText;
        }

        public boolean isCorrect() {
            return isCorrect;
        }
    }

    public Question(long questionId, String questionText, ArrayList<Answer> answers,
                    String category, String explanation) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.category = category;
        this.answers = new ArrayList<>(answers);
        this.explanation = explanation;

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

    public int getAnswerLength() {
        return answers.size();
    }

    public Answer getAnswer(long id) {
        for (Answer answer : answers) {
            if (answer.answerId == id) {
                return answer;
            }
        }
        return null;
    }

    public boolean isCorrectAnswer(long userAnswer) {
        Answer answer = getAnswer(userAnswer);

        if (answer == null) {
            System.out.println("Ответ не существует");
            return false;
        }

        return answer.isCorrect;
    }

    public void printDescription() {
        System.out.println(explanation);
    }
}