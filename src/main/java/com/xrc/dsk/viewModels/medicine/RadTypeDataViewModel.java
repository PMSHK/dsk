package com.xrc.dsk.viewModels.medicine;

import com.xrc.dsk.converters.NullChecker;
import com.xrc.dsk.data.bin.AppData;
import com.xrc.dsk.dto.Filled;
import com.xrc.dsk.dto.medicine.RadTypeDataDto;
import com.xrc.dsk.viewModels.DataViewModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RadTypeDataViewModel extends DataViewModel<RadTypeDataDto> implements Filled {

    private StringProperty nameProperty;
    private LongProperty voltageProperty;
    private DoubleProperty radiationExitProperty;
    private LongProperty workloadProperty;

    public RadTypeDataViewModel() {
        super();
    }

    public RadTypeDataViewModel(RadTypeDataDto dto) {
        super(dto);
    }

    @Override
    public RadTypeDataDto toDto() {
        return new RadTypeDataDto(
                getName(),
                getVoltage(),
                getRadiationExit(),
                getWorkload());
    }

    @Override
    public void fromDto(AppData dto) {
        RadTypeDataDto data = (RadTypeDataDto) dto;
        nameProperty.set(NullChecker.getString(data.getName(),""));
        voltageProperty.set(NullChecker.getValueOrDefault(data.getVoltage(),0L));
        radiationExitProperty.set(NullChecker.getValueOrDefault(data.getRadiationExit(),0D));
        workloadProperty.set(NullChecker.getValueOrDefault(data.getWorkload(),0L));
    }

    @Override
    public boolean filled() {
        return voltageProperty.get() != 0 && radiationExitProperty.get() != 0 && workloadProperty.get() != 0;
    }

    @Override
    public void init() {
        this.nameProperty = new SimpleStringProperty();
        this.voltageProperty = new SimpleLongProperty();
        this.radiationExitProperty = new SimpleDoubleProperty();
        this.workloadProperty = new SimpleLongProperty();
    }

    public String getName(){
        return NullChecker.getString(nameProperty.get(),"");
    }
    public Long getVoltage(){
        return NullChecker.getValueOrDefault(voltageProperty.get(),0L);
    }
    public Double getRadiationExit(){
        return NullChecker.getValueOrDefault(radiationExitProperty.get(),0D);
    }
    public Long getWorkload(){
        return NullChecker.getValueOrDefault(workloadProperty.get(),0L);
    }
}
