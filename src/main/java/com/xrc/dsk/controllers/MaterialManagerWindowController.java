package com.xrc.dsk.controllers;

import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.converters.StringConverter;
import com.xrc.dsk.dto.medicine.MaterialInfoDataDto;
import com.xrc.dsk.services.material.MaterialManagerService;
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
    private MaterialManagerService materialManagerService = new MaterialManagerService();
    private String method;

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
        boolean isDigit = StringConverter.extractNumber(matDensField.getText()) > 0;

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

        manageAddEditMaterial();

    }

    private void manageAddEditMaterial() {
        switch (method) {
            case "ADD" -> {
                MaterialInfoDataDto target = new MaterialInfoDataDto(
                        StringConverter.extractLetters(baseMatField.getText()),
                        (float) StringConverter.extractNumber(matDensField.getText()
                        ));
                float density = (float) StringConverter.extractNumber(matNameField.getText());
                String name = StringConverter.extractLetters(matNameField.getText());
                MaterialInfoDataDto source = new MaterialInfoDataDto(
                        name,
                        density);

                materialManagerService.addMaterial(source, target);
            }
            case "EDIT" -> materialManagerService.updateMaterial(
                    new MaterialInfoDataDto(baseMatField.getText(),
                            (float) StringConverter.extractNumber(matDensField.getText())),
                    StringConverter.extractLetters(matNameField.getText()) ,(float) StringConverter.extractNumber(matNameField.getText())
            );
            default -> throw new IllegalArgumentException("Unsupported method: " + method);
        }
    }

//    private boolean isMaterialValid(){
//        boolean isValid = false;
//        boolean isDigit = matDensField.getText().chars().allMatch(Character::isDigit);
//        if (baseMatField.getText().isEmpty()) {
//            baseMatField.setStyle("-fx-border-color: red");
//        } else {
//            baseMatField.setStyle("-fx-border-color: green");
//            isValid = true;
//        }
//        if (matDensField.getText().isEmpty() || !isDigit) {
//            matDensField.setStyle("-fx-border-color: red");
//            isValid = false;
//        } else {
//            matDensField.setStyle("-fx-border-color: green");
//            isValid = true;
//        }
//        return isValid;
//    }

    @FXML
    void addMatBtnClick(MouseEvent event) {
        switchOnOffMatFields("Наименование: ", "Плотность: ");
        this.method = "ADD";

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
        materialManagerService.deleteMaterial(
                new MaterialInfoDataDto(matNameField.getText(),
                        (float) StringConverter.convertToNumber(matDensField.getText())));
    }

    @FXML
    void editMatBtnClick(MouseEvent event) {
        switchOnOffMatFields("Новое наименование: ", "Новая плотность: ");
        this.method = "EDIT";
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

    private void turnOnMatFields(String matName, String density) {
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

