package com.xrc.dsk.viewModels.medicine;

import com.xrc.dsk.data.bin.AppData;
import com.xrc.dsk.dto.medicine.MedicineDataDto;
import com.xrc.dsk.dto.medicine.PanelDataDto;
import com.xrc.dsk.dto.medicine.RadTypeDataDto;
import com.xrc.dsk.viewModels.DataViewModel;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;

import java.util.List;

public class MedicineDataViewModel implements DataViewModel<MedicineDataDto> {

    private ObjectProperty<RadTypeDataViewModel> radiationType;
    private ListProperty<PanelDataViewModel> panelData = new SimpleListProperty<>(FXCollections.observableArrayList());

    public MedicineDataViewModel(MedicineDataDto dto) {
        this.radiationType = new SimpleObjectProperty<>(new RadTypeDataViewModel(dto));
        this.panelData.setAll(dto.getPanelDataDtoList().stream().map(PanelDataViewModel::new).toList());
    }

    @Override
    public MedicineDataDto toDto() {
        List<PanelDataDto> panelDataDtoList = panelData.get().stream().map(PanelDataViewModel::toDto).toList();
        return new MedicineDataDto(radiationType.get().toDto(),panelDataDtoList);
    }

    @Override
    public void fromDto(AppData dto) {
        this.radiationType.set(new RadTypeDataViewModel((MedicineDataDto) dto));
        this.panelData.set(FXCollections.observableArrayList(
                ((MedicineDataDto)dto).getPanelDataDtoList().stream().map(PanelDataViewModel::new).toList()
        ));
    }
}
