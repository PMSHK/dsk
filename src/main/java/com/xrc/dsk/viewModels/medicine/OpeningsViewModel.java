package com.xrc.dsk.viewModels.medicine;

import com.xrc.dsk.converters.NullChecker;
import com.xrc.dsk.data.bin.AppData;
import com.xrc.dsk.dto.medicine.OpeningsDataDto;
import com.xrc.dsk.viewModels.DataViewModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpeningsViewModel extends DataViewModel<OpeningsDataDto> {

    private StringProperty nameProperty;
    private DoubleProperty thicknessProperty;

    public OpeningsViewModel() {
        super();
    }

    public OpeningsViewModel(OpeningsDataDto dto) {
        super(dto);
    }

    @Override
    public OpeningsDataDto toDto() {
        return new OpeningsDataDto(
                getName(),
                getThickness());
    }

    @Override
    public void fromDto(AppData dto) {
        OpeningsDataDto data = (OpeningsDataDto) dto;
        this.nameProperty.set(NullChecker.getString(data.getName(),""));
        this.thicknessProperty.set(NullChecker.getValueOrDefault(data.getThickness(),0D));
    }

    @Override
    public void init() {
        this.nameProperty = new SimpleStringProperty();
        this.thicknessProperty = new SimpleDoubleProperty();
    }

    public String getName(){
        return NullChecker.getString(nameProperty.get(),"");
    }
    public Double getThickness(){
        return NullChecker.getValueOrDefault(thicknessProperty.get(),0D);
    }
}
