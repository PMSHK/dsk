package com.xrc.dsk.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lombok.extern.slf4j.Slf4j;

//@Slf4j
public class mainWindowController {
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
        System.out.println("medicineButtonClick");
//        log.info("medicineButtonClick");
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
//        log.info("veterinaryButtonClick");
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
//        log.info("flawButtonClick");
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
