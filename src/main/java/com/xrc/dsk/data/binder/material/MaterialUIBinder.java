package com.xrc.dsk.data.binder.material;

import com.xrc.dsk.services.material.MatCalcService;
import com.xrc.dsk.services.material.MaterialViewModelManager;
import javafx.beans.binding.Bindings;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MaterialUIBinder {
    private ComboBox<String> matBox;
    private TextField thicknessField;
    private Label label;
    private boolean suppressEvents = false;
    private final MaterialViewModelManager dataManager;
    private final MatCalcService calculator;

    public MaterialUIBinder(
            MaterialViewModelManager dataManager,
            MatCalcService calculator) {
        this.dataManager = dataManager;
        this.calculator = calculator;
    }

    public void setupThicknessBaseBindings(ComboBox<String> matBox, TextField thicknessField, Label label) {
        this.matBox = matBox;
        this.thicknessField = thicknessField;
        this.label = label;
        bindThicknessField();
        setupMaterialSelectionListener();
        setupLeadEquivalentLabel();
    }

    public void setupLeadBaseBindings(ComboBox<String> matBox, Label label) {
        this.matBox = matBox;
        this.label = label;
        setupThicknessLabel();
        setupMaterialSelectionListener();
    }

    private void bindThicknessField() {
        thicknessField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.isEmpty() && !newValue.equals(oldValue)) {
                dataManager.updateThickness(Double.parseDouble(newValue));
            }
        });
        System.out.println("ADDING LISTENER TO " + dataManager.getThicknessProperty());
        dataManager.getThicknessProperty().addListener((obs, oldVal, newVal) -> {
            System.out.println("🔥 Thickness changed: " + oldVal + " -> " + newVal);
//            if (suppressEvents) return;
//            suppressEvents = true;
//            try {
                calculator.handleThicknessChange(newVal.doubleValue());
//            } finally {
//                suppressEvents = false;
//            }
        });
    }

    private void setupMaterialSelectionListener() {
        matBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && !newVal.equals(oldVal) && thicknessField!=null) {
                thicknessField.setText(String.format("%.2f", calculator.updateMaterialInfo(newVal)));
            } else {calculator.updateMaterialInfo(newVal);}
        });
    }

    private void setupLeadEquivalentLabel() {
        label.textProperty().bind(Bindings.createStringBinding(
                () -> String.format("%.2f", dataManager.getMatVM().getLeadEquivalent()),
                dataManager.getMatVM().getLeadEquivalentProperty()
        ));
    }

    private void setupThicknessLabel() {
        label.textProperty().bind(Bindings.createStringBinding(
                () -> String.format("%.2f", dataManager.getMatVM().getThickness()),
                dataManager.getMatVM().getThicknessProperty(),
                dataManager.getPanelVM().getAdditionalLeadViewModelProperty()
        ));
    }

}
