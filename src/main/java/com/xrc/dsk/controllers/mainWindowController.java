package com.xrc.dsk.controllers;

import com.xrc.dsk.services.MedicineWindowService;
import com.xrc.dsk.windows.CalculatorWindow;
import com.xrc.dsk.windows.WindowControl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class mainWindowController extends WindowControl {
    @FXML
    private ImageView medicineImg;
    @FXML
    private ImageView flawImg;
    @FXML
    private ImageView veterinaryImg;
    @FXML
    private Button medicineButton;
    @FXML
    private Button veterinaryButton;
    @FXML
    private Button flawButton;
    @FXML
    private AnchorPane rightPane;
    @FXML
    private AnchorPane leftPane;


    public void medicineButtonClick(MouseEvent mouseEvent) {
        CalculatorWindow calculatorWindow = new CalculatorWindow(
                "/com/xrc/dsk/windows/calculator-window.fxml"
                , "XRC Medicine"
        );
        calculatorWindow.show();
        MedicineWindowService service = new MedicineWindowService(calculatorWindow);
        service.initialize("Медицина");
    }

    public void medicineButtonSelected(MouseEvent mouseEvent) {

        System.out.println("medicineButton entered");
        veterinaryButton.setVisible(false);
        flawButton.setVisible(false);
        rightPane.setVisible(true);
        medicineImg.setVisible(true);

    }

    public void medicineButtonDeselected(MouseEvent mouseEvent) {
        System.out.println("medicineButton exited");
        veterinaryButton.setVisible(true);
        flawButton.setVisible(true);
        rightPane.setVisible(false);
        medicineImg.setVisible(false);
    }

    public void veterinaryButtonClick(MouseEvent mouseEvent) {
        System.out.println("veterinaryButtonClick");
    }

    public void veterinaryButtonSelected(MouseEvent mouseEvent) {
        System.out.println("veterinaryButton entered");
        medicineButton.setVisible(false);
        flawButton.setVisible(false);
        leftPane.setVisible(true);
        veterinaryImg.setVisible(true);
    }

    public void veterinaryButtonDeselected(MouseEvent mouseEvent) {
        System.out.println("veterinaryButton exited");
        medicineButton.setVisible(true);
        flawButton.setVisible(true);
        leftPane.setVisible(false);
        veterinaryImg.setVisible(false);
    }

    public void flawButtonClick(MouseEvent mouseEvent) {
        System.out.println("flawButtonClick");
    }

    public void flawButtonSelected(MouseEvent mouseEvent) {
        System.out.println("flawButton entered");
        medicineButton.setVisible(false);
        veterinaryButton.setVisible(false);
        rightPane.setVisible(true);
        flawImg.setVisible(true);
    }

    public void flawButtonDeselected(MouseEvent mouseEvent) {
        System.out.println("flawButton exited");
        medicineButton.setVisible(true);
        veterinaryButton.setVisible(true);
        rightPane.setVisible(false);
        flawImg.setVisible(false);
    }
}
