package com.xrc.dsk.data.binder.openings;

import com.xrc.dsk.services.openings.OpeningsCalcService;
import com.xrc.dsk.services.openings.OpeningsVMManager;
import javafx.beans.binding.Bindings;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class OpeningsUIBinder {
    private ComboBox<String> openingsBox;
    private Label openingsLabel;
    private final OpeningsCalcService calculator;
    private final OpeningsVMManager manager;

    public OpeningsUIBinder(
            OpeningsVMManager manager,
            OpeningsCalcService calculator) {
        this.calculator = calculator;
        this.manager = manager;
    }

    public void setupBindings(ComboBox<String> openingsBox, Label openingsLabel) {
        this.openingsBox = openingsBox;
        this.openingsLabel = openingsLabel;
        bindBox();
        bindLabel();
    }

    private void bindBox() {
        openingsBox.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null && !newValue.equals(oldValue)) {
                calculator.updateOpeningInfo(newValue);
            }
        });
    }

    private void bindLabel() {
        openingsLabel.textProperty().bind(Bindings.createStringBinding(
                () -> {
                    calculator.updateOpeningInfo(openingsBox.getSelectionModel().getSelectedItem());
                    String thickness = manager.getPanelVM().getThickness();
                    return String.format(thickness);
                },
                manager.getPanelVM().getThicknessProperty(),
                manager.getPanelVM().getNameProperty(),
                manager.getGeneralVM().getAdditionalLeadViewModelProperty(),
                manager.getMedicineVM().getRadiationTypeProperty()
        ));
    }
}
