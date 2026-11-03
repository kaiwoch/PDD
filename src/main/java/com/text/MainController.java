package com.text;

import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class MainController {
    private ToggleGroup toggleGroup;
    private Question question;
    private Database db;

    @FXML
    private Label statusLabel;

    @FXML
    private Label questionLabel;

    @FXML
    private VBox answersContainer;

    @FXML
    private Label correctLabel;

    public void startTest() {
        db = new Database();
        question = db.findQuestionById(501);

        statusLabel.setText("Тестирование началось");

        if (question == null)
            questionLabel.setText("Вопрос не найден");
        else {
            questionLabel.setText(question.getQuestionText());
            ArrayList<Question.Answer> answers = question.getAnswers();

            answersContainer.getChildren().clear();
            toggleGroup = new ToggleGroup();

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
                System.out.println(toggle.getUserData());
                long userAnswer = (long)toggle.getUserData();
                if (question.isCorrectAnswer(userAnswer)) {
                    correctLabel.setText("Верно!");
                } else
                    correctLabel.setText("Ошибка!");
            }
        } else {
            correctLabel.setText("Тест еще не запущен!");
        }
    }

    public void initialize() {
        System.out.println("Интерфейс успешно загружен");
    }
}
