package com.xrc.dsk.controllers;

import com.xrc.dsk.panels.MaterialPanel;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;
@Getter
public class MaterialController {

    @Setter
    private MaterialPanel materialPanel;
    @FXML
    private FontAwesomeIconView addButton;

    @FXML
    private FontAwesomeIconView deleteButton;

    @FXML
    private ComboBox<String> materialBox;

    @FXML
    private VBox materialPlate;

    @FXML
    private TextField thicknessField;

    @FXML
    void addMatLayer(MouseEvent event) {
        MaterialPanel newMaterialLayer = new MaterialPanel(materialPanel.getParentPanel());
        newMaterialLayer.addToParentNode();
    }

    @FXML
    void deleteMatLayer(MouseEvent event) {
        materialPanel.getParentPanel().getMaterialBase().getChildren().remove(materialPlate);
        System.out.println("deleting material layer");
    }

    @FXML
    void getLeadEquivalent(KeyEvent event) {

    }
}
