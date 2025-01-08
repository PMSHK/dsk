package com.xrc.dsk.services;

import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.data.DataStorage;
import com.xrc.dsk.data.MedicineTextFormsBinder;
import com.xrc.dsk.dto.MedWindowDto;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Map;

public class MedPanelDataService {
    private ConnectionService connectionService;
    private DataStorage dataStorage = DataStorage.getInstance();
    private Map<String,Object[]> elements;

    public void bindTextFields(
            TextArea roomSignArea
            , TextArea purposeAdjacentRoomArea
            , TextField personalCategoryField) {
        MedWindowDto dto = (MedWindowDto) dataStorage.getWindowDto();
        if (dto == null) {
            dto = new MedWindowDto();
            dataStorage.setWindowDto(dto);
        }
        if (elements == null) {
            elements = new HashMap<>();
        }
        elements.put("WALL", new Object[]{roomSignArea});
        elements.put("PURPOSE", new Object[]{purposeAdjacentRoomArea});
        elements.put("PERSONAL_CATEGORY", new Object[]{personalCategoryField});
        MedicineTextFormsBinder binder = new MedicineTextFormsBinder();
        binder.bind(dto,elements);
    }
}
