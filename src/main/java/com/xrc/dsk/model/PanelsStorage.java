package com.xrc.dsk.model;

import javafx.scene.layout.HBox;
import lombok.Data;

@Data
public class PanelsStorage {
    private HBox panelsStorage;
    private static volatile PanelsStorage instance;

    private PanelsStorage() {
    }

    public static PanelsStorage getInstance() {
        if (instance == null) {
            synchronized (PanelsStorage.class) {
                if (instance == null) {
                    instance = new PanelsStorage();
                }
            }
        }
        return instance;
    }
}
