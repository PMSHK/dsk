package com.xrc.dsk.panels;


import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.controllers.MaterialController;
import com.xrc.dsk.dto.MaterialCharacteristicsDto;
import com.xrc.dsk.dto.MaterialInfoDto;
import com.xrc.dsk.services.MaterialPanelDataService;
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
    private MaterialInfoDto materialInfoDto;
    private MaterialCharacteristicsDto dto;
    private MaterialPanelDataService service;

    public MaterialPanel(CalculationPanel parent) {
        super("/com/xrc/dsk/windows/material-panel.fxml");
        this.parentPanel = parent;

        initialize();
    }

    @Override
    public void initialize() {
        log.info("Initializing material panel");
        dto = new MaterialCharacteristicsDto();
        materialInfoDto = new MaterialInfoDto();
        dto.setInfo(materialInfoDto);

        this.materialController = getController();
        materialController.setMaterialPanel(this);
        ConnectionService connectionService = new ConnectionService();
        ObservableList<String> materials = FXCollections.observableList(connectionService.getAllMaterials());
        materialController.getMaterialBox().setItems(materials);
        log.info("Material panel initialized");

        bind();
        service.addNewMaterial(dto);
        log.info("Material panel bound");
    }

    public void deletePanel(){
        service.deleteMaterial(dto);
    }

    private void bind() {
        service = new MaterialPanelDataService(
                materialController.getMaterialBox(),
                materialController.getThicknessField(),
                materialController.getMatEqvLabel(),
                dto, parentPanel.getPanelId()
        );
        service.addNewPanel();
        service.bind();
    }

    public void addToParentNode() {
        parentPanel.getMaterialBase().getChildren().add(this.getRootNode());

    }

}
