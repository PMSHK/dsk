package com.xrc.dsk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class KParamDto implements PublisherDto {
    private Double workLoad;
    private Double directionCoefficient;
    private Double distance;
    private Double dmd;
    private Double voltage;
    private Double kerma;
    private boolean changed = Boolean.FALSE;

    public void setWorkLoad(Double workLoad) {
        this.workLoad = workLoad;
        changed = Boolean.TRUE;
    }

    public void setDirectionCoefficient(Double directionCoefficient) {
        this.directionCoefficient = directionCoefficient;
        changed = Boolean.TRUE;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
        changed = Boolean.TRUE;
    }

    public void setDmd(Double dmd) {
        this.dmd = dmd;
        changed = Boolean.TRUE;
    }

    public void setVoltage(Double voltage) {
        this.voltage = voltage;
        changed = Boolean.TRUE;
    }

    public void setKerma(Double kerma) {
        this.kerma = kerma;
        changed = Boolean.TRUE;
    }

    @Override
    public boolean filled() {
        return workLoad != null
                && directionCoefficient != null
                && distance != null
                && dmd != null
                && voltage != null
                && kerma != null;
    }

    @Override
    public boolean changed() {
        if (changed == Boolean.TRUE) {
            changed = false;
            return true;
        }
        return false;
    }
}
