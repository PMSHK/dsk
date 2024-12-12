package com.xrc.dsk.panels;

import com.xrc.dsk.controllers.MedicineCalculationPanelController;

public class MedicineCalculationPanel extends GeneralPanel {
    private MedicineCalculationPanel panel;
    private MedicineCalculationPanelController controller;

    public MedicineCalculationPanel() {
        super("/com/xrc/dsk/windows/medicine-calculation-panel.fxml");
        initialize();
    }

    @Override
    public void initialize() {
        System.out.println("panel initialized");

    }
}
