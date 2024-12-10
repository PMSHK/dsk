package com.xrc.dsk.windows;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public abstract class GeneralWindow {
    private final String windowPath;
    private final String title;
    private Stage stage;
    @Getter
    private WindowControl controller;

    public void show() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(windowPath));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle(title);
            controller = loader.getController();
//            controller.setStage(stage);
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
