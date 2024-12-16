package com.xrc.dsk.panels;


import com.xrc.dsk.controllers.OpeningController;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpeningPanel extends Panel {

    private CalculationPanel parentPanel;
    private OpeningController openingController;

    public OpeningPanel(CalculationPanel parent) {
        super("/com/xrc/dsk/windows/opening-panel.fxml");
        this.parentPanel = parent;

        initialize();
    }

    @Override
    public void initialize() {
        this.openingController = getController();
        openingController.setOpeningPanel(this);
        System.out.println("Opening panel initialized");
    }

    public void addToParentNode() {
        parentPanel.getOpeningBase().getChildren().add(this.getRootNode());
    }

}
