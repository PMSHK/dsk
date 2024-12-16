package com.xrc.dsk.panels;


import com.xrc.dsk.controllers.MaterialController;
import com.xrc.dsk.controllers.OpeningController;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpeningPanel extends GeneralPanel {
    @Override
    public Pane getMaterialBase() {
        return null;
    }


    private GeneralPanel parentPanel;
    private OpeningController openingController;

    public OpeningPanel(GeneralPanel parent) {
        super("/com/xrc/dsk/windows/opening-panel.fxml");
        this.parentPanel = parent;

        initialize();
    }

    @Override
    public void initialize() {
        this.openingController = getController();
        openingController.setOpeningPanel(this);
        System.out.println("Material panel initialized");
    }

    public void addToParentNode() {
        parentPanel.getMaterialBase().getChildren().add(this.getRootNode());
    }

}
