package com.xrc.dsk.listeners;

import com.google.common.eventbus.Subscribe;
import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.dto.MaterialCharacteristicsDto;
import com.xrc.dsk.dto.PanelDataDto;
import com.xrc.dsk.dto.ProtectionDto;
import com.xrc.dsk.events.EventManager;
import com.xrc.dsk.events.Materiable;
import com.xrc.dsk.events.MaterialEvent;
import com.xrc.dsk.events.UpdateMatEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MaterialPanelUpdateService {
    private final PanelDataDto panelDataDto;
    private final ComboBox<String> materialComboBox;
    private final TextField ThicknessField;
    private final Label leadEquivalentLabel;
    private ConnectionService connectionService;
    private Boolean basedOnThickness;

    public MaterialPanelUpdateService(PanelDataDto panelDataDto, ComboBox<String> materialComboBox,
                                      TextField ThicknessField, Label leadEquivalentLabel, boolean basedOnThickness) {
        this.panelDataDto = panelDataDto;
        this.materialComboBox = materialComboBox;
        this.ThicknessField = ThicknessField;
        this.leadEquivalentLabel = leadEquivalentLabel;
        this.connectionService = new ConnectionService();
        this.basedOnThickness = basedOnThickness;
        EventManager.register(this);
    }

    @Subscribe
    public void onMaterialEvent(MaterialEvent event) {
        double result = init(event);
        if (result == -1) {
            return;
        }
        MaterialCharacteristicsDto dto = event.getMaterialCharacteristicsDto();
        dto.setLeadEquivalent(result);
        ProtectionDto pDto = panelDataDto.getProtectionDto();
        panelDataDto.setProtectionDto(null);
        log.info("Event {} has been caught. updating protectionDto", event);
        panelDataDto.setProtectionDto(pDto);
        log.info("Update protectionDto {}", pDto.toString());
    }

    @Subscribe
    public void onMaterialUpdateEvent(UpdateMatEvent event) {
        double result = init(event);
        if (result == -1) {
            return;
        }
        MaterialCharacteristicsDto dto = event.getMaterialCharacteristicsDto();
        if (dto.getThickness() == 0) {
            dto.setThickness(result);
            dto.thicknessProperty().set(result);
        }
    }

    private double init(Materiable event) {

        log.info("got correct event for {}", this.toString());
        log.info("Material event has been caught: {}", event);
        MaterialCharacteristicsDto dto = event.getMaterialCharacteristicsDto();
        Long voltage = event.getVoltage();
        if (voltage < 50) {
            voltage = 50L;
        }

        double result = connectionService.getMaterialCharacteristics(
                dto.getInfo().getName(), dto.getInfo().getDensity(),
                voltage, dto.getThickness(), dto.getLeadEquivalent()
        );
        log.info("result: {}, voltage: {}, material: {}, thickness: {}, leadEquivalent: {}", result, voltage, dto.getInfo().getName() + " " + dto.getInfo().getDensity(), dto.getThickness(), dto.getLeadEquivalent());
        return result;
    }
}
