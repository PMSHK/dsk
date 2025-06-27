package com.xrc.dsk.events;

import com.xrc.dsk.dto.RadiationTypeDto;
import com.xrc.dsk.dto.medicine.RadTypeDataDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class RadiationTypeEvent {
    private final RadTypeDataDto radiationType;


}
