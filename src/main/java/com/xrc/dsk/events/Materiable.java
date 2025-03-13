package com.xrc.dsk.events;

import com.xrc.dsk.dto.MaterialCharacteristicsDto;

public interface Materiable {
    Boolean getBasedOnThickness();
    MaterialCharacteristicsDto getMaterialCharacteristicsDto();
    Long getVoltage();
}
