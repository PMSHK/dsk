package com.xrc.dsk.dto;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor

public class MaterialCharacteristicsDto implements Serializable {
    private ObjectProperty<MaterialInfoDto> info = new SimpleObjectProperty<>();
    private DoubleProperty thickness = new SimpleDoubleProperty();
    private DoubleProperty leadEquivalent = new SimpleDoubleProperty();

    public ObjectProperty<MaterialInfoDto> infoProperty() {
        return info;
    }

    public DoubleProperty thicknessProperty() {
        return thickness;
    }

    public DoubleProperty leadEquivalentProperty() {
        return leadEquivalent;
    }

    public MaterialInfoDto getInfo() {
        return info.get();
    }

    public void setInfo(MaterialInfoDto info) {
        this.info.set(info);
    }

    public double getThickness() {
        return thickness.get();
    }

    public void setThickness(double thickness) {
        this.thickness.set(thickness);
    }

    public double getLeadEquivalent() {
        return leadEquivalent.get();
    }

    public void setLeadEquivalent(double leadEquivalent) {
        this.leadEquivalent.set(leadEquivalent);
    }
}
