package com.xrc.dsk.services;

import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.dto.medicine.MatCharacteristicsDataDto;
import com.xrc.dsk.services.material.MatBindingManager;
import com.xrc.dsk.services.material.MatCalcService;
import com.xrc.dsk.services.material.MaterialViewModelManager;
import com.xrc.dsk.viewModels.medicine.MatCharacteristicsDataViewModel;
import com.xrc.dsk.viewModels.medicine.MedicineDataViewModel;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MaterialPanelDataService {
    private MatCharacteristicsDataDto materialCharacteristicsDto;
    private ComboBox<String> materialComboBox;
    private TextField thicknessTextField;
    private Label leadEquivalentLabel;
    private final ConnectionService connectionService;
    private int panelId;
    private MedicineDataViewModel viewModel;
    private MatCharacteristicsDataViewModel matVm;

    public MaterialPanelDataService(ComboBox<String> materialComboBox,
                                    TextField thicknessTextField,
                                    Label leadEquivalentLabel,
                                    MatCharacteristicsDataViewModel matVm,
                                    int panelId,
                                    MedicineDataViewModel viewModel
    ) {
        this.connectionService = new ConnectionService();
        this.materialComboBox = materialComboBox;
        this.thicknessTextField = thicknessTextField;
        this.leadEquivalentLabel = leadEquivalentLabel;
        this.matVm = matVm;
        this.panelId = panelId;
        this.viewModel = viewModel;
    }

    public void bind() {
        log.info("Binding to material panel data from service");

        MaterialViewModelManager manager = new MaterialViewModelManager(viewModel.getPanelDataProperty().get(panelId), matVm);
        MatCalcService calculator = new MatCalcService(manager, viewModel, true);
        MatBindingManager matBindingManager = new MatBindingManager(calculator, manager);
        log.info("materialComboBox: {}, thicknessField: {}, label: {}",
                materialComboBox, thicknessTextField, leadEquivalentLabel);
        matBindingManager.bindThicknessBaseUI(materialComboBox, thicknessTextField, leadEquivalentLabel);

    }

    public void addNewMaterial(MatCharacteristicsDataViewModel vm) {
        log.info("Adding new material to material panel data from service");
        viewModel.getPanelDataProperty().get(panelId).getExistedMatCharacteristicsViewModelList().add(vm);
        log.info("Added new material to material panel data from service");
    }

    public void deleteMaterial(MatCharacteristicsDataViewModel vm) {
        log.info("Deleting existing material to material panel data from service");
        viewModel.getPanelDataProperty().get(panelId).getExistedMatCharacteristicsViewModelList().removeIf(vmCurr -> vmCurr.equals(vm));
    }
}
