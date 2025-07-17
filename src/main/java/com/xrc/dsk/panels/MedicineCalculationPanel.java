package com.xrc.dsk.panels;

import com.xrc.dsk.controllers.MedicineCalculationPanelController;
import com.xrc.dsk.services.fillers.MedPanelFiller;
import com.xrc.dsk.services.fillers.PanelsFiller;
import com.xrc.dsk.services.panels.MedPanelManager;
import com.xrc.dsk.viewModels.medicine.MedicineDataViewModel;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import lombok.Getter;

public class MedicineCalculationPanel extends CalculationPanel {
    private final MedicineCalculationPanelController controller;
    private final Integer id;
    private final PanelsFiller panelsFiller;
    @Getter
    private final HBox parentPanel;


    private final MedPanelManager panelsManager;
    private final MedicineDataViewModel viewModel;

    public MedicineCalculationPanel(int id, MedicineDataViewModel viewModel, HBox parentPanel) {
        super("/com/xrc/dsk/windows/medicine-calculation-panel.fxml");
        this.id = id;
        this.controller = getController();
        this.parentPanel = parentPanel;
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

    public void deletePanel() {
        panelsManager.deletePanel();
    }

    public void addToParentNode() {
        panelsManager.addToParentNode();
    }
}
