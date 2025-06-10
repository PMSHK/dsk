package com.xrc.dsk.viewModels.medicine;

import com.xrc.dsk.data.bin.AppData;
import com.xrc.dsk.dto.medicine.OpeningsDataDto;
import com.xrc.dsk.viewModels.DataViewModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;

@Getter

public class OpeningsViewModel implements DataViewModel<OpeningsDataDto> {

    private StringProperty name = new SimpleStringProperty();
    private DoubleProperty thickness = new SimpleDoubleProperty();

    public OpeningsViewModel(OpeningsDataDto dto) {
        this.name.set(dto.getName());
        this.thickness.set(dto.getThickness());
    }

    @Override
    public OpeningsDataDto toDto() {
        return new OpeningsDataDto(name.get(), thickness.get());
    }

    @Override
    public void fromDto(AppData dto) {
        OpeningsDataDto data = (OpeningsDataDto) dto;
        this.name.set(data.getName());
        this.thickness.set(data.getThickness());
    }
}
