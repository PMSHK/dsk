package com.xrc.dsk.controllers;

import com.xrc.dsk.panels.MaterialPanel;
import com.xrc.dsk.panels.OpeningPanel;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import lombok.Setter;

public class OpeningController {

    @Setter
    private OpeningPanel openingPanel;
    @FXML
    private FontAwesomeIconView addButton;

    @FXML
    private FontAwesomeIconView deleteButton;

    @FXML
    private Label leadEquivalentLabel;

    @FXML
    private VBox openingPlate;

    @FXML
    private ComboBox<?> openingBox;

    @FXML
    void addOpeningLayer(MouseEvent event) {
OpeningPanel newOpeningLayer = new OpeningPanel(openingPanel.getParentPanel());
    }

    @FXML
    void deleteOpeningLayer(MouseEvent event) {
openingPanel.getParentPanel().getMaterialBase().getChildren().remove(openingPlate);
    }
}
