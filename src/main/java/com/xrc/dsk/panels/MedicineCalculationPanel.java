package com.xrc.dsk.panels;

import com.xrc.dsk.controllers.MedicineCalculationPanelController;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class MedicineCalculationPanel extends GeneralPanel {
    private MedicineCalculationPanel panel;
    private MedicineCalculationPanelController controller;
    private List<MaterialPanel> materialPanels;

    public MedicineCalculationPanel() {
        super("/com/xrc/dsk/windows/medicine-calculation-panel.fxml");
        initialize();
    }

    @Override
    public Pane getMaterialBase() {
        return controller.getExMatStorage();
    }

    @Override
    public void initialize() {
        if (materialPanels == null) {
            materialPanels = new ArrayList<>();
        }

        this.controller = getController();
        if (materialPanels.isEmpty()) {
            materialPanels.add(new MaterialPanel(this));
        }
        materialPanels.forEach(MaterialPanel::addToParentNode);
//        materialPanels.stream().map(materialPanel ->
//                controller
//                .getExMatStorage()
//                .getChildren()
//                .add(materialPanel.getRootNode())).toList();
        System.out.println("panel initialized");
    }

    public void addMaterialPanel() {
        controller.getExMatStorage().getChildren().add(new MaterialPanel(this).getRootNode());
    }

    public void removeMaterialPanel(MaterialPanel materialPanel) {
        controller.getExMatStorage().getChildren().remove(materialPanel);
    }
}
