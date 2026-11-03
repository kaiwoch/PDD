package com.text;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainController {
    @FXML
    private Label statusLabel;

    public void startTest() {
        System.out.println("Тестирование началось");

        statusLabel.setText("Тестирование началось");
    }

    public void initialize() {
        System.out.println("Интерфейс успешно загружен");
    }
}
