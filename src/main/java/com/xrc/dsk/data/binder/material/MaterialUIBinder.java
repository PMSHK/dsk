package com.xrc.dsk.data.binder.material;

import com.xrc.dsk.services.material.MatCalcService;
import com.xrc.dsk.services.material.MaterialViewModelManager;
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

    public MaterialUIBinder(ComboBox<String> matBox, TextField thicknessField, Label label,
                            MaterialViewModelManager dataManager,
                            MatCalcService calculator) {
        this.matBox = matBox;
        this.thicknessField = thicknessField;
        this.label = label;
        this.dataManager = dataManager;
        this.calculator = calculator;
    }

    public void setupThicknessBaseBindings(){
        bindThicknessField();
    }

    public void setupLeadBaseBindings(){
        bindThicknessField();
    }

    private void bindThicknessField() {
        thicknessField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.isEmpty() && !newValue.equals(oldValue)) {
                dataManager.updateThickness(Double.parseDouble(newValue));
            }
        });

        dataManager.getThicknessProperty().addListener((obs, oldVal, newVal) -> {
            if (suppressEvents) return;
            suppressEvents = true;
            try {
                calculator.handleThicknessChange(newVal.doubleValue());
            } finally {
                suppressEvents = false;
            }
        });
    }
}
