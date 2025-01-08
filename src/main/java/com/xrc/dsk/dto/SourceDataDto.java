package com.xrc.dsk.dto;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor

public class SourceDataDto implements Serializable {
    private DoubleProperty dmd = new SimpleDoubleProperty();
    private DoubleProperty directionCoefficient = new SimpleDoubleProperty();
    private DoubleProperty distance = new SimpleDoubleProperty();

    public DoubleProperty dmdProperty() {
        return dmd;
    }

    public DoubleProperty directionCoefficientProperty() {
        return directionCoefficient;
    }

    public DoubleProperty distanceProperty() {
        return distance;
    }

    public void setDmd(double dmd) {
        this.dmd.set(dmd);
    }

    public void setDirectionCoefficient(double directionCoefficient) {
        this.directionCoefficient.set(directionCoefficient);
    }

    public void setDistance(double distance) {
        this.distance.set(distance);
    }

    public double getDmd() {
        return dmd.get();
    }

    public double getDirectionCoefficient() {
        return directionCoefficient.get();
    }

    public double getDistance() {
        return distance.get();
    }
}
