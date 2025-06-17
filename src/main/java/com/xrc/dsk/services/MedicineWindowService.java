package com.xrc.dsk.services;

import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.controllers.CalculatorWindowController;
import com.xrc.dsk.viewModels.DataViewModel;
import com.xrc.dsk.dto.medicine.MedicineDataDto;
import com.xrc.dsk.model.PanelsStorage;
import com.xrc.dsk.panels.MedicineCalculationPanel;
import com.xrc.dsk.viewModels.medicine.MedicineDataViewModel;
import com.xrc.dsk.windows.CalculatorWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;


@Getter
@Slf4j
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
        log.info("Initializing Medicine Window");
        ConnectionService connectionService = new ConnectionService();
        ObservableList<String> equipmentTypes = FXCollections.observableList(connectionService.getEquipmentType());
        calculatorWindowController = ((CalculatorWindowController) window.getController());
        calculatorWindowController.setWindow(window);
        calculatorWindowController.getType().setText(type);
        calculatorWindowController.setDataViewModel(new MedicineDataViewModel());
        calculatorWindowController.getEquipmentType().setItems(equipmentTypes);
        if (panels == null) {
            panels = new ArrayList<>();
            panels.add(new MedicineCalculationPanel(0));
        }
        for (MedicineCalculationPanel panel : panels) {
            calculatorWindowController.getPanelsStorage().getChildren().add(panel.getRootNode());
        }
        panelsStorage.setPanelsStorage(calculatorWindowController.getPanelsStorage());
    }
}
