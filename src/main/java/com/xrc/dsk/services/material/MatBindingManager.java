package com.xrc.dsk.services.material;

import com.xrc.dsk.data.binder.material.MaterialUIBinder;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MatBindingManager {
    private final MaterialUIBinder uiBinder;

    public MatBindingManager(MatCalcService calculator, MaterialViewModelManager viewModelManager) {
        this.uiBinder = new MaterialUIBinder(viewModelManager, calculator);
    }

    public void bindThicknessBaseUI(ComboBox<String> matBox, TextField thicknessField, Label label) {
        uiBinder.setupThicknessBaseBindings(matBox, thicknessField, label);
    }

    public void bindLeadBaseUI(ComboBox<String> matBox, Label label) {
        uiBinder.setupLeadBaseBindings(matBox, label);
    }
}
