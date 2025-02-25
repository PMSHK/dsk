package com.xrc.dsk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MaterialDto {
    private String name;
    private double density;
    private double voltage;
    private double thickness;
    private double leadEquivalent;
}
