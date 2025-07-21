package com.xrc.dsk.controllers;

import com.xrc.dsk.windows.MaterialManagerWindow;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class SettingsController implements StageCtrl {

    private Stage stage;
    @FXML
    private Button closeBtn;

    @FXML
    private Button genSettingsBtn;

    @FXML
    private Button matBtn;

    @FXML
    private Button statisticsBtn;

    @FXML
    void close(MouseEvent event) {
        closeWindow();
    }

    @FXML
    void openGeneralSettings(MouseEvent event) {

    }

    @FXML
    void openMatManager(MouseEvent event) {
        MaterialManagerWindow window = new MaterialManagerWindow();
        window.show();
    }

    @FXML
    void openStatistics(MouseEvent event) {

    }

    @Override
    public void closeWindow() {
        StageCtrl.super.closeWindow();
    }
}
