package edu.icet.controller;

import javafx.fxml.FXML;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;

public class DashboardController {
    @FXML
    private ImageView rootPane;

    @FXML
    private void initialize(){
        GaussianBlur blur = new GaussianBlur(10);
        rootPane.setEffect(blur);
    }
}
