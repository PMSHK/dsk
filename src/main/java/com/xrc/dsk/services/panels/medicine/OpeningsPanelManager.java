package com.xrc.dsk.services.panels.medicine;

import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.controllers.OpeningController;
import com.xrc.dsk.dto.medicine.OpeningsDataDto;
import com.xrc.dsk.panels.CalculationPanel;
import com.xrc.dsk.panels.OpeningPanel;
import com.xrc.dsk.services.panels.PanelsManager;
import com.xrc.dsk.viewModels.medicine.MedicineDataViewModel;
import com.xrc.dsk.viewModels.medicine.OpeningsViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OpeningsPanelManager implements PanelsManager {
    private final OpeningPanel panel;
    private final MedicineDataViewModel generalVM;
    private OpeningsViewModel panelVM;
    private CalculationPanel parentPanel;

    private OpeningController controller;
    private ConnectionService connectionService = new ConnectionService();
    private OpeningPanelDataService service;

    public OpeningsPanelManager(OpeningPanel panel, MedicineDataViewModel generalVM) {
        this.panel = panel;
        this.generalVM = generalVM;
        this.controller = panel.getController();
        this.parentPanel = panel.getParentPanel();
    }

    @Override
    public void init() {
        log.info("Initializing opening panel");
        controller.setOpeningPanel(panel);
        controller.setViewModel(generalVM);
        OpeningsDataDto dto = new OpeningsDataDto();
        this.panelVM = new OpeningsViewModel(dto);
        ObservableList<String> openings = FXCollections.observableList(connectionService.getAllOpenings());
        controller.getOpeningBox().setItems(openings);
        bind();
        service.addNewPanel(panelVM);
        System.out.println("Opening panel initialized");

    }

    private void bind() {
        service = new OpeningPanelDataService(
                controller.getOpeningBox(),
                controller.getLeadEquivalentLabel(),
                generalVM, panelVM, parentPanel.getPanelId()
        );
        service.bind();
    }

    public void deleteOpeningPanel() {
        service.deletePanel(panelVM);
    }

    public void addToParentNode() {
        panel.getParentPanel().getOpeningBase().getChildren().add(panel.getRootNode());
    }
}
