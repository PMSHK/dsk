package com.xrc.dsk.viewModels.medicine;

import com.xrc.dsk.data.bin.AppData;
import com.xrc.dsk.dto.medicine.ProtectionDataDto;
import com.xrc.dsk.viewModels.DataViewModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProtectionDataViewModel implements DataViewModel<ProtectionDataDto> {
    private DoubleProperty weaknessCoefficient = new SimpleDoubleProperty(0);
    private DoubleProperty leadEqv = new SimpleDoubleProperty(0);

    ProtectionDataViewModel(ProtectionDataDto dto) {
        weaknessCoefficient.set(dto.getWeaknessCoefficient());
        leadEqv.set(dto.getLeadEqv());
    }

    @Override
    public ProtectionDataDto toDto() {
        return new ProtectionDataDto(weaknessCoefficient.get(), leadEqv.get());
    }

    @Override
    public void fromDto(AppData dto) {
        this.weaknessCoefficient.set(((ProtectionDataDto) dto).getWeaknessCoefficient());
        this.leadEqv.set(((ProtectionDataDto) dto).getLeadEqv());
    }
}
