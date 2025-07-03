package com.xrc.dsk.data;

import com.xrc.dsk.dto.MaterialCharacteristicsDto;
import com.xrc.dsk.dto.MedWindowDto;
import com.xrc.dsk.dto.medicine.PanelDataDto;
import com.xrc.dsk.dto.medicine.MatCharacteristicsDataDto;
import com.xrc.dsk.dto.WindowDto;
import com.xrc.dsk.events.AdditionalMatEvent;
import com.xrc.dsk.events.EventManager;
import com.xrc.dsk.listeners.AdditionalMatUpdateService;
import com.xrc.dsk.viewModels.medicine.MatCharacteristicsDataViewModel;
import com.xrc.dsk.viewModels.medicine.MedicineDataViewModel;
import com.xrc.dsk.viewModels.medicine.PanelDataViewModel;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
        PanelDataViewModel panelDataViewModel = viewModel.getPanelDataViewModelList().get(panelId);
        PanelDataDto panelDataDto = viewModel.getPanelDataProperty().get(panelId).toDto();
        AdditionalMatUpdateService service = new AdditionalMatUpdateService(additionalMatLeadLabel, panelDataDto, panelId);
        panelDataViewModel.getExistedMatCharacteristicsViewModelListProperty().addListener((ListChangeListener<MatCharacteristicsDataViewModel>) change->
                createEvent(change,panelDataViewModel));
//        panelDataViewModel.getExistedMatCharacteristicsViewModelListProperty().addListener((obs, oldVal, newVal) -> {
//            createEvent(newVal, panelDataViewModel);
//
//        });


        panelDataViewModel.getProtectionViewModelProperty().addListener((obs, oldVal, newVal) -> {
            if (oldVal != null) {
                log.info("Removing additional material characteristics to panel {}", panelId);
            }
            createEvent(newVal, panelDataViewModel);
//            if (newVal != null) {
//                Double leadEquivalent = panelDataDto.getExistedMaterialCharacteristicsDtoList().stream().
//                        map(MatCharacteristicsDataDto::getLeadEquivalent).reduce(0d, Double::sum);
//                AdditionalMatEvent event = new AdditionalMatEvent(panelDataDto.getProtectionDto().getLeadEqv(), leadEquivalent, panelId);
//                log.info("Adding additional material characteristics to panel {}", panelId);
//                EventManager.post(event);
//            }
        });

    }

    private <T> void createEvent(T newVal, PanelDataViewModel panelDataViewModel) {
        if (newVal != null) {
//                Double leadEquivalent = panelDataDto.getExistedMaterialCharacteristicsDtoList().stream().
//                        map(MatCharacteristicsDataDto::getLeadEquivalent).reduce(0d, Double::sum);
            Double leadEquivalent = panelDataViewModel.getExistedMatCharacteristicsViewModelList().stream().
                    map(MatCharacteristicsDataViewModel::getLeadEquivalent).reduce(0.0, Double::sum);
            AdditionalMatEvent event = new AdditionalMatEvent(panelDataViewModel.getProtectionViewModel().getLeadEqv(), leadEquivalent, panelId,panelDataViewModel);
            log.info("Adding additional material characteristics to panel {}", panelId);
            EventManager.post(event);
        }
    }
}
