package com.xrc.dsk.data;

import com.xrc.dsk.events.AdditionalMatEvent;
import com.xrc.dsk.events.EventManager;
import com.xrc.dsk.listeners.AdditionalMatUpdateService;
import com.xrc.dsk.viewModels.medicine.MatCharacteristicsDataViewModel;
import com.xrc.dsk.viewModels.medicine.MedicineDataViewModel;
import com.xrc.dsk.viewModels.medicine.PanelDataViewModel;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Label;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdditionalMatBinder implements Bindable {
    private final Binder binder;
    private Label additionalMatLeadLabel;
    private int panelId;
    private MedicineDataViewModel viewModel;

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
        AdditionalMatUpdateService service = new AdditionalMatUpdateService(additionalMatLeadLabel, panelId);

        panelDataViewModel.getExistedMatCharacteristicsViewModelListProperty().forEach(
                characteristic -> {
                    characteristic.getLeadEquivalentProperty().addListener((observable, oldValue, newValue) -> {
                        if (newValue != null) {
                            createEvent(newValue, panelDataViewModel);
                        }
                    });
                }
        );
        panelDataViewModel.getExistedMatCharacteristicsViewModelListProperty().addListener(
                (ListChangeListener<MatCharacteristicsDataViewModel>) change -> {
                    while (change.next()) {
                        if (change.wasAdded()) {
                            for (MatCharacteristicsDataViewModel addedItem : change.getAddedSubList()) {
                                addedItem.getLeadEquivalentProperty().addListener((observable, oldValue, newValue) -> {
                                    if (newValue != null) {
                                        createEvent(newValue, panelDataViewModel);
                                    }
                                });
                            }
                        }

                        if (change.wasRemoved()) {

                        }
                    }
                }
        );

        panelDataViewModel.getExistedMatCharacteristicsViewModelListProperty().addListener((ListChangeListener<MatCharacteristicsDataViewModel>)
                change -> {
                    while (change.next()) {
                        if (change.wasAdded() || change.wasRemoved() || change.wasUpdated()) {
                            createEvent(change, panelDataViewModel);
                        }
                    }
                });


        panelDataViewModel.getProtectionViewModel().getLeadEqvProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && !newVal.equals(oldVal)) {
                createEvent(newVal, panelDataViewModel);
            }
        });

    }

    private <T> void createEvent(T newVal, PanelDataViewModel panelDataViewModel) {
        Double currentSum = panelDataViewModel.getExistedMatCharacteristicsViewModelList()
                .stream()
                .map(MatCharacteristicsDataViewModel::getLeadEquivalent)
                .reduce(0.0, Double::sum);

        if (!currentSum.equals(panelDataViewModel.getProtectionViewModel().getLeadEqv())) {
            AdditionalMatEvent event = new AdditionalMatEvent(
                    panelDataViewModel.getProtectionViewModel().getLeadEqv(),
                    currentSum,
                    panelId,
                    panelDataViewModel
            );
            EventManager.post(event);
        }
    }
}
