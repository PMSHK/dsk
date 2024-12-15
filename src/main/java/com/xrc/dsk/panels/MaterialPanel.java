package com.xrc.dsk.panels;


import com.xrc.dsk.controllers.MaterialController;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaterialPanel extends GeneralPanel{
    @Override
    public Pane getMaterialBase() {
        return null;
    }

    private GeneralPanel parentPanel;
    private MaterialController materialController;
    public MaterialPanel(GeneralPanel parent) {
        super("/com/xrc/dsk/windows/material-panel.fxml");
        this.parentPanel = parent;

        initialize();
    }
    @Override
    public void initialize() {
        this.materialController = getController();
        materialController.setMaterialPanel(this);
//        materialController.setMaterialStorage(parentPanel.getMaterialBase());
        System.out.println("Material panel initialized");
    }

    public void addToParentNode(){
        parentPanel.getMaterialBase().getChildren().add(this.getRootNode());
    }

}
