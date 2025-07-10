package com.xrc.dsk.panels;


import com.xrc.dsk.services.panels.medicine.MatPanelManager;
import com.xrc.dsk.viewModels.DataViewModel;
import com.xrc.dsk.viewModels.medicine.MedicineDataViewModel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MaterialPanel extends Panel {
    @Getter
    private final CalculationPanel parentPanel;
    private final MatPanelManager manager;

    public MaterialPanel(CalculationPanel parent, DataViewModel<?> viewModel) {
        super("/com/xrc/dsk/windows/material-panel.fxml");
        this.parentPanel = parent;
        this.manager = new MatPanelManager(this, (MedicineDataViewModel) viewModel);
        initialize();
    }

    @Override
    public void initialize() {
        manager.init();
    }

    public void deletePanel() {
        manager.deletePanel();
    }

    public void addToParentNode() {
        manager.addToParentNode();
    }

}
