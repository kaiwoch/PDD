package com.text;

import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class MainController {
    @FXML
    private Label statusLabel;

    @FXML
    private Label questionLabel;

    @FXML
    private VBox answersContainer;

    public void startTest() {
        Database db = new Database();
        Question question = db.findQuestionById(501);

        statusLabel.setText("Тестирование началось");

        if (question == null)
            questionLabel.setText("Вопрос не найден");
        else {
            questionLabel.setText(question.getQuestionText());
            ArrayList<Question.Answer> answers = question.getAnswers();

            answersContainer.getChildren().clear();
            ToggleGroup toggleGroup = new ToggleGroup();

            for (Question.Answer answer : answers) {
                RadioButton radioButton = new RadioButton(answer.getAnswerText());
                radioButton.setToggleGroup(toggleGroup);
                answersContainer.getChildren().add(radioButton);
            }
        }
    }

    public void initialize() {
        System.out.println("Интерфейс успешно загружен");
    }
}
