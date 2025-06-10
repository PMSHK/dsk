package com.xrc.dsk.dto.medicine;

import com.xrc.dsk.data.bin.AppData;
import com.xrc.dsk.dto.Filled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static com.xrc.dsk.settings.AppParameters.MEDICINE;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SourceDataDto implements Serializable, Filled, AppData {
    private Double dmd;
    private Double directionCoefficient;
    private Double distance;

    @Override
    public boolean filled() {
        return dmd > 0 && directionCoefficient > 0 && distance > 0;
    }

    @Override
    public String getType() {
        return MEDICINE;
    }
}
