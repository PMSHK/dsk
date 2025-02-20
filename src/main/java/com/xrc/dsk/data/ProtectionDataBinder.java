package com.xrc.dsk.data;

import com.xrc.dsk.dto.MedWindowDto;
import com.xrc.dsk.dto.PanelDataDto;
import com.xrc.dsk.dto.WindowDto;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Label;

public class ProtectionDataBinder implements Bindable{
    private final Binder binder;
    private MedWindowDto medWindowDto;
    private Integer panelId;
    private PanelDataDto panelDataDto;
    private Label attenuationLabel;
    private Label leadLabel;

    public ProtectionDataBinder() {
        this.binder = new Binder();
    }

    public ProtectionDataBinder(Label attenuationFrequency, Label leadEquivalent, int panelId) {
        this.binder = new Binder();
        this.panelId = panelId;
        this.attenuationLabel = attenuationFrequency;
        this.leadLabel = leadEquivalent;
    }
@Override
    public void bind(WindowDto dto) {
        medWindowDto = (MedWindowDto) dto;
        this.panelDataDto = medWindowDto.getPanelDataProperty().get(panelId);

        leadLabel.textProperty().bind(Bindings.createStringBinding(
                () -> panelDataDto.getProtectionDto() != null ? String.format("%.2f", panelDataDto.getProtectionDto().leadEqvProperty().get()) : "0.0",
                panelDataDto.protectionDtoProperty()
        ));

        attenuationLabel.textProperty().bind(Bindings.createStringBinding(
                () -> panelDataDto.getProtectionDto() != null ? String.format("%.2f", panelDataDto.getProtectionDto().weaknessCoefficientProperty().get()) : "0.0",
                panelDataDto.protectionDtoProperty()
        ));

    }
}
