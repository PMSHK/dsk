package com.xrc.dsk.panels;

import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class CalculationPanel extends Panel implements Initializable {

    public CalculationPanel(String path) {
        super(path);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public abstract Pane getMaterialBase();

    public abstract Pane getOpeningBase();

    public abstract void initialize();

}
