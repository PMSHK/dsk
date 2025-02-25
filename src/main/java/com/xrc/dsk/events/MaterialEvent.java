package com.xrc.dsk.events;

import com.xrc.dsk.dto.MaterialCharacteristicsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MaterialEvent {
    private MaterialCharacteristicsDto materialCharacteristicsDto;
    private Long voltage;
}
