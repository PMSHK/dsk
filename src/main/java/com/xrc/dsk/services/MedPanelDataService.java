package com.xrc.dsk.services;

import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.data.AdditionalMatBinder;
import com.xrc.dsk.data.DataStorage;
import com.xrc.dsk.data.MedicineSourceDataBinder;
import com.xrc.dsk.data.MedicineTextFormsBinder;
import com.xrc.dsk.data.ProtectionDataBinder;
import com.xrc.dsk.dto.MedWindowDto;
import com.xrc.dsk.dto.PanelDataDto;
import com.xrc.dsk.handlers.ComboBoxHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.Setter;

public class MedPanelDataService {
    private ConnectionService connectionService;
    private DataStorage dataStorage = DataStorage.getInstance();
    @Setter
    private Integer panelId;

    public void bindTextFields(
            TextArea roomSignArea
            , TextArea purposeAdjacentRoomArea
            , ComboBox<String> personalCategoryField) {
        MedWindowDto dto = getMedWindowDto();
        MedicineTextFormsBinder binder = new MedicineTextFormsBinder(roomSignArea, purposeAdjacentRoomArea, personalCategoryField, panelId);
        binder.bind(dto);
    }

    public void addNewPanel() {
        MedWindowDto dto = getMedWindowDto();
        dto.getPanelData().add(new PanelDataDto());
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
