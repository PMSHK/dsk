package com.xrc.dsk.services.openings;

import com.xrc.dsk.data.binder.openings.OpeningsUIBinder;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class OpeningsBindingManager {
    private final OpeningsUIBinder uiBinder;

    public OpeningsBindingManager(
            OpeningsCalcService calculator, OpeningsVMManager manager) {
        this.uiBinder = new OpeningsUIBinder(manager, calculator);
    }

    public void bind(ComboBox<String> box, Label label) {
        uiBinder.setupBindings(box, label);
    }
}
