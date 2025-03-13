package com.xrc.dsk.events;

import com.xrc.dsk.dto.MaterialCharacteristicsDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UpdateMatEvent implements Materiable {
    private final MaterialCharacteristicsDto materialCharacteristicsDto;
    private final Long voltage;
    private final Boolean basedOnThickness;
}
