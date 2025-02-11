package com.xrc.dsk.data;

import com.xrc.dsk.dto.MedWindowDto;
import com.xrc.dsk.dto.PanelDataDto;
import com.xrc.dsk.dto.WindowDto;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.util.Map;

import static com.xrc.dsk.data.AppParameters.PERSONAL_CATEGORY;
import static com.xrc.dsk.data.AppParameters.ROOM_PURPOSE;
import static com.xrc.dsk.data.AppParameters.WALL_SIGN;

public class MedicineTextFormsBinder implements Bindable {
    private final Binder binder;
    private Integer panelId;

    public MedicineTextFormsBinder() {
        binder = new Binder();
    }

    @Override
    public void bind(WindowDto dto, Map<String, Object[]> components) {
        this.panelId = (Integer) components.get(WALL_SIGN)[1];

        MedWindowDto windowDto = (MedWindowDto) dto;
        PanelDataDto panelDataDto = windowDto.getPanelDataProperty().get(panelId);
        StringProperty wallSignProperty = panelDataDto.getTextFormDto().wallSignProperty();
        StringProperty personalCategoryProperty = panelDataDto.getTextFormDto().personalCategoryProperty();
        StringProperty adjacentRoomProperty = panelDataDto.getTextFormDto().purposeAdjacentRoomProperty();

        TextArea roomSignAea = (TextArea) components.get(WALL_SIGN)[0];
        TextArea adjacentRoomArea = (TextArea) components.get(ROOM_PURPOSE)[0];
        ComboBox<String> personalCategoryField = (ComboBox<String>) components.get(PERSONAL_CATEGORY)[0];

        binder.bindTextPropertyToString(roomSignAea.textProperty(), wallSignProperty);
        binder.bindTextPropertyToString(adjacentRoomArea.textProperty(), adjacentRoomProperty);
        binder.bindTextPropertyToString(personalCategoryField.valueProperty(), personalCategoryProperty);
    }
}
