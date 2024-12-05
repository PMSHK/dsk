package com.xrc.dsk;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DskController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}