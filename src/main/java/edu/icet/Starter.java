package edu.icet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Starter extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Login - Thoga Kade");
        stage.setResizable(false);
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/customer_management.fxml"))));
        stage.show();
    }
}
