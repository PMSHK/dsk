package com.xrc.dsk.data;

import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.dto.MedWindowDto;
import com.xrc.dsk.dto.WindowDto;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.Map;

public class MedicineMainWindowBinder implements Bindable {
    private final Binder binder;
    private final ConnectionService connectionService;
    private MedWindowDto medWindowDto;

    public MedicineMainWindowBinder() {
        this.binder = new Binder();
        this.connectionService = new ConnectionService();
    }

    @Override
    public void bind(WindowDto dto, Map<String, Object[]> components) {
        medWindowDto = (MedWindowDto) dto;
        TextField voltageField = (TextField) components.get("voltage")[0];
        TextField workLoadField = (TextField) components.get("workLoad")[0];
        Label radExitLabel = (Label) components.get("radExit")[0];
        Label typeLabel = (Label) components.get("type")[0];
        ComboBox<String> equipmentType = (ComboBox<String>) components.get("equipmentType")[0];

        LongProperty voltageProperty = medWindowDto
                .getRadiationType()
                .voltageProperty();

        synchronized (binder) {
            binder.bindLongPropertyToString(voltageField.textProperty(), voltageProperty, (Long) components.get("voltage")[1],
                    (val) -> {
                        medWindowDto.getRadiationType().setVoltage(val);
                        System.out.println("voltage: " + val + " has been saved");
                        double radExit = connectionService.getRadExit(val);
                        radExitLabel.setText(String.valueOf(radExit));
                        System.out.println("rExit: " + radExit + " has been saved");
                    });
            radExitLabel.setText(String.valueOf((Double) components.get("radExit")[1]));
            medWindowDto.getRadiationType().setRadiationExit(Double.parseDouble(radExitLabel.getText()));
            medWindowDto.getRadiationType().setName(equipmentType.getValue());
            binder.notify();
        }

        LongProperty workloadProperty = medWindowDto
                .getRadiationType().workloadProperty();
        synchronized (binder) {
            binder.bindLongPropertyToString(workLoadField.textProperty(), workloadProperty, (Long) components.get("workLoad")[1],
                    (val) -> {
                        medWindowDto.getRadiationType().setWorkload(val);
                        System.out.println("workload: " + val + " has been saved");

                    });
            binder.notify();
        }

//        StringProperty typeName = medWindowDto
//                .getRadiationType()
//                .nameProperty();
//        typeLabel.textProperty().bindBidirectional(
//                (typeName)
//        );
    }

    private void addEquipmentTypeListener(ComboBox<String> equipmentType) {
        equipmentType.selectionModelProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == oldValue){
                String selected = equipmentType.getValue();
                equipmentType.getSelectionModel().clearSelection();
                equipmentType.getSelectionModel().select(selected);
            }
        });
    }

    public void updateRadExitLabel(Label label, Double value) {
        label.setText(String.valueOf(value));
    }
}
