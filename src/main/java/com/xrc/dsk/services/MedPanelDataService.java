package com.xrc.dsk.services;

import com.xrc.dsk.data.AdditionalMatBinder;
import com.xrc.dsk.data.DataStorage;
import com.xrc.dsk.data.MaterialDataBinder;
import com.xrc.dsk.data.MedicineSourceDataBinder;
import com.xrc.dsk.data.MedicineTextFormsBinder;
import com.xrc.dsk.data.ProtectionDataBinder;
import com.xrc.dsk.dto.medicine.MatCharacteristicsDataDto;
import com.xrc.dsk.dto.medicine.MaterialInfoDataDto;
import com.xrc.dsk.handlers.ComboBoxHandler;
import com.xrc.dsk.services.material.MatBindingManager;
import com.xrc.dsk.services.material.MatCalcService;
import com.xrc.dsk.services.material.MaterialViewModelManager;
import com.xrc.dsk.viewModels.DataViewModel;
import com.xrc.dsk.viewModels.medicine.MatCharacteristicsDataViewModel;
import com.xrc.dsk.viewModels.medicine.MedicineDataViewModel;
import com.xrc.dsk.viewModels.medicine.PanelDataViewModel;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MedPanelDataService {
    private DataStorage dataStorage = DataStorage.getInstance();
    private MedicineDataViewModel viewModel;
    //    @Setter
    private int panelId;

    public MedPanelDataService(int panelId, DataViewModel<?> viewModel) {
        this.panelId = panelId;
        this.viewModel = (MedicineDataViewModel) viewModel;
    }

    public void bindTextFields(
            TextArea roomSignArea
            , TextArea purposeAdjacentRoomArea
            , ComboBox<String> personalCategoryField) {
        MedicineTextFormsBinder binder = new MedicineTextFormsBinder(
                roomSignArea,
                purposeAdjacentRoomArea,
                personalCategoryField,
                panelId, viewModel);
        binder.bind();
    }

//    public void addNewPanel() {
//        MedWindowDto dto = getMedWindowDto();
//        dto.getPanelData().add(new PanelDataDto());
//    }

    public void addNewPanelToVM() {
        viewModel.getPanelDataProperty().add(new PanelDataViewModel());
    }

    public void bindSourceData(Label dmdLabel, ComboBox<Double> attenuationCoefficientBox, TextField distanceField) {
        MedicineSourceDataBinder binder = new MedicineSourceDataBinder(dmdLabel, attenuationCoefficientBox, distanceField, panelId, viewModel);
        binder.bind();
    }

    public void bindProtectionData(Label attenuationFrequencyLabel, Label leadEquivalentLabel) {
        ProtectionDataBinder binder = new ProtectionDataBinder(attenuationFrequencyLabel, leadEquivalentLabel, panelId, viewModel);
        binder.bind();
    }

    public void bindAdditionalLeadEquivalent(Label leadEquivalentLabel) {
        AdditionalMatBinder binder = new AdditionalMatBinder(leadEquivalentLabel, panelId, viewModel);
        binder.bind();
    }

    public void bindAdditionalMaterial(ComboBox<String> materialComboBox, Label thicknessLabel) {
        MaterialInfoDataDto materialInfoDataDto = new MaterialInfoDataDto();
        MatCharacteristicsDataDto materialCharacteristicsDto = new MatCharacteristicsDataDto(materialInfoDataDto, 0D, 0D);
        MaterialViewModelManager manager = new MaterialViewModelManager(
                viewModel.getPanelDataProperty().get(panelId),
                new MatCharacteristicsDataViewModel(materialCharacteristicsDto));
        MatCalcService calculator = new MatCalcService(manager, viewModel, false);
        MatBindingManager matBindingManager = new MatBindingManager(calculator, manager);

        log.info("materialComboBox: {}, thicknessLabel: {}",
                materialComboBox, thicknessLabel);
        matBindingManager.bindLeadBaseUI(materialComboBox, thicknessLabel);
//        MaterialDataBinder binder = new MaterialDataBinder(materialComboBox, thicknessLabel, panelId, viewModel);
//        binder.bind();
    }

    public void selectElement(ComboBox<Double> directionCoefficientBox) {
        ComboBoxHandler<Double> handler = new ComboBoxHandler<>(directionCoefficientBox);
        directionCoefficientBox.setPromptText(String.valueOf(handler.getElement()));
        System.out.println("Selected direction coefficient: " + handler.getElement());
    }

//    private MedWindowDto getMedWindowDto() {
//        MedWindowDto dto = (MedWindowDto) dataStorage.getWindowDto();
//        if (dto == null) {
//            dto = new MedWindowDto();
//            dataStorage.setWindowDto(dto);
//        }
//        return dto;
//    }

}
