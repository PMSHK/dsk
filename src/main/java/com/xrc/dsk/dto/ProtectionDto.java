package com.xrc.dsk.dto;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor

public class ProtectionDto implements Serializable {
    private DoubleProperty weaknessCoefficient = new SimpleDoubleProperty(0);
    private DoubleProperty leadEqv = new SimpleDoubleProperty(0);

    public DoubleProperty weaknessCoefficientProperty() {
        return weaknessCoefficient;
    }

    public DoubleProperty leadEqvProperty() {
        return leadEqv;
    }

    public void setWeaknessCoefficient(Double weaknessCoefficient) {
        this.weaknessCoefficient.set(weaknessCoefficient);
    }

    public void setLeadEqv(Double leadEqv) {
        this.leadEqv.set(leadEqv);
    }

    public Double getWeaknessCoefficient() {
        return weaknessCoefficient.get();
    }

    public Double getLeadEqv() {
        return leadEqv.get();
    }

}
