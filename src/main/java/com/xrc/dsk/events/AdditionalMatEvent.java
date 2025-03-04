package com.xrc.dsk.events;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AdditionalMatEvent {
    private Double demandedEquivalent;
    private Double existedEquivalent;
}
