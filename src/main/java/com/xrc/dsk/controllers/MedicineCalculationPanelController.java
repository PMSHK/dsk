package com.xrc.dsk.controllers;

import com.xrc.dsk.model.PanelsStorage;
import com.xrc.dsk.panels.MedicineCalculationPanel;
import com.xrc.dsk.services.MedicinePanelService;
import com.xrc.dsk.services.MedicineWindowService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class MedicineCalculationPanelController {
    private MedicinePanelService medicinePanelService;
    private MedicineWindowService medicineWindowService;
    private PanelsStorage panelsStorage = PanelsStorage.getInstance();
    @FXML
    private FontAwesomeIconView addButton;

    @FXML
    private Label additionalProtection;

    @FXML
    private ComboBox<?> analogMaterial;

    @FXML
    private Label analogMaterialThickness;

    @FXML
    private Label attenuationCoefficient;

    @FXML
    private FontAwesomeIconView calculatedParamMark;

    @FXML
    private VBox calculationPanel;

    @FXML
    private FontAwesomeIconView deleteButton;

    @FXML
    private ComboBox<?> directionCoefficient;

    @FXML
    private TextField distance;

    @FXML
    private Label dmd;

    @FXML
    private VBox exMatStorage;

    @FXML
    private Label leadEquivalent;

    @FXML
    private VBox openingsStorage;

    @FXML
    private ComboBox<?> personalCategory;

    @FXML
    private TextArea roomAssignment;

    @FXML
    private TextArea wallName;

    @FXML
    void addNewPanel(MouseEvent event) {
        panelsStorage.getPanelsStorage().getChildren().add(new MedicineCalculationPanel().getRootNode());
        System.out.println("Panel added");

    }

    @FXML
    void deleteRecentPanel(MouseEvent event) {
        panelsStorage.getPanelsStorage().getChildren().remove(calculationPanel);
    }

}
