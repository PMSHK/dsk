package com.xrc.dsk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class KParamDto implements Filled {
    private Double workLoad;
    private Double directionCoefficient;
    private Double distance;
    private Double dmd;
    private Double voltage;
    private Double kerma;

    public void setWorkLoad(Double workLoad) {
        this.workLoad = workLoad;
    }

    public void setDirectionCoefficient(Double directionCoefficient) {
        this.directionCoefficient = directionCoefficient;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public void setDmd(Double dmd) {
        this.dmd = dmd;
    }

    public void setVoltage(Double voltage) {
        this.voltage = voltage;
    }

    public void setKerma(Double kerma) {
        this.kerma = kerma;
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
}
