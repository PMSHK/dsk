package com.xrc.dsk.data;

import com.xrc.dsk.dto.MedWindowDto;
import com.xrc.dsk.dto.medicine.PanelDataDto;
import com.xrc.dsk.events.EventManager;
import com.xrc.dsk.events.PanelRadiationTypeEvent;
import com.xrc.dsk.events.RadiationTypeEvent;
import com.xrc.dsk.listeners.PanelProtectionUpdateService;
import com.xrc.dsk.viewModels.medicine.MedicineDataViewModel;
import com.xrc.dsk.viewModels.medicine.SourceDataViewModel;
import javafx.beans.property.DoubleProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MedicineSourceDataBinder implements Bindable{
    private final Binder binder;
    private Integer panelId;
    private PanelProtectionUpdateService panelProtectionUpdateService;
    private Label dmdLabel;
    private ComboBox<Double> directionCoefficientBox;
    private TextField distanceField;
    private MedicineDataViewModel viewModel;

    public MedicineSourceDataBinder() {
        binder = new Binder();
    }

    public MedicineSourceDataBinder(Label dmdLabel, ComboBox<Double> directionCoefficientBox, TextField distanceField, int panelId, MedicineDataViewModel viewModel) {
        this.dmdLabel = dmdLabel;
        this.directionCoefficientBox = directionCoefficientBox;
        this.distanceField = distanceField;
        this.panelId = panelId;
        binder = new Binder();
        this.viewModel = viewModel;
    }

    @Override
    public void bind() {
        this.panelProtectionUpdateService = new PanelProtectionUpdateService(viewModel,panelId);

        SourceDataViewModel vm = viewModel.getPanelDataProperty().get(panelId).getSourceDataViewModel();
        DoubleProperty dmdProperty = vm.getDmdProperty();
        DoubleProperty directionCoefficientProperty = vm.getDirectionCoefficientProperty();
        DoubleProperty distanceProperty = vm.getDistanceProperty();

        binder.bindDoublePropertyToString(dmdLabel.textProperty(), dmdProperty, Double.parseDouble(dmdLabel.getText()),
                (val) -> {
                    vm.getDmdProperty().set(val);
                    EventManager.post(new PanelRadiationTypeEvent(viewModel.getRadiationTypeViewModel()));
                    System.out.println("dmd: " + val + " has been saved");
                });

        directionCoefficientBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                vm.getDirectionCoefficientProperty().set((Double) newValue);
                EventManager.post(new PanelRadiationTypeEvent(viewModel.getRadiationTypeViewModel()));
                System.out.println("direction coefficient: " + newValue + " has been saved");
            }
        });

        Double distance = 0d;
        if (!distanceField.getText().isEmpty()) {
            distance = Double.parseDouble(distanceField.getText());
        }
        binder.bindDoublePropertyToString(distanceField.textProperty(), distanceProperty, distance,
                (val) -> {
                    vm.getDistanceProperty().set(val);
                    EventManager.post(new PanelRadiationTypeEvent(viewModel.getRadiationTypeViewModel()));
                    System.out.println("distance: " + val + " has been saved");
                });
    }
}
