package com.text;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        //SQL CONNECTION
        String username = "postgres";
        String password = System.getenv("DB_PASSWORD");

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pdd_app", username, password)) {
            int total = 0;
            Scanner in = new Scanner(System.in);

            ArrayList<Question> questions = new ArrayList<Question>();

            Question question1 = findQuestionById(connection, 501);
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

    public static Question findQuestionById(Connection connection, long id) {
        Question question = null;

        String sql = "SELECT * FROM questions WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    long resultId = result.getLong("id");
                    String resultQuestionText = result.getString("question_text");
                    String resultQuestionCategory = result.getString("category");
                    String resultQuestionExplanation = result.getString("explanation");

                    ArrayList<Question.Answer> answers = findAnswersByQuestionId(connection, id);

                    question = new Question(resultId, resultQuestionText, answers, resultQuestionCategory, resultQuestionExplanation);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return question;
    }

    //TODO: UPDATE RETURN VALUES
    public static ArrayList<String> findAllQuestions(Connection connection) {
        ArrayList<String> result = new ArrayList<>();

        String sql = "SELECT * FROM questions";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(resultSet.getString("question_text"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static ArrayList<Question.Answer> findAnswersByQuestionId(Connection connection, long id) {
        ArrayList<Question.Answer> answers = new ArrayList<>();

        String sql = "select * from answers where question_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    long answerId = resultSet.getLong("id");
                    String answerText = resultSet.getString("answer_text");
                    boolean isCorrect = resultSet.getBoolean("is_correct");

                    Question.Answer answer = new Question.Answer(answerId, answerText, isCorrect);
                    answers.add(answer);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return answers;
    }
}
