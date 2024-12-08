package com.xrc.dsk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DskApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DskApplication.class.getResource("/com/xrc/dsk/windows/main-window.fxml"));
        String menuCssPath = DskApplication.class.getResource("/com/xrc/dsk/windows/menuStyle.css").toExternalForm();
        String buttonsCssPath = DskApplication.class.getResource("/com/xrc/dsk/windows/buttonsStyle.css").toExternalForm();
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        scene.getStylesheets().add(menuCssPath);
        scene.getStylesheets().add(buttonsCssPath);
        stage.setTitle("XRC");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}