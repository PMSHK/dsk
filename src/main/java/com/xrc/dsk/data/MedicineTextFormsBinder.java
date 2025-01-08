package com.xrc.dsk.data;

import com.xrc.dsk.dto.MedWindowDto;
import com.xrc.dsk.dto.WindowDto;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Map;

public class MedicineTextFormsBinder implements Bindable {
    private final Binder binder;

    public MedicineTextFormsBinder() {
        binder = new Binder();
    }

    @Override
    public void bind(WindowDto dto, Map<String, Object[]> components) {
        TextArea roomSignAea = (TextArea) components.get("WALL")[0];
        TextArea adjacentRoomArea = (TextArea) components.get("PURPOSE")[0];
        TextField personalCategoryField = (TextField) components.get("PERSONAL_CATEGORY")[0];

//        ((MedWindowDto) dto)

    }
}
