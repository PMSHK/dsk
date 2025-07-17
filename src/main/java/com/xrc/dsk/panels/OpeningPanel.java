package com.xrc.dsk.panels;


import com.xrc.dsk.controllers.OpeningController;
import com.xrc.dsk.services.panels.medicine.OpeningsPanelManager;
import com.xrc.dsk.viewModels.DataViewModel;
import com.xrc.dsk.viewModels.medicine.MedicineDataViewModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpeningPanel extends Panel {

    private CalculationPanel parentPanel;
    private OpeningController openingController;
    private final OpeningsPanelManager manager;

    public OpeningPanel(CalculationPanel parent, DataViewModel<?> viewModel) {
        super("/com/xrc/dsk/windows/opening-panel.fxml");
        this.parentPanel = parent;
        manager = new OpeningsPanelManager(this, (MedicineDataViewModel) viewModel);
        initialize();
    }

    @Override
    public void initialize() {
        manager.init();
    }

    public void deletePanel(){
        manager.deleteOpeningPanel();
    }

    public void addToParentNode() {
        manager.addToParentNode();
    }

}
