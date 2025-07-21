package com.xrc.dsk.windows;

import com.xrc.dsk.controllers.MaterialManagerWindowController;

public class MaterialManagerWindow extends GeneralWindow<MaterialManagerWindowController> {
    public MaterialManagerWindow() {
        super("/com/xrc/dsk/windows/DelEditUpdateWindow.fxml", "Менеджер материалов");
    }
}
