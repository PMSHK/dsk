package com.xrc.dsk.services;

import com.xrc.dsk.panels.MedicineCalculationPanel;
import javafx.scene.layout.Pane;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class MedicinePanelService {
    private MedicineCalculationPanel panel;

    public void addPanel(Pane pane){
        pane.getChildren().add(new MedicineCalculationPanel().getRootNode());
//        MedicineCalculationPanel newPanel = new MedicineCalculationPanel();
//        service.getCalculatorWindowController().getPanelsStorage().getChildren().add(newPanel.getRootNode());
    }

//    public void deletePanel(MedicineCalculationPanel panel){
//        panel.getRootNode().getParent().getChildren().remove(panel);
//    }

}
