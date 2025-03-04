package com.xrc.dsk.listeners;

import com.google.common.eventbus.Subscribe;
import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.dto.KParamDto;
import com.xrc.dsk.dto.PanelDataDto;
import com.xrc.dsk.dto.ProtectionDto;
import com.xrc.dsk.dto.RadiationTypeDto;
import com.xrc.dsk.events.EventManager;
import com.xrc.dsk.events.PanelSourceEvent;
import com.xrc.dsk.events.RadiationTypeEvent;

import java.util.ArrayList;
import java.util.List;

public class PanelProtectionUpdateService {
//    private final EventManager eventManager;
    private final List<PanelDataDto> panels;
    private final RadiationTypeDto radiationType;
    private ConnectionService connectionService;

    public PanelProtectionUpdateService() {
        panels = new ArrayList<>();
        panels.add(new PanelDataDto());
        radiationType = new RadiationTypeDto();
        EventManager.register(this);
    }

    public PanelProtectionUpdateService(PanelDataDto panel, RadiationTypeDto radiationType){
        this.panels = new ArrayList<PanelDataDto>();
        this.panels.add(panel);
        this.radiationType = radiationType;
        EventManager.register(this);
    }

    public PanelProtectionUpdateService(List<PanelDataDto> panels, RadiationTypeDto radiationType){
        this.panels = panels;
        this.radiationType = radiationType;
        RadiationTypeEvent event = new RadiationTypeEvent(radiationType);
        EventManager.register(this);
    }

    public void addPanel(PanelDataDto panel){
        panels.add(panel);
    }

    @Subscribe
    public void onRadiationTypeChanged(RadiationTypeEvent event) {
        System.out.println("RadiationTypeEvent has been caught: " + event);
        this.connectionService = new ConnectionService();
        for (PanelDataDto panel : panels) {
            if (panel.filled() && radiationType.filled()) {
                KParamDto dto = new KParamDto();
                dto.setDmd(panel.getSourceDataDto().getDmd());
                dto.setDistance(panel.getSourceDataDto().getDistance());
                dto.setDirectionCoefficient(panel.getSourceDataDto().getDirectionCoefficient());
                dto.setVoltage(event.getRadiationType().getVoltage().doubleValue());
                dto.setKerma(event.getRadiationType().getRadiationExit());
                dto.setWorkLoad(event.getRadiationType().getWorkload().doubleValue());

                ProtectionDto protectionDto = connectionService.getDemandedLeadEquivalent(dto);
                panel.setProtectionDto(protectionDto);
            }
        }
    }
    @Subscribe
    public void onPanelChanged(PanelSourceEvent event){
        System.out.println("PanelSourceEvent has been caught: " + event);
        KParamDto dto = new KParamDto();
        dto.setDmd(event.getDto().getSourceDataDto().getDmd());
        dto.setDistance(event.getDto().getSourceDataDto().getDistance());
        dto.setDirectionCoefficient(event.getDto().getSourceDataDto().getDirectionCoefficient());
        dto.setVoltage(radiationType.getVoltage().doubleValue());
        dto.setKerma(radiationType.getRadiationExit());
        dto.setWorkLoad(radiationType.getWorkload().doubleValue());
    }

}
