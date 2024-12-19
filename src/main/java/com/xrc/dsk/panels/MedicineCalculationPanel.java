package com.xrc.dsk.panels;

import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.controllers.MedicineCalculationPanelController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class MedicineCalculationPanel extends CalculationPanel {
    private MedicineCalculationPanel panel;
    private MedicineCalculationPanelController controller;
    private List<MaterialPanel> materialPanels;
    private List<OpeningPanel> openingPanels;

    public MedicineCalculationPanel() {
        super("/com/xrc/dsk/windows/medicine-calculation-panel.fxml");
        initialize();
    }

    @Override
    public void initialize() {
        if (materialPanels == null) {
            materialPanels = new ArrayList<>();
        }
        if (openingPanels == null) {
            openingPanels = new ArrayList<>();
        }

        this.controller = getController();
        if (materialPanels.isEmpty()) {
            materialPanels.add(new MaterialPanel(this));
        }
        if (openingPanels.isEmpty()) {
            openingPanels.add(new OpeningPanel(this));
        }
        fillComboBoxes();
        materialPanels.forEach(MaterialPanel::addToParentNode);
        openingPanels.forEach(OpeningPanel::addToParentNode);
        System.out.println("panel initialized");
    }

    private void fillComboBoxes(){
        ConnectionService connectionService = new ConnectionService();
        ObservableList<String> categories = FXCollections.observableList(connectionService.getPersonalCategories());
        ObservableList<Double> directionCoefficients = FXCollections.observableList(connectionService.getDirectionCoefficients());
        ObservableList<String> materials = FXCollections.observableList(connectionService.getAllMaterials());

        controller.getPersonalCategory().setItems(categories);
        controller.getDirectionCoefficient().setItems(directionCoefficients);
        controller.getAnalogMaterial().setItems(materials);
    }

    public void addMaterialPanel() {
        controller.getExMatStorage().getChildren().add(new MaterialPanel(this).getRootNode());
    }

    public void removeMaterialPanel(MaterialPanel materialPanel) {
        controller.getExMatStorage().getChildren().remove(materialPanel);
    }

    @Override
    public Pane getMaterialBase() {
        return controller.getExMatStorage();
    }

    @Override
    public Pane getOpeningBase() {
        return controller.getOpeningsStorage();
    }
}
