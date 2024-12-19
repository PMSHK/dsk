package com.xrc.dsk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProtectionDto {
    private double weaknessCoefficient;
    private double leadEqv;
}
