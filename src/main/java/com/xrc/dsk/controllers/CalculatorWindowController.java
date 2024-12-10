package com.xrc.dsk.controllers;

import com.xrc.dsk.windows.CalculatorWindow;
import com.xrc.dsk.windows.WindowControl;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.ResourceBundle;
@Setter
@Getter
public class CalculatorWindowController extends WindowControl {

    @FXML
    private ComboBox<?> equipmentType;

    @FXML
    private FontAwesomeIconView gearIcon;

    @FXML
    private FontAwesomeIconView infoIcon;

    @FXML
    private HBox panelsStorage;

    @FXML
    private Label radExit;

    @FXML
    private FontAwesomeIconView saveIcon;

    @FXML
    private FontAwesomeIconView sendIcon;

    @FXML
    private Label type;

    @FXML
    private Label voltage;

    @FXML
    private Label workload;

    private CalculatorWindow window;


    @FXML
    void getInfo(MouseEvent event) {

    }

    @FXML
    void openSettings(MouseEvent event) {

    }

    @FXML
    void save(MouseEvent event) {

    }

    @FXML
    void send(MouseEvent event) {

    }
}
