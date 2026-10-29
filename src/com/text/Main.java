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
                        "Это не вынужденная остановка. По ПДД водитель обязан уступить дорогу пешеходу — это остановка по требованию Правил, то есть заранее ожидаемое, предписанное действие. Вынужденная остановка — это прекращение движения не по желанию водителя и не по прямому предписанию ПДД (п. 1.2 Основных положений).",
                        "Это классический пример вынужденной остановки: движение прекращено из-за технической неисправности (поломка, отказ систем), то есть по причине, не зависящей от воли водителя и не связанной с выполнением ПДД. Именно такое определение даётся в п. 1.2 ПДД: вынужденная остановка — прекращение движения из-за технической неисправности, опасности груза, состояния водителя/пассажира, препятствия на дороге и т.п.",
                        "Неверно, потому что первый случай — это остановка по требованию ПДД (уступить дорогу), а не вынужденная. Значит, объединять их нельзя."
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
                        "Мигающий зелёный — это штатный режим работы светофора, а не признак неисправности. О неисправности светофора водителя информирует, например, жёлтый мигающий сигнал (в ряде случаев) или сотрудник ГИБДД / временные знаки, но не мигающий зелёный.",
                        "Согласно п. 6.2 ПДД, мигание зелёного сигнала разрешает движение и информирует водителя о том, что время действия разрешающего сигнала истекает и скоро включится жёлтый (затем красный) — то есть запрещающий сигнал. Водитель должен быть готов завершить манёвр / остановиться, не прибегая к экстренному торможению.",
                        "Мигающий зелёный не запрещает движение — он разрешает его, как и постоянный зелёный. Запрещающими являются жёлтый и красный сигналы (а также их сочетания). Поэтому данный вариант неверен."
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

                    if (question.isCorrectAnswer(userAnswersInt)) {
                        total++;
                        System.out.println("Верно!");
                    } else {
                        System.out.println("Неверно!");
                        System.out.println("Правильный ответ: "
                                + Arrays.toString(question.getCorrectAnswerIds()));

                        question.printCorrectAnswersDescriptions();
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
