package com.xrc.dsk.viewModels.medicine;

import com.xrc.dsk.data.bin.AppData;
import com.xrc.dsk.dto.Filled;
import com.xrc.dsk.dto.medicine.MedicineDataDto;
import com.xrc.dsk.dto.medicine.RadTypeDataDto;
import com.xrc.dsk.viewModels.DataViewModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RadTypeDataViewModel implements Filled, DataViewModel<RadTypeDataDto> {

    private StringProperty name = new SimpleStringProperty();
    private LongProperty voltage = new SimpleLongProperty();
    private DoubleProperty radiationExit = new SimpleDoubleProperty();
    private LongProperty workload = new SimpleLongProperty();

    @Override
    public RadTypeDataDto toDto() {
        return new RadTypeDataDto(name.get(), voltage.get(), radiationExit.get(), workload.get());
    }

    @Override
    public void fromDto(AppData dto) {
        RadTypeDataDto data = (RadTypeDataDto) dto;
        name.set(data.getName());
        voltage.set(data.getVoltage());
        radiationExit.set(data.getRadiationExit());
        workload.set(data.getWorkload());
    }

    RadTypeDataViewModel(MedicineDataDto data) {
        this.name.set(data.getRadiationTypeDto().getName());
        this.voltage.set(data.getRadiationTypeDto().getVoltage());
        this.radiationExit.set(data.getRadiationTypeDto().getRadiationExit());
        this.workload.set(data.getRadiationTypeDto().getWorkload());
    }

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

    @Override
    public boolean filled() {
        return voltage.get() != 0 && radiationExit.get() != 0 && workload.get() != 0;
    }
}
