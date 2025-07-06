package com.xrc.dsk.controllers;

import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.data.DataStorage;
import com.xrc.dsk.services.DataService;
import com.xrc.dsk.services.SaveLoader;
import com.xrc.dsk.viewModels.medicine.MedicineDataViewModel;
import com.xrc.dsk.windows.CalculatorWindow;
import com.xrc.dsk.windows.WindowControl;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CalculatorWindowController extends WindowControl {

    private ConnectionService connectionService;
    private MedicineDataViewModel dataViewModel;

    @FXML
    private ComboBox<String> equipmentType;

    @FXML
    private FontAwesomeIconView gearIcon;

    @FXML
    private FontAwesomeIconView infoIcon;

    @FXML
    private FontAwesomeIconView uploadIcon;

    @FXML
    private HBox panelsStorage;

    @FXML
    private Label radExitLabel;

    @FXML
    private FontAwesomeIconView saveIcon;

    @FXML
    private FontAwesomeIconView sendIcon;

    @FXML
    private Label type;

    @FXML
    private TextField voltageField;

    @FXML
    private TextField workloadField;

    private CalculatorWindow window;

    private DataStorage storage = DataStorage.getInstance();

    private SaveLoader saveLoader;
    private DataService dataService;

    @FXML
    void getInfo(MouseEvent event) {

    }

    @FXML
    void getRadExit(KeyEvent event) {
    }

    @FXML
    void getWorkload(KeyEvent event) {

    }

    @FXML
    void load(MouseEvent event) {

    }

    @FXML
    void openSettings(MouseEvent event) {

    }

    @FXML
    void save(MouseEvent event) {
        if (saveLoader == null) {
            saveLoader = new SaveLoader(getWindow());
        }
        saveLoader.save(dataViewModel);
    }

    @FXML
    void send(MouseEvent event) {

    }

    @FXML
    void handleEquipType(ActionEvent event) {
        initialize();
        if (dataService == null) {
            dataService = new DataService(dataViewModel);
        }
        dataService.bindMedicineMainWindow(voltageField, workloadField, radExitLabel, type, equipmentType);
    }

    private void initialize() {
        if (connectionService == null) {
            connectionService = new ConnectionService();
        }
    }
}
