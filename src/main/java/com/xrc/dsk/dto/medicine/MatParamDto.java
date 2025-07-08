package com.xrc.dsk.dto.medicine;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class MatParamDto {
    private double thickness;
    private double leadEquivalent;

    public MatParamDto() {
        thickness = 0.0;
        leadEquivalent = 0.0;
    }

    public MatParamDto(double thickness, double leadEquivalent) {
        this.thickness = thickness;
        this.leadEquivalent = leadEquivalent;
    }

    public boolean isThicknessBased() {
        return thickness > 0 && leadEquivalent == 0;
    }
}
