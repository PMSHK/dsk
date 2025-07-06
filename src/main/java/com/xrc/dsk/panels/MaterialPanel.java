package com.xrc.dsk.panels;


import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.controllers.MaterialController;
import com.xrc.dsk.dto.medicine.MatCharacteristicsDataDto;
import com.xrc.dsk.dto.medicine.MaterialInfoDataDto;
import com.xrc.dsk.services.MaterialPanelDataService;
import com.xrc.dsk.viewModels.DataViewModel;
import com.xrc.dsk.viewModels.medicine.MatCharacteristicsDataViewModel;
import com.xrc.dsk.viewModels.medicine.MedicineDataViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class MaterialPanel extends Panel {

    private CalculationPanel parentPanel;
    private MaterialController materialController;
    private MaterialInfoDataDto materialInfoDto;
    private MatCharacteristicsDataDto dto;
    private MaterialPanelDataService service;
    private MedicineDataViewModel viewModel;
    private MatCharacteristicsDataViewModel vm;

    public MaterialPanel(CalculationPanel parent, DataViewModel<?> viewModel) {
        super("/com/xrc/dsk/windows/material-panel.fxml");
        this.parentPanel = parent;
        this.viewModel = (MedicineDataViewModel)viewModel;
        initialize();
    }

    @Override
    public void initialize() {
        log.info("Initializing material panel");
        dto = new MatCharacteristicsDataDto();
        materialInfoDto = new MaterialInfoDataDto();
        dto.setInfo(materialInfoDto);
        this.vm = new MatCharacteristicsDataViewModel(dto);


        this.materialController = getController();
        materialController.setMaterialPanel(this);
        materialController.setViewModel(viewModel);
        ConnectionService connectionService = new ConnectionService();
        ObservableList<String> materials = FXCollections.observableList(connectionService.getAllMaterials());
        materialController.getMaterialBox().setItems(materials);
        log.info("Material panel initialized");

        bind();
        service.addNewMaterial(vm);
        log.info("Material panel bound");
    }

    public void deletePanel(){
        service.deleteMaterial(vm);
    }

    private void bind() {
        service = new MaterialPanelDataService(
                materialController.getMaterialBox(),
                materialController.getThicknessField(),
                materialController.getMatEqvLabel(),
                vm, parentPanel.getPanelId(),
                viewModel
        );
//        service.addNewMaterial(dto);
        service.bind();
    }

    public void addToParentNode() {
        parentPanel.getMaterialBase().getChildren().add(this.getRootNode());

    }

}
