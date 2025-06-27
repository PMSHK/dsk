package com.xrc.dsk.viewModels.medicine;

import com.xrc.dsk.converters.FX;
import com.xrc.dsk.converters.NullChecker;
import com.xrc.dsk.data.bin.AppData;
import com.xrc.dsk.dto.medicine.MedicineDataDto;
import com.xrc.dsk.dto.medicine.PanelDataDto;
import com.xrc.dsk.dto.medicine.RadTypeDataDto;
import com.xrc.dsk.viewModels.DataViewModel;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MedicineDataViewModel extends DataViewModel<MedicineDataDto> {

    private ObjectProperty<RadTypeDataViewModel> radiationTypeProperty;
    private ListProperty<PanelDataViewModel> panelDataProperty;

    public MedicineDataViewModel() {
        super();
    }

    public MedicineDataViewModel(MedicineDataDto dto) {
        super(dto);
    }

    @Override
    public MedicineDataDto toDto() {
        List<PanelDataDto> panelDataDtoList = NullChecker.getList(panelDataProperty, PanelDataViewModel::toDto);
        RadTypeDataDto dto = NullChecker.getDtoOrDefault(radiationTypeProperty,RadTypeDataViewModel::toDto,new RadTypeDataDto());
        return new MedicineDataDto(dto, panelDataDtoList);
    }

    @Override
    public void fromDto(AppData dto) {
        MedicineDataDto data = (MedicineDataDto) dto;
        this.radiationTypeProperty.set(NullChecker.getValueOrDefault(
                data.getRadiationTypeDto(),
                RadTypeDataViewModel::new,
                new RadTypeDataViewModel())
        );
        FX.updateList(panelDataProperty, data.getPanelDataDtoList(), PanelDataViewModel::new);
    }

    @Override
    public void init() {
        this.radiationTypeProperty = new SimpleObjectProperty<>();
        this.panelDataProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
    }
    public RadTypeDataViewModel getRadiationTypeViewModel() {
        return NullChecker.getValueOrDefault(radiationTypeProperty,new RadTypeDataViewModel());
    }
    public ObservableList<PanelDataViewModel> getPanelDataViewModelList() {
        return NullChecker.getObservableList(panelDataProperty);
    }
}
