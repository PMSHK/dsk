package com.xrc.dsk.controllers;

import com.xrc.dsk.panels.MaterialPanel;
import com.xrc.dsk.services.MaterialPanelDataService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;
@Getter
public class MaterialController {

    private MaterialPanelDataService service;

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
    private Label matEqvLabel;

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

    @FXML
    void selectMaterial(ActionEvent event) {
//        service = new MaterialPanelDataService(materialBox,thicknessField,matEqvLabel);
    }
}
