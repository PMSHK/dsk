package com.xrc.dsk.data;

import com.xrc.dsk.dto.MaterialCharacteristicsDto;
import com.xrc.dsk.dto.MedWindowDto;
import com.xrc.dsk.dto.medicine.PanelDataDto;
import com.xrc.dsk.dto.medicine.MatCharacteristicsDataDto;
import com.xrc.dsk.dto.WindowDto;
import com.xrc.dsk.events.AdditionalMatEvent;
import com.xrc.dsk.events.EventManager;
import com.xrc.dsk.listeners.AdditionalMatUpdateService;
import com.xrc.dsk.viewModels.medicine.MedicineDataViewModel;
import javafx.scene.control.Label;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdditionalMatBinder implements Bindable {
    private final Binder binder;
//    private WindowDto window;
    private Label additionalMatLeadLabel;
    private int panelId;
    private MedicineDataViewModel viewModel;
//    private MedWindowDto medWindowDto;

    public AdditionalMatBinder(
            Label additionalMatLeadLabel,
            int panelId,
            MedicineDataViewModel viewModel) {
        this.binder = new Binder();
        this.additionalMatLeadLabel = additionalMatLeadLabel;
        this.panelId = panelId;
        this.viewModel = viewModel;
        log.info("addMatBinder has been created");
    }

    @Override
    public void bind() {
        log.info("Starting binding for additional material data");
        PanelDataDto panelDataDto = viewModel.getPanelDataProperty().get(panelId).toDto();
        AdditionalMatUpdateService service = new AdditionalMatUpdateService(additionalMatLeadLabel, panelDataDto, panelId);
        viewModel.getPanelDataProperty().get(panelId).getExistedMatCharacteristicsViewModelListProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                Double leadEquivalent = panelDataDto.getExistedMaterialCharacteristicsDtoList().stream().
                        map(MatCharacteristicsDataDto::getLeadEquivalent).reduce(0d, Double::sum);
                AdditionalMatEvent event = new AdditionalMatEvent(panelDataDto.getProtectionDto().getLeadEqv(), leadEquivalent, panelId);
                log.info("Adding additional material characteristics to panel {}", panelId);
                EventManager.post(event);
            }

        });


        viewModel.getPanelDataProperty().get(panelId).getProtectionViewModelProperty().addListener((obs, oldVal, newVal) -> {
            if (oldVal != null) {
                log.info("Removing additional material characteristics to panel {}", panelId);
            }
            if (newVal != null) {
                Double leadEquivalent = panelDataDto.getExistedMaterialCharacteristicsDtoList().stream().
                        map(MatCharacteristicsDataDto::getLeadEquivalent).reduce(0d, Double::sum);
                AdditionalMatEvent event = new AdditionalMatEvent(panelDataDto.getProtectionDto().getLeadEqv(), leadEquivalent, panelId);
                log.info("Adding additional material characteristics to panel {}", panelId);
                EventManager.post(event);
            }
        });

    }
}
