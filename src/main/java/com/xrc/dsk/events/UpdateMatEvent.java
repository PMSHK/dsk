package com.xrc.dsk.events;

import com.xrc.dsk.dto.MaterialCharacteristicsDto;
import com.xrc.dsk.dto.medicine.MatCharacteristicsDataDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UpdateMatEvent implements Materiable {
    private final MatCharacteristicsDataDto materialCharacteristicsDto;
    private final Long voltage;
    private final Boolean basedOnThickness;
}
