package com.xrc.dsk.viewModels.medicine;

import com.xrc.dsk.converters.NullChecker;
import com.xrc.dsk.data.bin.AppData;
import com.xrc.dsk.dto.medicine.ProtectionDataDto;
import com.xrc.dsk.viewModels.DataViewModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProtectionDataViewModel extends DataViewModel<ProtectionDataDto> {
    private DoubleProperty weaknessCoefficientProperty;
    private DoubleProperty leadEqvProperty;

    public ProtectionDataViewModel() {
        super();
    }

    public ProtectionDataViewModel(ProtectionDataDto dto) {
        super(dto);
    }

    @Override
    public ProtectionDataDto toDto() {
        return new ProtectionDataDto(
                getWeaknessCoefficient(),
                getLeadEqv());
    }

    @Override
    public void fromDto(AppData dto) {
        ProtectionDataDto data = (ProtectionDataDto) dto;
        this.weaknessCoefficientProperty.set(NullChecker.getValueOrDefault(data.getWeaknessCoefficient(), 0D));
        this.leadEqvProperty.set(NullChecker.getValueOrDefault(data.getLeadEqv(), 0D));
    }

    @Override
    public void init() {
        this.weaknessCoefficientProperty = new SimpleDoubleProperty(0);
        this.leadEqvProperty = new SimpleDoubleProperty(0);
    }

    public Double getWeaknessCoefficient(){
        return NullChecker.getValueOrDefault(weaknessCoefficientProperty.get(), 0D);
    }

    public Double getLeadEqv(){
        return NullChecker.getValueOrDefault(leadEqvProperty.get(), 0D);
    }
}
