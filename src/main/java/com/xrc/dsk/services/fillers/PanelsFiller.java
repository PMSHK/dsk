package com.xrc.dsk.services.fillers;

import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.panels.CalculationPanel;
import lombok.Getter;

@Getter
public abstract class PanelsFiller {
    private final CalculationPanel panel;
    private final ConnectionService connectionService = new ConnectionService();

    public PanelsFiller(CalculationPanel panel) {
        this.panel = panel;
    }

    public abstract void fill();
}
