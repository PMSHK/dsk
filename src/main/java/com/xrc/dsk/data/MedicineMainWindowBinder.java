package com.xrc.dsk.data;

import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.dto.MedWindowDto;
import com.xrc.dsk.dto.RadiationTypeDto;
import com.xrc.dsk.dto.WindowDto;
import com.xrc.dsk.events.EventManager;
import com.xrc.dsk.events.RadiationTypeEvent;
import javafx.beans.property.LongProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MedicineMainWindowBinder implements Bindable {
    private final Binder binder;
    private final ConnectionService connectionService;
    private MedWindowDto medWindowDto;
    private TextField voltageField;
    private TextField workLoadField;
    private Label radExitLabel;
    private Label typeLabel;
    private ComboBox<String> equipmentType;
    private RadiationTypeDto radiationTypeDto;

    public MedicineMainWindowBinder() {
        this.binder = new Binder();
        this.connectionService = new ConnectionService();
    }

    public MedicineMainWindowBinder(TextField voltageField,
                                    TextField workLoadField,
                                    Label radExitLabel,
                                    Label typeLabel,
                                    ComboBox<String> equipmentType,
                                    RadiationTypeDto radiationTypeDto
    ) {
        this();
        this.voltageField = voltageField;
        this.workLoadField = workLoadField;
        this.radExitLabel = radExitLabel;
        this.typeLabel = typeLabel;
        this.equipmentType = equipmentType;
        this.radiationTypeDto = radiationTypeDto;
    }

    @Override
    public void bind(WindowDto dto) {
        this.medWindowDto = (MedWindowDto) dto;

        LongProperty voltageProperty = medWindowDto
                .getRadiationType()
                .voltageProperty();


        binder.bindLongPropertyToString(voltageField.textProperty(), voltageProperty, radiationTypeDto.getVoltage(),
                (val) -> {
                    medWindowDto.getRadiationType().setVoltage(val);
                    System.out.println("voltage: " + val + " has been saved");
                    double radExit = connectionService.getRadExit(val);
                    radExitLabel.setText(String.valueOf(radExit));
                    System.out.println("rExit: " + radExit + " has been saved");
//                    updatePanelsProtection();
                });
        radExitLabel.setText(String.valueOf(connectionService.getRadExit(radiationTypeDto.getVoltage())));
        medWindowDto.getRadiationType().setRadiationExit(Double.parseDouble(radExitLabel.getText()));
        medWindowDto.getRadiationType().setName(equipmentType.getValue());


        LongProperty workloadProperty = medWindowDto
                .getRadiationType().workloadProperty();

        binder.bindLongPropertyToString(workLoadField.textProperty(), workloadProperty, radiationTypeDto.getWorkload(),
                (val) -> {
                    medWindowDto.getRadiationType().setWorkload(val);
                    System.out.println("workload: " + val + " has been saved");
//                    updatePanelsProtection();
                });
        updatePanelsProtection();
    }

    private void updatePanelsProtection() {
        if (medWindowDto.getRadiationType().filled()) {
            EventManager.post(new RadiationTypeEvent(medWindowDto.getRadiationType()));
        }
    }

    private void addEquipmentTypeListener(ComboBox<String> equipmentType) {
        equipmentType.selectionModelProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == oldValue) {
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
