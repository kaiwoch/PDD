package com.text;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {
    @FXML
    private Button startButton;

    public void startTest() {
        System.out.println("Тестирование началось");
        startButton.setText("Тестирование началось");
    }
}
