package com.xrc.dsk.listeners;

import com.google.common.eventbus.Subscribe;
import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.dto.KParamDto;
import com.xrc.dsk.dto.medicine.ProtectionDataDto;
import com.xrc.dsk.dto.medicine.RadTypeDataDto;
import com.xrc.dsk.events.EventManager;
import com.xrc.dsk.events.PanelRadiationTypeEvent;
import com.xrc.dsk.events.PanelSourceEvent;
import com.xrc.dsk.events.RadTypeEvents;
import com.xrc.dsk.events.RadiationTypeEvent;
import com.xrc.dsk.viewModels.medicine.MedicineDataViewModel;
import com.xrc.dsk.viewModels.medicine.PanelDataViewModel;

import java.util.ArrayList;
import java.util.List;

public class PanelProtectionUpdateService {
    //    private final EventManager eventManager;
    private final List<PanelDataViewModel> panels;
    private final RadTypeDataDto radiationType;
    private ConnectionService connectionService;
    private final MedicineDataViewModel viewModel;
    private final int panelId;

    public PanelProtectionUpdateService(MedicineDataViewModel viewModel, int panelId) {
        this.viewModel = viewModel;
        this.panels = new ArrayList<>();
        this.panels.addAll(viewModel.getPanelDataProperty().get());
        this.radiationType = viewModel.getRadiationTypeViewModel().toDto();
        this.panelId = panelId;
        this.connectionService = new ConnectionService();
        EventManager.register(this);
    }

    public void addPanel(PanelDataViewModel panel) {
        panels.add(panel);
    }

    @Subscribe
    public void onRadiationTypeChanged(RadiationTypeEvent event) {
        System.out.println("RadiationTypeEvent has been caught: " + event);
        for (PanelDataViewModel panelVM : panels) {
            if (event.getViewModel().filled() && panelVM.getSourceDataViewModel().filled()) {
                KParamDto dto = getkParamDto(event, panelVM);
                ProtectionDataDto protectionDto = connectionService.getDemandedLeadEquivalent(dto);
                panelVM.getProtectionViewModel().fromDto(protectionDto);
            }
        }

    }

    @Subscribe
    public void onRadiationTypeChanged(PanelRadiationTypeEvent event) {
        System.out.println("RadiationTypeEvent has been caught: " + event);
        PanelDataViewModel currentPanelVM = viewModel.getPanelDataProperty().get().get(panelId);
        if (event.getViewModel().filled() && currentPanelVM.getSourceDataViewModel().filled()) {
            KParamDto dto = getkParamDto(event, currentPanelVM);
            ProtectionDataDto protectionDto = connectionService.getDemandedLeadEquivalent(dto);
            currentPanelVM.getProtectionViewModel().fromDto(protectionDto);
        }
    }

    private KParamDto getkParamDto(RadTypeEvents event, PanelDataViewModel currentPanelVM) {
        KParamDto dto = new KParamDto();
        dto.setDmd(currentPanelVM.getSourceDataViewModel().getDmd());
        dto.setDistance(currentPanelVM.getSourceDataViewModel().getDistance());
        dto.setDirectionCoefficient(currentPanelVM.getSourceDataViewModel().getDirectionCoefficient());
        dto.setVoltage(event.getViewModel().getVoltage().doubleValue());
        dto.setKerma(event.getViewModel().getRadiationExit());
        dto.setWorkLoad(event.getViewModel().getWorkload().doubleValue());
        return dto;
    }

    @Subscribe
    public void onPanelChanged(PanelSourceEvent event) {
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
