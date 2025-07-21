package com.xrc.dsk.windows;

import com.xrc.dsk.controllers.StageCtrl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public abstract class GeneralWindow <T extends StageCtrl> extends Window {
    private final String windowPath;
    private final String title;
    private Stage stage;
    @Getter
    private T controller;

    public void show() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(windowPath));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle(title);
            controller = loader.getController();
            controller.setStage(stage);
            this.stage = stage;
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        this.stage.close();
    }
}
