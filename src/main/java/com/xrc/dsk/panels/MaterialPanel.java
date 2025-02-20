package com.xrc.dsk.panels;


import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.controllers.MaterialController;
import com.xrc.dsk.dto.MaterialDto;
import com.xrc.dsk.dto.MaterialInfoDto;
import com.xrc.dsk.services.MaterialPanelDataService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaterialPanel extends Panel {

    private CalculationPanel parentPanel;
    private MaterialController materialController;
    private MaterialInfoDto materialInfoDto;
    private MaterialDto dto;

    public MaterialPanel(CalculationPanel parent) {
        super("/com/xrc/dsk/windows/material-panel.fxml");
        this.parentPanel = parent;

        initialize();
    }

    @Override
    public void initialize() {
        dto = new MaterialDto();

        this.materialController = getController();
        materialController.setMaterialPanel(this);
        ConnectionService connectionService = new ConnectionService();
        ObservableList<String> materials = FXCollections.observableList(connectionService.getAllMaterials());
        materialController.getMaterialBox().setItems(materials);
        System.out.println("Material panel initialized");

        bind();
    }

    private void bind() {
        MaterialPanelDataService service = new MaterialPanelDataService(
                materialController.getMaterialBox(),
                materialController.getThicknessField(),
                materialController.getMatEqvLabel(),
                dto, parentPanel.getPanelId()
        );
        service.bind();
    }

    public void addToParentNode() {
        parentPanel.getMaterialBase().getChildren().add(this.getRootNode());
    }

}
