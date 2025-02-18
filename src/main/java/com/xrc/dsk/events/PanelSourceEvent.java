package com.xrc.dsk.events;

import com.xrc.dsk.dto.PanelDataDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PanelSourceEvent {
    private final PanelDataDto dto;
    private final Integer panelId;
}
