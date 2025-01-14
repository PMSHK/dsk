package com.xrc.dsk.services;

import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.data.DataStorage;
import com.xrc.dsk.data.MedicineSourceDataBinder;
import com.xrc.dsk.data.MedicineTextFormsBinder;
import com.xrc.dsk.dto.MedWindowDto;
import com.xrc.dsk.dto.PanelDataDto;
import com.xrc.dsk.handlers.ComboBoxHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

import static com.xrc.dsk.data.AppParameters.DIRECTION_COEFFICIENT;
import static com.xrc.dsk.data.AppParameters.DISTANCE;
import static com.xrc.dsk.data.AppParameters.DMD;
import static com.xrc.dsk.data.AppParameters.PERSONAL_CATEGORY;
import static com.xrc.dsk.data.AppParameters.ROOM_PURPOSE;
import static com.xrc.dsk.data.AppParameters.WALL_SIGN;

public class MedPanelDataService {
    private ConnectionService connectionService;
    private DataStorage dataStorage = DataStorage.getInstance();
    private Map<String, Object[]> elements;
    @Setter
    private Integer panelId;

    public void bindTextFields(
            TextArea roomSignArea
            , TextArea purposeAdjacentRoomArea
            , ComboBox<String> personalCategoryField) {
        MedWindowDto dto = getMedWindowDto();
        elements = getElements();
        elements.put(WALL_SIGN, new Object[]{roomSignArea, panelId});
        elements.put(ROOM_PURPOSE, new Object[]{purposeAdjacentRoomArea, panelId});
        elements.put(PERSONAL_CATEGORY, new Object[]{personalCategoryField, panelId});
        MedicineTextFormsBinder binder = new MedicineTextFormsBinder();
        binder.bind(dto, elements);
    }

    public void addNewPanel() {
        MedWindowDto dto = getMedWindowDto();
        dto.getPanelData().add(new PanelDataDto());
        elements = getElements();
    }

    public void bindSourceData(Label dmdLabel, ComboBox<Double> attenuationCoefficientBox, TextField distanceField) {
        MedWindowDto dto = getMedWindowDto();
        elements = getElements();
        elements.put(DMD, new Object[]{dmdLabel, panelId});
        elements.put(DIRECTION_COEFFICIENT, new Object[]{attenuationCoefficientBox, panelId});
        elements.put(DISTANCE, new Object[]{distanceField, panelId});
        MedicineSourceDataBinder binder = new MedicineSourceDataBinder();
        binder.bind(dto, elements);
    }

    public void selectElement (ComboBox<Double> directionCoefficientBox){
        ComboBoxHandler<Double> handler = new ComboBoxHandler<>(directionCoefficientBox);
        directionCoefficientBox.setPromptText(String.valueOf(handler.getElement()));
        System.out.println("Selected direction coefficient: " + handler.getElement());
    }

    private Map<String, Object[]> getElements() {
        if (elements == null) {
            elements = new HashMap<>();
        }
        return elements;
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
