package com.xrc.dsk.services;

import com.xrc.dsk.controllers.CalculatorWindowController;
import com.xrc.dsk.model.PanelsStorage;
import com.xrc.dsk.panels.CalculationPanel;
import com.xrc.dsk.panels.MedicineCalculationPanel;
import com.xrc.dsk.windows.CalculatorWindow;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Getter
public class MedicineWindowService {
    private final CalculatorWindow window;
    private List<MedicineCalculationPanel> panels;
    private SaveLoader saveLoader;
    private MedicinePanelService panelService;
    private CalculatorWindowController calculatorWindowController;
    private PanelsStorage panelsStorage = PanelsStorage.getInstance();

    public MedicineWindowService(CalculatorWindow window) {
        this.window = window;
    }

    public MedicineWindowService (CalculatorWindow window, List<MedicineCalculationPanel> panels){
        this.window = window;
        this.panels = panels;
    }

    public void initialize(String type) {
        calculatorWindowController = ((CalculatorWindowController) window.getController());
        calculatorWindowController.getType().setText(type);
        if (panels == null) {
            panels = new ArrayList<>();
            panels.add(new MedicineCalculationPanel());
        }
        for (MedicineCalculationPanel panel : panels) {
            calculatorWindowController.getPanelsStorage().getChildren().add(panel.getRootNode());
        }
        panelsStorage.setPanelsStorage(calculatorWindowController.getPanelsStorage());
    }

//    public void initialize(String type, SaveLoader saveLoader) {
//        this.saveLoader = saveLoader;
//    }
//
//    public void addNewPanel() {
//        MedicineCalculationPanel panel = new MedicineCalculationPanel();
//        panels.add(panel);
//        calculatorWindowController.getPanelsStorage().getChildren().add(panel);
//    }
//
//    public void deletePanel(MedicineCalculationPanel panel) {
//        panels.remove(panel);
//    }
}
