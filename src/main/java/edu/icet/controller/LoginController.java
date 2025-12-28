package edu.icet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    public ImageView rootPane;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private void initialize(){
        GaussianBlur blur = new GaussianBlur(10);
        rootPane.setEffect(blur);
    }

    Stage stage = new Stage();
    @FXML
    void btnLoginOnAction(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(username.isEmpty()){
            alert.setHeaderText("Please enter username");
            alert.show();
            return;
        }
        if(password.isEmpty()){
            alert.setHeaderText("Please enter password");
            alert.show();
            return;
        }

        if(username.equals("admin") && password.equals("1234")){
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setTitle("Dashboard - Thoga Kade");
            stage.setResizable(false);
            Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            oldStage.close();
            stage.show();
            return;
        }
        alert.setHeaderText("Login failed");
        alert.setContentText("Please check your username or password");
        alert.show();
    }
}