package com.xrc.dsk.controllers;

import com.xrc.dsk.windows.WindowControl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaterialManagerWindowController implements StageCtrl {
    private Stage stage;

    @FXML
    private Button acceptBtn;

    @FXML
    private Button addMatBtn;

    @FXML
    private TextField baseMatField;

    @FXML
    private Label baseMatLbl;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button declineBtn;

    @FXML
    private Button delMatBtn;

    @FXML
    private Label densLbl;

    @FXML
    private Button editMatBtn;

    @FXML
    private TextField matDensField;

    @FXML
    private TextField matNameField;

    @FXML
    void acceptBaseMatField(MouseEvent event) {

    }

    @FXML
    void acceptBtnClick(MouseEvent event) {

    }

    @FXML
    void addMatBtnClick(MouseEvent event) {

    }

    @FXML
    void cancelBtnClick(MouseEvent event) {
        closeWindow();
    }

    @FXML
    void declineBtnClick(MouseEvent event) {

    }

    @FXML
    void delMatBtnClick(MouseEvent event) {

    }

    @FXML
    void editMatBtnClick(MouseEvent event) {
        if(!densLbl.isVisible()||!baseMatLbl.isVisible()||!matDensField.isVisible()||!baseMatField.isVisible()){
            densLbl.setVisible(true);
            baseMatLbl.setVisible(true);
            matDensField.setVisible(true);
            baseMatField.setVisible(true);
            acceptBtn.setVisible(true);
            declineBtn.setVisible(true);
        } else{
            densLbl.setVisible(false);
            baseMatLbl.setVisible(false);
            matDensField.setVisible(false);
            baseMatField.setVisible(false);
            acceptBtn.setVisible(false);
            declineBtn.setVisible(false);
        }
    }

    @Override
    public void closeWindow() {
        StageCtrl.super.closeWindow();
    }

}

