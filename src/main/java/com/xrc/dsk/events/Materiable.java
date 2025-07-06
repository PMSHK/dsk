package com.xrc.dsk.events;

import com.xrc.dsk.dto.medicine.MatCharacteristicsDataDto;

public interface Materiable {
    Boolean getBasedOnThickness();

    MatCharacteristicsDataDto getMaterialCharacteristicsDto();

    Long getVoltage();
}
