package com.text;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class MainController {
    private ToggleGroup toggleGroup;
    private Question question;
    private Database db;
    private long currentQuestionId = 500;
    private int totalQuestions;
    private int correctAnswers;

    @FXML
    private Label statusLabel;

    @FXML
    private Label questionLabel;

    @FXML
    private VBox answersContainer;

    @FXML
    private Label correctLabel;

    @FXML
    private ImageView questionImageView;

    @FXML
    private Button checkAnswerButon;

    @FXML
    private Button startButton;

    @FXML
    private Label descriptionLabel;

    public void startTest() throws IOException {
        db = new Database();
        statusLabel.setText("Тестирование началось");
        startButton.setVisible(false);

        loadQuestion(currentQuestionId);
    }

    public void loadQuestion(long id) {
        checkAnswerButon.setVisible(true);
        correctLabel.setText("");
        questionImageView.setImage(null);
        question = db.findQuestionById(id);
        descriptionLabel.setText("");

        if (question == null) {
            answersContainer.getChildren().clear();
            checkAnswerButon.setVisible(false);
            toggleGroup = null;

            float result = 0;
            if (totalQuestions > 0)
                result = ((float) correctAnswers / totalQuestions) * 100;

            if (totalQuestions == 0) {
                questionLabel.setText("Тест завершен. Нет отвеченных вопросов.");
                return;
            }

            if (result > 80) {
                questionLabel.setText("Тестирование завершено! Процент верных ответов: " + result + ". Ваша итоговая оценка: 5");
            } else if (result > 60) {
                questionLabel.setText("Тестирование завершено! Процент верных ответов: " + result + ". Ваша итоговая оценка: 4");
            } else if (result > 40) {
                questionLabel.setText("Тестирование завершено! Процент верных ответов: " + result + ". Ваша итоговая оценка: 3");
            } else {
                questionLabel.setText("Тестирование завершено! Процент верных ответов: " + result + ". Ваша итоговая оценка: 2");
            }
        }
        else {
            questionLabel.setText(question.getQuestionText());
            ArrayList<Question.Answer> answers = question.getAnswers();

            answersContainer.getChildren().clear();
            toggleGroup = new ToggleGroup();

            if (question.getImagePath() != null && !question.getImagePath().isEmpty()) {
                Path path = Path.of(question.getImagePath()).toAbsolutePath();
                Image image = new Image(path.toUri().toString());
                questionImageView.setImage(image);
            }

            for (Question.Answer answer : answers) {
                RadioButton radioButton = new RadioButton(answer.getAnswerText());
                radioButton.setToggleGroup(toggleGroup);
                radioButton.setUserData(answer.getAnswerId());
                answersContainer.getChildren().add(radioButton);
            }
        }
    }

    public void checkAnswer() {
        if (toggleGroup != null) {
            Toggle toggle = toggleGroup.getSelectedToggle();
            if (toggle != null) {
                totalQuestions++;
                checkAnswerButon.setVisible(false);
                System.out.println(toggle.getUserData());
                long userAnswer = (long)toggle.getUserData();
                if (question.isCorrectAnswer(userAnswer)) {
                    correctLabel.setText("Верно!");
                    correctAnswers++;
                } else {
                    correctLabel.setText("Ошибка!");
                }
                descriptionLabel.setText(question.getDescription());
                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(event -> {
                    nextQuestion();
                });
                pause.play();
                statusLabel.setText("Отвечено: " + totalQuestions + " | Правильно: " + correctAnswers + " | Ошибок: " + (totalQuestions - correctAnswers));
            } else {
                correctLabel.setText("Выберите ответ!");
            }
        } else {
            correctLabel.setText("Тест еще не запущен!");
        }
    }

    public void nextQuestion() {
        currentQuestionId++;
        loadQuestion(currentQuestionId);
    }

    public void initialize() {
        System.out.println("Интерфейс успешно загружен");
    }
}
