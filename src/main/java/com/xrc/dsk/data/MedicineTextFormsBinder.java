package com.xrc.dsk.data;

import com.xrc.dsk.dto.MedWindowDto;
import com.xrc.dsk.dto.PanelDataDto;
import com.xrc.dsk.dto.WindowDto;
import com.xrc.dsk.viewModels.DataViewModel;
import com.xrc.dsk.viewModels.medicine.MedicineDataViewModel;
import com.xrc.dsk.viewModels.medicine.PanelDataViewModel;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class MedicineTextFormsBinder implements Bindable {
    private final Binder binder;
    private Integer panelId;
    private TextArea roomSignAea;
    private TextArea adjacentRoomArea;
    private ComboBox<String> personalCategoryField;
    private MedicineDataViewModel viewModel;

    public MedicineTextFormsBinder() {
        binder = new Binder();
    }

    public MedicineTextFormsBinder(TextArea roomSignAea,
                                   TextArea adjacentRoomArea,
                                   ComboBox<String> personalCategoryField,
                                   int panelId,
                                   DataViewModel<?> viewModel) {
        this();
        this.roomSignAea = roomSignAea;
        this.adjacentRoomArea = adjacentRoomArea;
        this.personalCategoryField = personalCategoryField;
        this.panelId = panelId;
        this.viewModel = (MedicineDataViewModel) viewModel;
    }

    @Override
    public void bind() {
        PanelDataViewModel panelDataVM = viewModel.getPanelDataViewModelList().get(panelId);
        StringProperty wallSignProperty = panelDataVM.getTextFormViewModel().getWallSignProperty();
        StringProperty personalCategoryProperty = panelDataVM.getTextFormViewModel().getPersonalCategoryProperty();
        StringProperty adjacentRoomProperty = panelDataVM.getTextFormViewModel().getPurposeAdjacentRoomProperty();

        binder.bindTextPropertyToString(roomSignAea.textProperty(), wallSignProperty);
        binder.bindTextPropertyToString(adjacentRoomArea.textProperty(), adjacentRoomProperty);
        binder.bindTextPropertyToString(personalCategoryField.valueProperty(), personalCategoryProperty);
    }
}
