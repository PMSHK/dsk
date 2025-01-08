package com.xrc.dsk.dto;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class MedWindowDto extends WindowDto {
    private ObjectProperty<RadiationTypeDto> radiationType = new SimpleObjectProperty<>();
    private ListProperty<PanelDataDto> panelData = new SimpleListProperty<>(FXCollections.observableArrayList());

    public void setRadiationType(RadiationTypeDto radiationType) {
        this.radiationType.set(radiationType);
    }

    public void setPanelData(ObservableList<PanelDataDto> panelData) {
        this.panelData.set(panelData);
    }

    public ObjectProperty<RadiationTypeDto> getRadiationTypeProperty() {
        return radiationType;
    }

    public ListProperty<PanelDataDto> getPanelDataProperty() {
        return panelData;
    }

    public RadiationTypeDto getRadiationType() {
        if (radiationType.get()== null){
            radiationType.set(new RadiationTypeDto());
        }
        return radiationType.get();
    }

    public ObservableList<PanelDataDto> getPanelData() {
        return panelData.get();
    }
}
