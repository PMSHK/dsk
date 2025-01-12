package com.xrc.dsk.services;

import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.data.DataStorage;
import com.xrc.dsk.data.MedicineTextFormsBinder;
import com.xrc.dsk.dto.MedWindowDto;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

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
        MedWindowDto dto = (MedWindowDto) dataStorage.getWindowDto();
        if (dto == null) {
            dto = new MedWindowDto();
            dataStorage.setWindowDto(dto);
        }
        if (elements == null) {
            elements = new HashMap<>();
        }
        elements.put("WALL", new Object[]{roomSignArea, panelId});
        elements.put("PURPOSE", new Object[]{purposeAdjacentRoomArea, panelId});
        elements.put("PERSONAL_CATEGORY", new Object[]{personalCategoryField, panelId});
        MedicineTextFormsBinder binder = new MedicineTextFormsBinder();
        binder.bind(dto, elements);
    }

}
