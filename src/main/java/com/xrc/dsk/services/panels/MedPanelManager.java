package com.xrc.dsk.services.panels;

import com.xrc.dsk.controllers.MedicineCalculationPanelController;
import com.xrc.dsk.panels.MaterialPanel;
import com.xrc.dsk.panels.MedicineCalculationPanel;
import com.xrc.dsk.panels.OpeningPanel;
import com.xrc.dsk.services.MedPanelDataService;
import com.xrc.dsk.viewModels.medicine.MedicineDataViewModel;
import com.xrc.dsk.viewModels.medicine.PanelDataViewModel;
import javafx.scene.layout.HBox;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MedPanelManager implements PanelsManager {
    private final MedicineCalculationPanel panel;
    private final MedicineDataViewModel viewModel;
    private List<MaterialPanel> materialPanels;
    private List<OpeningPanel> openingPanels;
    private MedicineCalculationPanelController controller;
    private final HBox parentPanel;
    private MedPanelDataService dataService;

    public MedPanelManager(MedicineCalculationPanel panel, MedicineDataViewModel viewModel, MedicineCalculationPanelController controller) {
        this.panel = panel;
        this.viewModel = viewModel;
        this.controller = controller;
        this.parentPanel = panel.getParentPanel();
        controller.setPanel(panel);
    }

    public MedPanelManager(MedicineCalculationPanel panel, MedicineDataViewModel viewModel,
                           MedicineCalculationPanelController controller,
                           List<MaterialPanel> materialPanels,
                           List<OpeningPanel> openingPanels) {
        this(panel, viewModel, controller);
        this.materialPanels = materialPanels;
        this.openingPanels = openingPanels;
    }

    @Override
    public void init() {
        viewModel.getPanelDataProperty().add(new PanelDataViewModel());
        if (materialPanels == null || materialPanels.isEmpty()) {
            materialPanels = new ArrayList<>();
            materialPanels.add(new MaterialPanel(panel, viewModel));
        }
        if (openingPanels == null || openingPanels.isEmpty()) {
            openingPanels = new ArrayList<>();
            openingPanels.add(new OpeningPanel(panel, viewModel));
        }

        materialPanels.forEach(MaterialPanel::addToParentNode);
        openingPanels.forEach(OpeningPanel::addToParentNode);

        controller.setId(panel.getPanelId());
        controller.setViewModel(viewModel);

        bind();

        log.info("{} panel has been created", panel.getClass().getSimpleName());
    }

    private void bind() {
        dataService = new MedPanelDataService(panel.getPanelId(), viewModel);
        dataService.bindTextFields(controller.getWallName(), controller.getRoomAssignment(), controller.getPersonalCategory());
        dataService.bindSourceData(controller.getDmd(), controller.getDirectionCoefficient(), controller.getDistance());
        dataService.bindProtectionData(controller.getAttenuationCoefficient(), controller.getLeadEquivalent());
        dataService.bindAdditionalLeadEquivalent(controller.getAdditionalProtection());
        dataService.bindAdditionalMaterial(controller.getAnalogMaterial(), controller.getAnalogMaterialThickness());
        log.info("fields bindings  for {} has been finished", panel.getClass().getSimpleName());
    }

    public void deletePanel() {
        dataService.deletePanel(viewModel);
    }

    public void addToParentNode() {
        parentPanel.getChildren().add(panel.getRootNode());
    }

}
