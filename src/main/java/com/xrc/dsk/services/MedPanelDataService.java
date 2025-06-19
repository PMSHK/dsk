package com.xrc.dsk.services;

import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.data.AdditionalMatBinder;
import com.xrc.dsk.data.DataStorage;
import com.xrc.dsk.data.MaterialDataBinder;
import com.xrc.dsk.data.MedicineSourceDataBinder;
import com.xrc.dsk.data.MedicineTextFormsBinder;
import com.xrc.dsk.data.ProtectionDataBinder;
import com.xrc.dsk.dto.MedWindowDto;
import com.xrc.dsk.dto.PanelDataDto;
import com.xrc.dsk.handlers.ComboBoxHandler;
import com.xrc.dsk.viewModels.DataViewModel;
import com.xrc.dsk.viewModels.medicine.MedicineDataViewModel;
import com.xrc.dsk.viewModels.medicine.PanelDataViewModel;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.Setter;

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
        MedWindowDto dto = getMedWindowDto();
        MedicineTextFormsBinder binder = new MedicineTextFormsBinder(
                roomSignArea,
                purposeAdjacentRoomArea,
                personalCategoryField,
                panelId, viewModel);
        binder.bind(dto);
    }

    public void addNewPanel() {
        MedWindowDto dto = getMedWindowDto();
        dto.getPanelData().add(new PanelDataDto());
    }

    public void addNewPanelToVM() {
        viewModel.getPanelDataViewModelList().add(new PanelDataViewModel());
    }

    public void bindSourceData(Label dmdLabel, ComboBox<Double> attenuationCoefficientBox, TextField distanceField) {
        MedWindowDto dto = getMedWindowDto();
        MedicineSourceDataBinder binder = new MedicineSourceDataBinder(dmdLabel, attenuationCoefficientBox, distanceField, panelId);
        binder.bind(dto);
    }

    public void bindProtectionData(Label attenuationFrequencyLabel, Label leadEquivalentLabel) {
        MedWindowDto dto = getMedWindowDto();
        ProtectionDataBinder binder = new ProtectionDataBinder(attenuationFrequencyLabel, leadEquivalentLabel, panelId);
        binder.bind(dto);
    }

    public void bindAdditionalLeadEquivalent(Label leadEquivalentLabel) {
        MedWindowDto dto = getMedWindowDto();
        AdditionalMatBinder binder = new AdditionalMatBinder(leadEquivalentLabel, panelId);
        binder.bind(dto);
    }

    public void bindAdditionalMaterial(ComboBox<String> materialComboBox, Label thicknessLabel) {
        MedWindowDto dto = getMedWindowDto();
        MaterialDataBinder binder = new MaterialDataBinder(materialComboBox, thicknessLabel, panelId);
        binder.bind(dto);
    }

    public void selectElement(ComboBox<Double> directionCoefficientBox) {
        ComboBoxHandler<Double> handler = new ComboBoxHandler<>(directionCoefficientBox);
        directionCoefficientBox.setPromptText(String.valueOf(handler.getElement()));
        System.out.println("Selected direction coefficient: " + handler.getElement());
    }

    private MedWindowDto getMedWindowDto() {
        MedWindowDto dto = (MedWindowDto) dataStorage.getWindowDto();
        if (dto == null) {
            dto = new MedWindowDto();
            dataStorage.setWindowDto(dto);
        }
        return dto;
    }

}
