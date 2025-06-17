package com.xrc.dsk.viewModels.medicine;

import com.xrc.dsk.converters.NullChecker;
import com.xrc.dsk.data.bin.AppData;
import com.xrc.dsk.dto.medicine.MaterialInfoDataDto;
import com.xrc.dsk.viewModels.DataViewModel;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatInfoViewModel extends DataViewModel<MaterialInfoDataDto> {

    private StringProperty nameProperty;
    private FloatProperty densityProperty;

    public MatInfoViewModel() {
        super();
    }

    public MatInfoViewModel(MaterialInfoDataDto dto) {
        super(dto);
    }

    public String getName(){
        return NullChecker.getString(nameProperty.get(), "");
    }
    public Float getDensity(){
        return NullChecker.getValueOrDefault(densityProperty.get(), 0F);
    }

    @Override
    public MaterialInfoDataDto toDto() {
        return new MaterialInfoDataDto(
                getName(),
                getDensity());
    }

    @Override
    public void fromDto(AppData dto) {
        MaterialInfoDataDto data = (MaterialInfoDataDto) dto;
        this.nameProperty.set(NullChecker.getValueOrDefault(data.getName(), ""));
        this.densityProperty.set(NullChecker.getValueOrDefault(data.getDensity(), 0F));
    }

    @Override
    public void init() {
        this.nameProperty = new SimpleStringProperty();
        this.densityProperty = new SimpleFloatProperty();
    }
}
