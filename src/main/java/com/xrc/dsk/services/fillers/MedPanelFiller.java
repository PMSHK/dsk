package com.xrc.dsk.services.fillers;

import com.xrc.dsk.controllers.MedicineCalculationPanelController;
import com.xrc.dsk.panels.CalculationPanel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MedPanelFiller extends PanelsFiller{
    private final MedicineCalculationPanelController controller;
    public MedPanelFiller(CalculationPanel panel, MedicineCalculationPanelController controller) {
        super(panel);
        this.controller = controller;
    }

    @Override
    public void fill() {
        ObservableList<String> categories = FXCollections.observableList(getConnectionService().getPersonalCategories());
        ObservableList<Double> directionCoefficients = FXCollections.observableList(getConnectionService().getDirectionCoefficients());
        ObservableList<String> materials = FXCollections.observableList(getConnectionService().getAllMaterials());

        controller.getPersonalCategory().setItems(categories);
        controller.getDirectionCoefficient().setItems(directionCoefficients);
        controller.getAnalogMaterial().setItems(materials);
    }
}
