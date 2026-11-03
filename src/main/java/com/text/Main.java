package com.text;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Database db = new Database();

        int total = 0;
        Scanner in = new Scanner(System.in);

        ArrayList<Question> questions = new ArrayList<Question>();

        Question question1 = db.findQuestionById(501);
        questions.add(question1);

        for (Question question : questions) {
            question.printQuestion();

            long userAnswer = readAnswer(question, in);

            if (question.isCorrectAnswer(userAnswer)) {
                total++;
                System.out.println("Верно!");
            } else {
                System.out.println("Неверно!");

                question.printDescription();
            }

        }
        System.out.printf("Тестирование закончено с результатом: %d/%d правильных ответов\n", total, questions.size());
        System.out.printf("Общий процент верных ответов: %.2f", ((float)total / questions.size()) * 100);
    }

    public static boolean isExists(int value, long[] array, int length) {
        for (int i = 0; i < length; i++) {
            if (array[i] == value) {
                return true;
            }
        }
        return false;
    }

    public static long readAnswer(Question question, Scanner in) {
        while (true) {
            System.out.print("Введите ответ: ");
            try {
                return in.nextLong();
            } catch (InputMismatchException e) {
                System.out.println("Неверный формат");
                in.next(); // съедаем некорректный токен, иначе бесконечный цикл
            }
        }
    }
}
