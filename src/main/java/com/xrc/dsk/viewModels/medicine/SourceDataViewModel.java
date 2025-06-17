package com.xrc.dsk.viewModels.medicine;

import com.xrc.dsk.converters.NullChecker;
import com.xrc.dsk.data.bin.AppData;
import com.xrc.dsk.dto.medicine.SourceDataDto;
import com.xrc.dsk.viewModels.DataViewModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SourceDataViewModel extends DataViewModel<SourceDataDto> {

    private DoubleProperty dmdProperty;
    private DoubleProperty directionCoefficientProperty;
    private DoubleProperty distanceProperty;

    public SourceDataViewModel() {
        super();
    }

    public SourceDataViewModel(SourceDataDto dto) {
        super(dto);
    }

    @Override
    public SourceDataDto toDto() {
        return new SourceDataDto(
                getDmd(),
                getDirectionCoefficient(),
                getDistance());
    }

    @Override
    public void fromDto(AppData dto) {
        SourceDataDto data = (SourceDataDto) dto;
        this.dmdProperty.set(NullChecker.getValueOrDefault(data.getDmd(), 0D));
        this.directionCoefficientProperty.set(NullChecker.getValueOrDefault(data.getDirectionCoefficient(), 0D));
        this.distanceProperty.set(NullChecker.getValueOrDefault(data.getDistance(), 0D));
    }

    @Override
    public void init() {
        this.dmdProperty = new SimpleDoubleProperty();
        this.directionCoefficientProperty = new SimpleDoubleProperty();
        this.distanceProperty = new SimpleDoubleProperty();
    }

    public Double getDmd() {
        return NullChecker.getValueOrDefault(dmdProperty.get(), 0D);
    }

    public Double getDirectionCoefficient() {
        return NullChecker.getValueOrDefault(directionCoefficientProperty.get(), 0D);
    }

    public Double getDistance() {
        return NullChecker.getValueOrDefault(distanceProperty.get(), 0D);
    }
}
