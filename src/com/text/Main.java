package com.text;

import java.util.ArrayList;
import java.util.Arrays;
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
                new String[]{
                        "1",
                        "2",
                        "3"
                },
                new int[]{
                        2
        }));
        questions.add(new Question(2, "Что означает мигание зеленого сигнала светофора?",
                new String[]{
                        "Предупреждает о неисправности светофора.",
                        "Разрешает движение и информирует о том, что вскоре будет включен запрещающий сигнал",
                        "Запрещает дальнейшее движение."},
                new String[]{
                        "1",
                        "2",
                        "3"
                },
                new int[]{
                    2
        }));

        for (Question question : questions) {
            question.printQuestion();

            System.out.print("Введите один или более ответ(ов): ");
            String[] user_answers = in.nextLine().split(" ");


            int[] user_answers_int = new int[user_answers.length];
            for (int i = 0; i < user_answers.length; i++) {
                while (true) {
                    try {
                        user_answers_int[i] = Integer.parseInt(user_answers[i]);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка: введен неверный формат данных");
                        user_answers = in.nextLine().split(" ");
                    }
                }
            }

            if (Arrays.equals(user_answers_int, question.getCorrectAnswerIds())) {
                total++;
                System.out.println("Верно!");
            } else {
                System.out.println("Не верно!");
                System.out.println("Правильный ответ: " + Arrays.toString(question.getCorrectAnswerIds()));
            }
        }
        System.out.printf("Тестирование закончено с результатом: %d/%d правильных ответов\n", total, questions.size());
        System.out.printf("Общий процент верных ответов: %.2f", ((float)total / questions.size()) * 100);
    }

}
