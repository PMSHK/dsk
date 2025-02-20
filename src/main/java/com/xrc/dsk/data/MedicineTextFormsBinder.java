package com.xrc.dsk.data;

import com.xrc.dsk.dto.MedWindowDto;
import com.xrc.dsk.dto.PanelDataDto;
import com.xrc.dsk.dto.WindowDto;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class MedicineTextFormsBinder implements Bindable{
    private final Binder binder;
    private Integer panelId;
    private TextArea roomSignAea;
    private TextArea adjacentRoomArea;
    private ComboBox<String> personalCategoryField;

    public MedicineTextFormsBinder() {
        binder = new Binder();
    }

    public MedicineTextFormsBinder(TextArea roomSignAea, TextArea adjacentRoomArea, ComboBox<String> personalCategoryField, int panelId) {
        binder = new Binder();
        this.roomSignAea = roomSignAea;
        this.adjacentRoomArea = adjacentRoomArea;
        this.personalCategoryField = personalCategoryField;
        this.panelId = panelId;
    }

@Override
    public void bind(WindowDto dto) {
        MedWindowDto windowDto = (MedWindowDto) dto;
        PanelDataDto panelDataDto = windowDto.getPanelDataProperty().get(panelId);
        StringProperty wallSignProperty = panelDataDto.getTextFormDto().wallSignProperty();
        StringProperty personalCategoryProperty = panelDataDto.getTextFormDto().personalCategoryProperty();
        StringProperty adjacentRoomProperty = panelDataDto.getTextFormDto().purposeAdjacentRoomProperty();

        binder.bindTextPropertyToString(roomSignAea.textProperty(), wallSignProperty);
        binder.bindTextPropertyToString(adjacentRoomArea.textProperty(), adjacentRoomProperty);
        binder.bindTextPropertyToString(personalCategoryField.valueProperty(), personalCategoryProperty);
    }
}
