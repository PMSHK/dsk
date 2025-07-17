package com.xrc.dsk.controllers;

import com.xrc.dsk.panels.OpeningPanel;
import com.xrc.dsk.viewModels.medicine.MedicineDataViewModel;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;
@Getter
public class OpeningController {

    @Setter
    private OpeningPanel openingPanel;
    @Setter
    private MedicineDataViewModel viewModel;
    @FXML
    private FontAwesomeIconView addButton;

    @FXML
    private FontAwesomeIconView deleteButton;

    @FXML
    private Label leadEquivalentLabel;

    @FXML
    private VBox openingPlate;

    @FXML
    private ComboBox<String> openingBox;

    @FXML
    void addOpeningLayer(MouseEvent event) {
        OpeningPanel newOpeningLayer = new OpeningPanel(openingPanel.getParentPanel(),openingPanel.getParentPanel().getDataViewModel());
        newOpeningLayer.addToParentNode();
    }

    @FXML
    void deleteOpeningLayer(MouseEvent event) {
        openingPanel.getParentPanel().getOpeningBase().getChildren().remove(openingPlate);
        openingPanel.deletePanel();

    }
}
