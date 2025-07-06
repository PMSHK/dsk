package com.xrc.dsk.viewModels.medicine;

import com.xrc.dsk.converters.NullChecker;
import com.xrc.dsk.data.bin.AppData;
import com.xrc.dsk.dto.medicine.MatCharacteristicsDataDto;
import com.xrc.dsk.dto.medicine.MaterialInfoDataDto;
import com.xrc.dsk.viewModels.DataViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatCharacteristicsDataViewModel extends DataViewModel<MatCharacteristicsDataDto> {

    private ObjectProperty<MatInfoViewModel> infoProperty;
    private DoubleProperty thicknessProperty;
    private DoubleProperty leadEquivalentProperty;

    public MatCharacteristicsDataViewModel() {
        super();
    }

    public MatCharacteristicsDataViewModel(MatCharacteristicsDataDto dto) {
        super(dto);
    }

    @Override
    public MatCharacteristicsDataDto toDto() {
        MaterialInfoDataDto dto = NullChecker.getDtoOrDefault(infoProperty, MatInfoViewModel::toDto, new MaterialInfoDataDto());
        return new MatCharacteristicsDataDto(dto, getThickness(), getLeadEquivalent());
    }

    @Override
    public void fromDto(AppData dto) {
        MatCharacteristicsDataDto data = (MatCharacteristicsDataDto) dto;
        this.infoProperty.set(NullChecker.getValueOrDefault(data.getInfo(), MatInfoViewModel::new, new MatInfoViewModel()));
        this.thicknessProperty.set(NullChecker.getValueOrDefault(data.getThickness(), 0d));
        this.leadEquivalentProperty.set(NullChecker.getValueOrDefault(data.getLeadEquivalent(), 0d));
    }

    @Override
    public void init() {
        this.infoProperty = new SimpleObjectProperty<>();
        this.thicknessProperty = new SimpleDoubleProperty(0.0);
        this.leadEquivalentProperty = new SimpleDoubleProperty(0.0);
    }

    public MatInfoViewModel getInfo() {
        return NullChecker.getValueOrSetDefault(infoProperty, new MatInfoViewModel());
    }

    public Double getThickness() {
        return NullChecker.getValueOrSetDefault(thicknessProperty, 0.0).doubleValue();
    }

    public Double getLeadEquivalent() {
        return NullChecker.getValueOrSetDefault(leadEquivalentProperty, 0.0).doubleValue();
    }
}
