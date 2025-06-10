package com.xrc.dsk.viewModels.medicine;

import com.xrc.dsk.data.bin.AppData;
import com.xrc.dsk.dto.medicine.MaterialInfoDataDto;
import com.xrc.dsk.viewModels.DataViewModel;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;

@Getter

public class MatInfoViewModel implements DataViewModel<MaterialInfoDataDto> {

    private StringProperty name = new SimpleStringProperty();
    private FloatProperty density = new SimpleFloatProperty();

    public MatInfoViewModel(MaterialInfoDataDto dto) {
        this.name.set(dto.getName());
        this.density.set(dto.getDensity());
    }

    @Override
    public MaterialInfoDataDto toDto() {
        return new MaterialInfoDataDto(name.get(), density.get());
    }

    @Override
    public void fromDto(AppData dto) {
        MaterialInfoDataDto data = (MaterialInfoDataDto) dto;
        this.name.set(data.getName());
        this.density.set(data.getDensity());
    }
}
