package com.xrc.dsk.viewModels.medicine;

import com.xrc.dsk.data.bin.AppData;
import com.xrc.dsk.dto.medicine.MatCharacteristicsDataDto;
import com.xrc.dsk.dto.medicine.MaterialInfoDataDto;
import com.xrc.dsk.viewModels.DataViewModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MatCharacteristicsDataViewModel implements DataViewModel<MatCharacteristicsDataDto> {

    private ObjectProperty<MatInfoViewModel> info = new SimpleObjectProperty<>();
    private DoubleProperty thickness = new SimpleDoubleProperty();
    private DoubleProperty leadEquivalent = new SimpleDoubleProperty();

    public MatCharacteristicsDataViewModel(MatCharacteristicsDataDto dto) {
        this.info.set(new MatInfoViewModel(dto.getInfo()));
        this.thickness.set(dto.getThickness());
        this.leadEquivalent.set(dto.getLeadEquivalent());
    }

    @Override
    public MatCharacteristicsDataDto toDto() {
        MaterialInfoDataDto dto = new MaterialInfoDataDto(info.get().toDto().getName(), info.get().toDto().getDensity());
        return new MatCharacteristicsDataDto(dto, thickness.get(), leadEquivalent.get());
    }

    @Override
    public void fromDto(AppData dto) {
        MatCharacteristicsDataDto data = (MatCharacteristicsDataDto) dto;
        this.info.set(new MatInfoViewModel(data.getInfo()));
        this.thickness.set(data.getThickness());
        this.leadEquivalent.set(data.getLeadEquivalent());
    }
}
