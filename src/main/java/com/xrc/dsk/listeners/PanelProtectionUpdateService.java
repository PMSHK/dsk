package com.xrc.dsk.listeners;

import com.google.common.eventbus.Subscribe;
import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.dto.KParamDto;
import com.xrc.dsk.dto.medicine.PanelDataDto;
import com.xrc.dsk.dto.medicine.ProtectionDataDto;
import com.xrc.dsk.dto.RadiationTypeDto;
import com.xrc.dsk.dto.medicine.RadTypeDataDto;
import com.xrc.dsk.events.EventManager;
import com.xrc.dsk.events.PanelSourceEvent;
import com.xrc.dsk.events.RadiationTypeEvent;
import com.xrc.dsk.viewModels.DataViewModel;
import com.xrc.dsk.viewModels.medicine.MedicineDataViewModel;

import java.util.ArrayList;
import java.util.List;

public class PanelProtectionUpdateService {
//    private final EventManager eventManager;
    private final List<PanelDataDto> panels;
    private final RadTypeDataDto radiationType;
    private ConnectionService connectionService;
    private final MedicineDataViewModel viewModel;
    private final int panelId;

//    public PanelProtectionUpdateService() {
//        panels = new ArrayList<>();
//        panels.add(new PanelDataDto());
//        radiationType = new RadTypeDataDto();
//        EventManager.register(this);
//    }
//
//    public PanelProtectionUpdateService(PanelDataDto panel, RadTypeDataDto radiationType){
//        this.panels = new ArrayList<>();
//        this.panels.add(panel);
//        this.radiationType = radiationType;
//        EventManager.register(this);
//    }

    public PanelProtectionUpdateService(MedicineDataViewModel viewModel, int panelId){
        this.viewModel = viewModel;
        this.panels = new ArrayList<>();
        this.panels.add(viewModel.getPanelDataProperty().get(panelId).toDto());
        this.radiationType = viewModel.getRadiationTypeViewModel().toDto();
        this.panelId = panelId;
        EventManager.register(this);
    }

//    public PanelProtectionUpdateService(List<PanelDataDto> panels, RadTypeDataDto radiationType){
//        this.panels = panels;
//        this.radiationType = radiationType;
//        RadiationTypeEvent event = new RadiationTypeEvent(radiationType);
//        EventManager.register(this);
//    }

    public void addPanel(PanelDataDto panel){
        panels.add(panel);
    }

    @Subscribe
    public void onRadiationTypeChanged(RadiationTypeEvent event) {
        System.out.println("RadiationTypeEvent has been caught: " + event);
        PanelDataDto currentPanelDto = viewModel.getPanelDataProperty().get().get(panelId).toDto();
        RadTypeDataDto currentRadiationType = viewModel.getRadiationTypeViewModel().toDto();
        this.connectionService = new ConnectionService();

            if (currentPanelDto.filled() && currentRadiationType.filled()) {
                KParamDto dto = getkParamDto(event, currentPanelDto);
                ProtectionDataDto protectionDto = connectionService.getDemandedLeadEquivalent(dto);
                viewModel.getPanelDataViewModelList().get(panelId).getProtectionViewModel().fromDto(protectionDto);
            }
    }

    private KParamDto getkParamDto(RadiationTypeEvent event, PanelDataDto currentPanelDto) {
        KParamDto dto = new KParamDto();
        dto.setDmd(currentPanelDto.getSourceDataDto().getDmd());
        dto.setDistance(currentPanelDto.getSourceDataDto().getDistance());
        dto.setDirectionCoefficient(currentPanelDto.getSourceDataDto().getDirectionCoefficient());
        dto.setVoltage(event.getRadiationType().getVoltage().doubleValue());
        dto.setKerma(event.getRadiationType().getRadiationExit());
        dto.setWorkLoad(event.getRadiationType().getWorkload().doubleValue());
        return dto;
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
