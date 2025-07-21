package com.xrc.dsk.controllers;

import javafx.stage.Stage;

public interface StageCtrl {
    void setStage(Stage stage);
    default void closeWindow() {
        if(getStage() != null && getStage().isShowing()) {
            getStage().close();
        }
    }
    Stage getStage();
}
