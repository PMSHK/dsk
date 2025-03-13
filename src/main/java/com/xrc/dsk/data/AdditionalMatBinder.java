package com.xrc.dsk.data;

import com.xrc.dsk.dto.MaterialCharacteristicsDto;
import com.xrc.dsk.dto.MedWindowDto;
import com.xrc.dsk.dto.PanelDataDto;
import com.xrc.dsk.dto.WindowDto;
import com.xrc.dsk.events.AdditionalMatEvent;
import com.xrc.dsk.events.EventManager;
import com.xrc.dsk.listeners.AdditionalMatUpdateService;
import javafx.scene.control.Label;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdditionalMatBinder implements Bindable {
    private final Binder binder;
    private WindowDto window;
    private Label additionalMatLeadLabel;
    private int panelId;
    private MedWindowDto medWindowDto;

    public AdditionalMatBinder(
            Label additionalMatLeadLabel,
            int panelId) {
        this.binder = new Binder();
        this.additionalMatLeadLabel = additionalMatLeadLabel;
        this.panelId = panelId;
        log.info("addMatBinder has been created");
    }

    @Override
    public void bind(WindowDto dto) {
        log.info("Starting binding for additional material data");
        this.medWindowDto = (MedWindowDto) dto;
        PanelDataDto panelDataDto = medWindowDto.getPanelData().get(panelId);
        AdditionalMatUpdateService service = new AdditionalMatUpdateService(additionalMatLeadLabel, panelDataDto, panelId);
        panelDataDto.existedMaterialDtoListProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                Double leadEquivalent = panelDataDto.getExistedMaterialCharacteristicsDtoList().stream().
                        map(MaterialCharacteristicsDto::getLeadEquivalent).reduce(0d, Double::sum);
                AdditionalMatEvent event = new AdditionalMatEvent(panelDataDto.getProtectionDto().getLeadEqv(), leadEquivalent, panelId);
                log.info("Adding additional material characteristics to panel {}", panelId);
                EventManager.post(event);
            }

        });


        panelDataDto.protectionDtoProperty().addListener((obs, oldVal, newVal) -> {
            if (oldVal != null) {
                log.info("Removing additional material characteristics to panel {}", panelId);
            }
            if (newVal != null) {
                Double leadEquivalent = panelDataDto.getExistedMaterialCharacteristicsDtoList().stream().
                        map(MaterialCharacteristicsDto::getLeadEquivalent).reduce(0d, Double::sum);
                AdditionalMatEvent event = new AdditionalMatEvent(panelDataDto.getProtectionDto().getLeadEqv(), leadEquivalent, panelId);
                log.info("Adding additional material characteristics to panel {}", panelId);
                EventManager.post(event);
            }
        });

    }
}
