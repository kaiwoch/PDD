package com.text;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    private final String url = "jdbc:postgresql://localhost:5432/pdd_app";
    private final String username = "postgres";
    //private final String password = System.getenv("DB_PASSWORD");
    private  final String password = "2084";
    private Connection connection;

    public Database() {
        try {
            connection = connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection connect() throws SQLException {
        this.connection = DriverManager.getConnection(url, username, password);
        if (connection.isValid(2)) {
            System.out.println("Успешно подключено");
            return connection;
        }
        disconnect();
        return null;
    }

    public void disconnect() throws SQLException {
        connection.close();
    }

    public ArrayList<Question.Answer> findAnswersByQuestionId(long id) {
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

    public Question findQuestionById(long id) {
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
                    String resultQuestionImagePath = result.getString("image_path");

                    ArrayList<Question.Answer> answers = findAnswersByQuestionId(id);

                    question = new Question(resultId, resultQuestionText, answers, resultQuestionCategory, resultQuestionExplanation, resultQuestionImagePath);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return question;
    }

    public ArrayList<Question> findAllQuestions() {
        ArrayList<Question> result = new ArrayList<>();

        String sql = "SELECT * FROM questions";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    long resultId = resultSet.getLong("id");
                    String resultQuestionText = resultSet.getString("question_text");
                    String resultQuestionCategory = resultSet.getString("category");
                    String resultQuestionExplanation = resultSet.getString("explanation");
                    String resultQuestionImagePath = resultSet.getString("image_path");

                    ArrayList<Question.Answer> answers = findAnswersByQuestionId(resultId);

                    Question question = new Question(resultId, resultQuestionText, answers, resultQuestionCategory, resultQuestionExplanation, resultQuestionImagePath);
                    result.add(question);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
