package com.xrc.dsk.events;

import com.xrc.dsk.dto.medicine.MatCharacteristicsDataDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MatThicknessLeadEvent {
    private final MatCharacteristicsDataDto dto;
    private final double voltage;
    private final boolean isThicknessBased;
}
