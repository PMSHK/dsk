package com.xrc.dsk.listeners;

import com.google.common.eventbus.Subscribe;
import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.events.AdditionalMatEvent;
import com.xrc.dsk.events.EventManager;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdditionalMatUpdateService {
    private Label leadEquivalentLabel;
    private StringProperty leadEquivalentProperty;
    private ConnectionService connectionService;

    public AdditionalMatUpdateService(Label label, StringProperty leadEquivalentProperty) {
        this.leadEquivalentLabel = label;
        this.leadEquivalentProperty = leadEquivalentProperty;
        EventManager.register(this);
        log.info("Initializing AdditionalMatUpdateService");
    }

    @Subscribe
    public void onAdditionalEvent(AdditionalMatEvent additionalMatEvent) {
        log.info("Received Additional MatEvent: " + additionalMatEvent);
        connectionService = new ConnectionService();
        String demandedLeadEquivalent = connectionService.getAdditionalProtection(additionalMatEvent.getDemandedEquivalent(), additionalMatEvent.getExistedEquivalent());
        leadEquivalentLabel.setText(demandedLeadEquivalent);
        leadEquivalentProperty.set(demandedLeadEquivalent);
    }
}
