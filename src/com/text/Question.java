package com.text;

import java.util.ArrayList;

public class Question {
    private int question_id;
    private String question_text;

    private final ArrayList<Answer> answers;

    private int current_answer_id;

    public static class Answer {
        private int answer_id;
        private String answer_text;

        public Answer(int answer_id, String answer_text) {
            this.answer_id = answer_id;
            this.answer_text = answer_text;
        }
    }

    public Question(int question_id, String question_text, String[] array_answers, int current_answer_id) {
        this.question_id = question_id;
        this.question_text = question_text;
        this.current_answer_id = current_answer_id;
        answers = new ArrayList<Answer>();

        for (int i = 0; i < array_answers.length; i++) {
            answers.add(new Answer(i + 1, array_answers[i]));
        }
    }

    public void printQuestion() {
        System.out.println("===========================================");
        System.out.println("Вопрос номер: " + question_id);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(question_text);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
        for (Answer answer : answers) {
            System.out.println(answer.answer_id + ". " + answer.answer_text);
        }
        System.out.println("===========================================");
    }

    public int getCurrentAnswerId() {
        return current_answer_id;
    }
}
