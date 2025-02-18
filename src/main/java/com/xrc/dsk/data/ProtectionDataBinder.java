package com.xrc.dsk.data;

import com.xrc.dsk.dto.MedWindowDto;
import com.xrc.dsk.dto.PanelDataDto;
import com.xrc.dsk.dto.WindowDto;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Label;

import java.util.Map;

import static com.xrc.dsk.data.AppParameters.ATTENUATION_FREQUENCY;
import static com.xrc.dsk.data.AppParameters.LEAD_EQUIVALENT;

public class ProtectionDataBinder implements Bindable {
    private final Binder binder;
    private MedWindowDto medWindowDto;
    private Integer panelId;
    private PanelDataDto panelDataDto;

    public ProtectionDataBinder() {
        this.binder = new Binder();
    }

    @Override
    public void bind(WindowDto dto, Map<String, Object[]> components) {
        medWindowDto = (MedWindowDto) dto;
        this.panelId = (Integer) components.get(LEAD_EQUIVALENT)[1];
        this.panelDataDto = medWindowDto.getPanelDataProperty().get(panelId);
        Label attenuationFrequencyLabel = (Label) components.get(ATTENUATION_FREQUENCY)[0];
        Label leadEquivalenLabel = (Label) components.get(LEAD_EQUIVALENT)[0];

        leadEquivalenLabel.textProperty().bind(Bindings.createStringBinding(
                ()-> panelDataDto.getProtectionDto() != null ? String.format("%.2f", panelDataDto.getProtectionDto().leadEqvProperty().get()):"0.0",
                panelDataDto.protectionDtoProperty()
        ));

        attenuationFrequencyLabel.textProperty().bind(Bindings.createStringBinding(
                ()-> panelDataDto.getProtectionDto()!=null? String.format("%.2f", panelDataDto.getProtectionDto().weaknessCoefficientProperty().get()):"0.0",
                panelDataDto.protectionDtoProperty()
        ));

    }
}
