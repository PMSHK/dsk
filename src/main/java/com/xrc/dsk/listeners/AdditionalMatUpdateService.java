package com.xrc.dsk.listeners;

import com.google.common.eventbus.Subscribe;
import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.dto.medicine.PanelDataDto;
import com.xrc.dsk.events.AdditionalMatEvent;
import com.xrc.dsk.events.EventManager;
import javafx.scene.control.Label;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class AdditionalMatUpdateService {
    private final Label leadEquivalentLabel;
    private final PanelDataDto panelDataDto;
    private final ConnectionService connectionService;
    private final Integer panelId;

    public AdditionalMatUpdateService(Label label, PanelDataDto panelDataDto, Integer panelId) {
        this.leadEquivalentLabel = label;
        this.panelDataDto = panelDataDto;
        this.panelId = panelId;
        this.connectionService = new ConnectionService();
        EventManager.register(this);
        log.info("Initializing AdditionalMatUpdateService");
    }

    @Subscribe
    public void onAdditionalEvent(AdditionalMatEvent additionalMatEvent) {
        if (!Objects.equals(this.panelId, additionalMatEvent.getPanelId())) {
            return;
        }
        log.info("Received Additional MatEvent: " + additionalMatEvent);
        String demandedLeadEquivalent = connectionService.getAdditionalProtection(additionalMatEvent.getDemandedEquivalent(), additionalMatEvent.getExistedEquivalent());

        try {
            double leadEquivalent = Double.parseDouble(demandedLeadEquivalent);
            String formattedLeadEquivalent = String.format("%.2f", leadEquivalent);
            additionalMatEvent.getViewModel().getAdditionalLeadViewModelProperty().set(formattedLeadEquivalent);
            leadEquivalentLabel.setText(formattedLeadEquivalent);
        } catch (NumberFormatException e) {
            additionalMatEvent.getViewModel().getAdditionalLeadViewModelProperty().set(demandedLeadEquivalent);
            leadEquivalentLabel.setText(demandedLeadEquivalent);
        }
    }
}
