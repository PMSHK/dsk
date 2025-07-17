package com.xrc.dsk.services.panels.medicine;

import com.xrc.dsk.services.openings.OpeningsBindingManager;
import com.xrc.dsk.services.openings.OpeningsCalcService;
import com.xrc.dsk.services.openings.OpeningsVMManager;
import com.xrc.dsk.viewModels.medicine.MedicineDataViewModel;
import com.xrc.dsk.viewModels.medicine.OpeningsViewModel;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OpeningPanelDataService {
    private final MedicineDataViewModel generalVM;
    private final OpeningsViewModel panelVM;
    private final ComboBox<String> openingBox;
    private final Label openingLabel;
    private final int panelId;

    public OpeningPanelDataService(
            ComboBox<String> openingBox,
            Label openingLabel,
            MedicineDataViewModel generalVM,
            OpeningsViewModel panelVM,
            int panelId) {
        this.generalVM = generalVM;
        this.panelVM = panelVM;
        this.openingBox = openingBox;
        this.openingLabel = openingLabel;
        this.panelId = panelId;
    }

    public void bind() {
        OpeningsVMManager manager = new OpeningsVMManager(generalVM.getPanelDataProperty().get(panelId), panelVM, generalVM);
        OpeningsCalcService calculator = new OpeningsCalcService(manager);
        OpeningsBindingManager binder = new OpeningsBindingManager(calculator, manager);
        binder.bind(openingBox, openingLabel);
    }

    public void addNewPanel(OpeningsViewModel vm) {
        log.info("Adding new opening panel");
        generalVM.getPanelDataProperty().get(panelId).getOpeningDtoList().add(vm);
        log.info("Added new opening panel");
    }

    public void deletePanel(OpeningsViewModel vm) {
        log.info("Deleting opening panel");
        generalVM.getPanelDataProperty().get(panelId).getOpeningDtoList().removeIf(vmCurr -> vmCurr.equals(vm));
        log.info("Deleted opening panel");
    }
}
