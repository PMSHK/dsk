package com.xrc.dsk.listeners;

import com.google.common.eventbus.Subscribe;
import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.connection.MaterialCharacteristicsRequestBuilder;
import com.xrc.dsk.events.EventManager;
import com.xrc.dsk.events.MaterialEvent;
import com.xrc.dsk.events.UpdateMatEvent;
import com.xrc.dsk.viewModels.medicine.MatCharacteristicsDataViewModel;
import com.xrc.dsk.viewModels.medicine.MedicineDataViewModel;
import javafx.beans.property.ListProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class MaterialPanelUpdateService {
    private final ComboBox<String> materialComboBox;
    private final TextField ThicknessField;
    private final Label leadEquivalentLabel;
    private ConnectionService connectionService;
    private Boolean basedOnThickness;
    private MedicineDataViewModel viewModel;
    private boolean isProcessing = false;

    public MaterialPanelUpdateService(ComboBox<String> materialComboBox,
                                      TextField ThicknessField, Label leadEquivalentLabel, boolean basedOnThickness,
                                      MedicineDataViewModel viewModel) {
        this.materialComboBox = materialComboBox;
        this.ThicknessField = ThicknessField;
        this.leadEquivalentLabel = leadEquivalentLabel;
        this.connectionService = new ConnectionService();
        this.basedOnThickness = basedOnThickness;
        this.viewModel = viewModel;
        EventManager.register(this);
    }

    @Subscribe
    public void onMaterialEvent(MaterialEvent event) {
        if(isProcessing) return;
        isProcessing = true;
        try {
            log.info("Material event received: {} from: {}", event, Thread.currentThread().getStackTrace()[2]);
            double result = init(event.getViewModel());
            if (result == -1) {
                return;
            }
            EventManager.unregister(this);
            event.getViewModel().getLeadEquivalentProperty().set(result);
            viewModel.getPanelDataProperty().get(event.getPanelId()).getExistedMatCharacteristicsViewModelListProperty().add(event.getViewModel());
            EventManager.register(this);
            System.out.println("Lead equivalent " + event.getViewModel().hashCode());
        } finally {
            isProcessing = false;
        }
//        leadEquivalentLabel.setText(Double.toString(result));
    }

    @Subscribe
    public void onMaterialUpdateEvent(UpdateMatEvent event) {
        if(isProcessing) return;
        isProcessing = true;
        try {
            double result = init(event.getViewModel());
            if (result == -1) {
                return;
            }
            if (event.getViewModel().getThickness() == 0) {
                event.getViewModel().getThicknessProperty().set(result);
                ThicknessField.setText(Double.toString(result));
            }
        } finally {
            isProcessing = false;
        }
    }

    private double init(MatCharacteristicsDataViewModel matModel) {

        log.info("got correct event for {}", this.toString());
        log.info("Material event has been caught: {}", matModel.toString());
        Long voltage = viewModel.getRadiationTypeViewModel().getVoltage();
        if (voltage < 50) {
            voltage = 50L;
        }

        double result = connectionService.getMaterialCharacteristics(
                matModel.getInfo().getName(), matModel.getInfo().getDensity(),
                voltage, matModel.getThickness(), matModel.getLeadEquivalent()
        );
        log.info("result: {}, voltage: {}, material: {}, thickness: {}, leadEquivalent: {}", result, voltage, matModel.getInfo().getName() + " " + matModel.getInfo().getDensity(), matModel.getThickness(), matModel.getLeadEquivalent());
        return result;
    }
}
