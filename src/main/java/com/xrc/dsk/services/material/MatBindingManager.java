package com.xrc.dsk.services.material;

import com.xrc.dsk.data.binder.material.MaterialUIBinder;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MatBindingManager {
    private final MaterialUIBinder uiBinder;
    private final MatCalcService calculator;

    public MatBindingManager(MaterialUIBinder uiBinder, MatCalcService calculator, MaterialViewModelManager viewModelManager) {
        this.uiBinder = uiBinder;
        this.calculator = calculator;
    }

    public void bindThicknessBaseUI(ComboBox<String> matBox, TextField thicknessField, Label label){
        uiBinder.setupThicknessBaseBindings();
    }
    public void bindLeadBaseUI(){
        uiBinder.setupLeadBaseBindings();
    }
}
