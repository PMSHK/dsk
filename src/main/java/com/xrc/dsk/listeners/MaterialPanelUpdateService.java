package com.xrc.dsk.listeners;

import com.google.common.eventbus.Subscribe;
import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.dto.MaterialCharacteristicsDto;
import com.xrc.dsk.dto.PanelDataDto;
import com.xrc.dsk.dto.ProtectionDto;
import com.xrc.dsk.events.EventManager;
import com.xrc.dsk.events.MaterialEvent;
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

    public MaterialPanelUpdateService(PanelDataDto panelDataDto, ComboBox<String> materialComboBox,
                                      TextField ThicknessField, Label leadEquivalentLabel) {
        this.panelDataDto = panelDataDto;
        this.materialComboBox = materialComboBox;
        this.ThicknessField = ThicknessField;
        this.leadEquivalentLabel = leadEquivalentLabel;
        this.connectionService = new ConnectionService();
        EventManager.register(this);
    }

    @Subscribe
    public void onMaterialEvent(MaterialEvent event) {
//        if (event.getVoltage() != null && event.getVoltage()>=50 &&
//                !event.getMaterialCharacteristicsDto().getInfo().getName().isEmpty()){
//            return;
//        }
        log.info("RadiationTypeEvent has been caught: {}", event);
        MaterialCharacteristicsDto dto = event.getMaterialCharacteristicsDto();
        Long voltage = event.getVoltage();
        if (voltage < 50) {
            voltage = 50L;
        }

        double leadEquivalent = connectionService.getMaterialCharacteristics(
                dto.getInfo().getName(), dto.getInfo().getDensity(),
                voltage, dto.getThickness(), 0
        );
        dto.setLeadEquivalent(leadEquivalent);
        ProtectionDto pDto = panelDataDto.getProtectionDto();
        panelDataDto.setProtectionDto(null);
        log.info("Event {} has been caught. updating protectionDto", event);
        panelDataDto.setProtectionDto(pDto);
        log.info("Update protectionDto {}", pDto.toString());
    }
}
