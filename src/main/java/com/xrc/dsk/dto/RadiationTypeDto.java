package com.xrc.dsk.dto;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor

public class RadiationTypeDto implements Serializable {
    private StringProperty name = new SimpleStringProperty();
    private LongProperty voltage = new SimpleLongProperty();
    private DoubleProperty radiationExit = new SimpleDoubleProperty();
    private LongProperty workload = new SimpleLongProperty();

    public void setName(String name) {
        this.name.set(name);
    }

    public void setVoltage(Long voltage) {
        this.voltage.set(voltage);
    }

    public void setRadiationExit(Double radiationExit) {
        this.radiationExit.set(radiationExit);
    }

    public void setWorkload(Long workLoad) {
        this.workload.set(workLoad);
    }

    public String getName() {
        return name.get();
    }

    public Long getVoltage() {
        return voltage.get();
    }

    public Double getRadiationExit() {
        return radiationExit.get();
    }

    public Long getWorkload() {
        return workload.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public LongProperty voltageProperty() {
        return voltage;
    }

    public DoubleProperty radiationExitProperty() {
        return radiationExit;
    }

    public LongProperty workloadProperty() {
        return workload;
    }
}
