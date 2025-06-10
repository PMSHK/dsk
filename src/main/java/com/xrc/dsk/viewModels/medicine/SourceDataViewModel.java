package com.xrc.dsk.viewModels.medicine;

import com.xrc.dsk.data.bin.AppData;
import com.xrc.dsk.dto.medicine.SourceDataDto;
import com.xrc.dsk.viewModels.DataViewModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SourceDataViewModel implements DataViewModel<SourceDataDto> {

    private DoubleProperty dmd = new SimpleDoubleProperty();
    private DoubleProperty directionCoefficient = new SimpleDoubleProperty();
    private DoubleProperty distance = new SimpleDoubleProperty();

    public SourceDataViewModel(SourceDataDto dto) {
        this.dmd.set(dto.getDmd());
        this.directionCoefficient.set(dto.getDirectionCoefficient());
        this.distance.set(dto.getDistance());
    }

    @Override
    public SourceDataDto toDto() {
        return new SourceDataDto(dmd.get(), directionCoefficient.get(), distance.get());
    }

    @Override
    public void fromDto(AppData dto) {
        SourceDataDto data = (SourceDataDto) dto;
        this.dmd.set(data.getDmd());
        this.directionCoefficient.set(data.getDirectionCoefficient());
        this.distance.set(data.getDistance());
    }
}
