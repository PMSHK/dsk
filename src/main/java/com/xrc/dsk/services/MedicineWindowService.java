package com.xrc.dsk.services;

import com.xrc.dsk.controllers.CalculatorWindowController;
import com.xrc.dsk.windows.CalculatorWindow;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MedicineWindowService {
    private final CalculatorWindow window;

    public void initialize(String type){
        ((CalculatorWindowController)window.getController()).getType().setText(type);
    }
}
