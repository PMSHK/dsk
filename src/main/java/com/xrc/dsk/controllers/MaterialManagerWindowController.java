package com.xrc.dsk.controllers;

import com.xrc.dsk.connection.ConnectionService;
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
    private ConnectionService connectionService;

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
        boolean isDigit = matDensField.getText().chars().allMatch(Character::isDigit);
        if (baseMatField.getText().isEmpty()) {
            baseMatField.setStyle("-fx-border-color: red");
        } else {
            baseMatField.setStyle("-fx-border-color: green");
        }
        if (matDensField.getText().isEmpty() || !isDigit) {
            matDensField.setStyle("-fx-border-color: red");
            return;
        } else {
            matDensField.setStyle("-fx-border-color: green");
        }
        System.out.println("hahahaha");
    }

    @FXML
    void addMatBtnClick(MouseEvent event) {
        switchOnOffMatFields("Наименование: ", "Плотность: ");
    }

    @FXML
    void cancelBtnClick(MouseEvent event) {
        closeWindow();
    }

    @FXML
    void declineBtnClick(MouseEvent event) {
        turnOffMatFields();
    }

    @FXML
    void delMatBtnClick(MouseEvent event) {

    }

    @FXML
    void editMatBtnClick(MouseEvent event) {
        switchOnOffMatFields("Новое наименование: ", "Новая плотность: ");
    }

    @Override
    public void closeWindow() {
        StageCtrl.super.closeWindow();
    }

    private void switchOnOffMatFields(String matName, String density) {
        if (!densLbl.isVisible() || !baseMatLbl.isVisible() || !matDensField.isVisible() || !baseMatField.isVisible()) {
            turnOnMatFields(matName, density);
        } else {
            turnOffMatFields();
        }
    }

    private void turnOffMatFields() {
        densLbl.setText("");
        baseMatLbl.setText("");
        densLbl.setVisible(false);
        baseMatLbl.setVisible(false);
        matDensField.setVisible(false);
        baseMatField.setVisible(false);
        acceptBtn.setVisible(false);
        declineBtn.setVisible(false);
    }
    private void turnOnMatFields(String matName, String density){
        densLbl.setText(density);
        baseMatLbl.setText(matName);
        densLbl.setVisible(true);
        baseMatLbl.setVisible(true);
        matDensField.setVisible(true);
        baseMatField.setVisible(true);
        acceptBtn.setVisible(true);
        declineBtn.setVisible(true);
    }

}

