package com.xrc.dsk.dto.medicine;

import com.xrc.dsk.data.bin.AppData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static com.xrc.dsk.settings.AppParameters.MEDICINE;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProtectionDataDto implements Serializable, AppData {
    private Double weaknessCoefficient;
    private Double leadEqv;

    @Override
    public String getType() {
        return MEDICINE;
    }
}
