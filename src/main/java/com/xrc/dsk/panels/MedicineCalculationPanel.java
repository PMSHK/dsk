package com.xrc.dsk.panels;

import com.xrc.dsk.controllers.MedicineCalculationPanelController;
import com.xrc.dsk.services.fillers.MedPanelFiller;
import com.xrc.dsk.services.fillers.PanelsFiller;
import com.xrc.dsk.services.panels.MedPanelManager;
import com.xrc.dsk.services.panels.PanelsManager;
import com.xrc.dsk.viewModels.DataViewModel;
import com.xrc.dsk.viewModels.medicine.MedicineDataViewModel;
import javafx.scene.layout.Pane;

public class MedicineCalculationPanel extends CalculationPanel {
    private final MedicineCalculationPanelController controller;
    private final Integer id;
    private final PanelsFiller panelsFiller;



    private final PanelsManager panelsManager;
    private final MedicineDataViewModel viewModel;

    public MedicineCalculationPanel(int id, MedicineDataViewModel viewModel) {
        super("/com/xrc/dsk/windows/medicine-calculation-panel.fxml");
        this.id = id;
        this.controller = getController();
        this.panelsFiller = new MedPanelFiller(this, controller);
        this.panelsManager = new MedPanelManager(this, viewModel, controller);
        this.viewModel = viewModel;
        initialize();
    }

    @Override
    public void initialize() {
        panelsFiller.fill();
        panelsManager.init();
    }

    @Override
    public int getPanelId() {
        return id;
    }

    @Override
    public Pane getMaterialBase() {
        return controller.getExMatStorage();
    }

    @Override
    public Pane getOpeningBase() {
        return controller.getOpeningsStorage();
    }

    @Override
    public MedicineDataViewModel getDataViewModel() {
        return viewModel;
    }
}
