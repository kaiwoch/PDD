package com.text;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int total = 0;
        Scanner in = new Scanner(System.in);

        ArrayList<Question> questions = new ArrayList<Question>();

        questions.add(new Question(1, "В каком случае водитель совершит вынужденную остановку?",
                new String[]{
                        "Остановившись непосредственно перед пешеходным переходом, чтобы уступить дорогу пешеходу.",
                        "Остановившись на проезжей части из-за технической неисправности транспортного средства.",
                        "В обоих перечисленных случаях."},
                2));
        questions.add(new Question(2, "Что означает мигание зеленого сигнала светофора?",
                new String[]{
                        "Предупреждает о неисправности светофора.",
                        "Разрешает движение и информирует о том, что вскоре будет включен запрещающий сигнал",
                        "Запрещает дальнейшее движение."},
                2));

        for (Question question : questions) {
            question.printQuestion();

            int user_answer = -1;

            System.out.print("Введи правильный ответ -> ");
            while (in.hasNext()) {
                if (in.hasNextInt()) {
                    user_answer = in.nextInt();
                    if (user_answer <= question.getAnswerLength() && user_answer >= 1)
                        break;
                    else
                        System.out.println("Ошибка: ответ вне допустимого диапазона!");
                } else {
                    System.out.println("Ошибка: неправильный ввод!");

                    in.next();
                }
            }

            if (user_answer == question.getCorrectAnswerId()) {
                total++;
                System.out.println("Верно!");
            } else {
                System.out.println("Не верно!");
                System.out.println("Правильный ответ: " + question.getCorrectAnswerId());
            }
        }
        System.out.printf("Тестирование закончено с результатом: %d/%d правильных ответов\n", total, questions.size());
        System.out.printf("Общий процент верных ответов: %.2f", ((float)total / questions.size()) * 100);
    }

}
