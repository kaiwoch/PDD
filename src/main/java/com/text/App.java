package com.text;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("PravilaDD");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/text/main_view.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
