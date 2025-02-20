package com.xrc.dsk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MaterialCharacteristicsDto {
    private String name;
    private double density;
    private double voltage;
    private double thickness;
    private double leadEquivalent;
}
