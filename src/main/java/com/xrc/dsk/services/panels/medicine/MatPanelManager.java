package com.xrc.dsk.services.panels.medicine;

import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.controllers.MaterialController;
import com.xrc.dsk.dto.medicine.MatCharacteristicsDataDto;
import com.xrc.dsk.dto.medicine.MaterialInfoDataDto;
import com.xrc.dsk.panels.CalculationPanel;
import com.xrc.dsk.panels.MaterialPanel;
import com.xrc.dsk.services.MaterialPanelDataService;
import com.xrc.dsk.services.panels.PanelsManager;
import com.xrc.dsk.viewModels.medicine.MatCharacteristicsDataViewModel;
import com.xrc.dsk.viewModels.medicine.MedicineDataViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MatPanelManager implements PanelsManager {
    private final MedicineDataViewModel viewModel;
    private final MaterialPanel panel;
    private final MaterialController controller;
    private MaterialPanelDataService service;
    private CalculationPanel parentPanel;
    private MatCharacteristicsDataViewModel vm;

    public MatPanelManager(MaterialPanel panel, MedicineDataViewModel viewModel) {
        this.viewModel = viewModel;
        this.panel = panel;
        this.controller = panel.getController();
        this.parentPanel = panel.getParentPanel();
    }

    @Override
    public void init() {
        log.info("Initializing material panel");
        MatCharacteristicsDataDto dto = new MatCharacteristicsDataDto();
        MaterialInfoDataDto materialInfoDto = new MaterialInfoDataDto();
        dto.setInfo(materialInfoDto);
        this.vm = new MatCharacteristicsDataViewModel(dto);

        controller.setMaterialPanel(panel);
        controller.setViewModel(viewModel);
        ConnectionService connectionService = new ConnectionService();
        ObservableList<String> materials = FXCollections.observableList(connectionService.getAllMaterials());
        controller.getMaterialBox().setItems(materials);
        log.info("Material panel initialized");

        bind();
        service.addNewMaterial(vm);

        log.info("Material panel bound");
    }

    private void bind() {
        this.service = new MaterialPanelDataService(
                controller.getMaterialBox(),
                controller.getThicknessField(),
                controller.getMatEqvLabel(),
                vm, parentPanel.getPanelId(),
                viewModel
        );
        service.bind();
    }

    public void deletePanel() {
        service.deleteMaterial(vm);
    }

    public void addToParentNode() {
        parentPanel.getMaterialBase().getChildren().add(panel.getRootNode());

    }
}
