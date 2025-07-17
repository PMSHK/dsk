package com.xrc.dsk.viewModels.medicine;

import com.xrc.dsk.converters.NullChecker;
import com.xrc.dsk.data.bin.AppData;
import com.xrc.dsk.dto.medicine.OpeningsDataDto;
import com.xrc.dsk.viewModels.DataViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpeningsViewModel extends DataViewModel<OpeningsDataDto> {

    private StringProperty nameProperty;
    private StringProperty thicknessProperty;

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
        this.nameProperty.set(NullChecker.getString(data.getName(), ""));
        this.thicknessProperty.set(NullChecker.getString(data.getThickness(), ""));
    }

    @Override
    public void init() {
        this.nameProperty = new SimpleStringProperty();
        this.thicknessProperty = new SimpleStringProperty();
    }

    public String getName() {
        return NullChecker.getString(nameProperty.get(), "");
    }

    public String getThickness() {
        return NullChecker.getString(thicknessProperty.get(), "");
    }
}
