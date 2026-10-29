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

            while (true) {
                System.out.print("Введите один или более ответ(ов): ");
                String input = in.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("Ошибка: введите хотя бы один ответ.");
                    continue;
                }

                String[] userAnswers = input.split("\\s+");

                try {
                    int[] userAnswersInt = new int[userAnswers.length];
                    for (int i = 0; i < userAnswers.length; i++) {
                        if (!isExists(Integer.parseInt(userAnswers[i]), userAnswersInt, i) && Integer.parseInt(userAnswers[i]) >= 1 && Integer.parseInt(userAnswers[i]) <= question.getAnswerLength())
                            userAnswersInt[i] = Integer.parseInt(userAnswers[i]);
                        else
                            throw new NumberFormatException("Ошибка: присутствует дубликат");
                    }

                    Arrays.sort(userAnswersInt);
                    Arrays.sort(question.getCorrectAnswerIds());

                    if (Arrays.equals(userAnswersInt, question.getCorrectAnswerIds())) {
                        total++;
                        System.out.println("Верно!");
                    } else {
                        System.out.println("Не верно!");
                        System.out.println("Правильный ответ: " + Arrays.toString(question.getCorrectAnswerIds()));
                    }
                    break;

                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: вводите только целые числа.");
                }
            }
        }
        System.out.printf("Тестирование закончено с результатом: %d/%d правильных ответов\n", total, questions.size());
        System.out.printf("Общий процент верных ответов: %.2f", ((float)total / questions.size()) * 100);
    }

    public static boolean isExists(int value, int[] array, int length) {
        for (int i = 0; i < length; i++) {
            if (array[i] == value) {
                return true;
            }
        }
        return false;
    }

}
