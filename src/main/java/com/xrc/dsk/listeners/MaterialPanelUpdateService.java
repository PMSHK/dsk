package com.xrc.dsk.listeners;

import com.google.common.eventbus.Subscribe;
import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.dto.MaterialDto;
import com.xrc.dsk.dto.MaterialCharacteristicsDto;
import com.xrc.dsk.events.EventManager;
import com.xrc.dsk.events.MaterialEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MaterialPanelUpdateService {
    private final int panelId;
    private final ComboBox<String> materialComboBox;
    private final TextField ThicknessField;
    private final Label leadEquivalentLabel;
    private ConnectionService connectionService;

    public MaterialPanelUpdateService(int panelId, ComboBox<String> materialComboBox,
                                      TextField ThicknessField, Label leadEquivalentLabel) {
        this.panelId = panelId;
        this.materialComboBox = materialComboBox;
        this.ThicknessField = ThicknessField;
        this.leadEquivalentLabel = leadEquivalentLabel;
        this.connectionService = new ConnectionService();
        EventManager.register(this);
    }

    @Subscribe
    public void onMaterialEvent(MaterialEvent event) {
        log.info("RadiationTypeEvent has been caught: {}", event);
        MaterialCharacteristicsDto dto = event.getMaterialCharacteristicsDto();
        Long voltage = event.getVoltage();
        if(voltage < 50){
            voltage = 50L;
        }

        double leadEquivalent = connectionService.getMaterialCharacteristics(
                dto.getInfo().getName() , dto.getInfo().getDensity(),
                voltage, dto.getThickness(), 0
        );
        dto.setLeadEquivalent(leadEquivalent);
    }
}
