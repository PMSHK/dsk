package com.xrc.dsk.data;

import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.dto.MedWindowDto;
import com.xrc.dsk.dto.RadiationTypeDto;
import com.xrc.dsk.dto.WindowDto;
import com.xrc.dsk.dto.medicine.RadTypeDataDto;
import com.xrc.dsk.events.EventManager;
import com.xrc.dsk.events.RadiationTypeEvent;
import com.xrc.dsk.viewModels.medicine.MedicineDataViewModel;
import com.xrc.dsk.viewModels.medicine.RadTypeDataViewModel;
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
//    private RadTypeDataDto radiationTypeDto;
    private MedicineDataViewModel viewModel;

    public MedicineMainWindowBinder() {
        this.binder = new Binder();
        this.connectionService = new ConnectionService();
    }

    public MedicineMainWindowBinder(TextField voltageField,
                                    TextField workLoadField,
                                    Label radExitLabel,
                                    Label typeLabel,
                                    ComboBox<String> equipmentType,
                                    MedicineDataViewModel viewModel
    ) {
        this();
        this.voltageField = voltageField;
        this.workLoadField = workLoadField;
        this.radExitLabel = radExitLabel;
        this.typeLabel = typeLabel;
        this.equipmentType = equipmentType;
        this.viewModel = viewModel;
    }

    @Override
    public void bind() {

        RadTypeDataViewModel radTypeViewModel = viewModel.getRadiationTypeViewModel();
        LongProperty voltageProperty = radTypeViewModel.getVoltageProperty();
        RadTypeDataDto radTypeDataDto = radTypeViewModel.toDto();
        LongProperty workloadProperty = radTypeViewModel.getWorkloadProperty();

        binder.bindLongPropertyToString(voltageField.textProperty(), voltageProperty, radTypeDataDto.getVoltage(),
                (val) -> {
                    voltageProperty.set(val);
                    System.out.println("voltage: " + val + " has been saved");
                    double radExit = connectionService.getRadExit(val);
                    radExitLabel.setText(String.valueOf(radExit));
                    System.out.println("rExit: " + radExit + " has been saved");
//                    updatePanelsProtection();
                });
        radExitLabel.setText(String.valueOf(connectionService.getRadExit(radTypeDataDto.getVoltage())));
        radTypeViewModel.getRadiationExitProperty().set(Double.parseDouble(radExitLabel.getText()));
        radTypeViewModel.getNameProperty().set(equipmentType.getValue());



        binder.bindLongPropertyToString(workLoadField.textProperty(), workloadProperty, radTypeDataDto.getWorkload(),
                (val) -> {
                    radTypeViewModel.getWorkloadProperty().set(val);
                    System.out.println("workload: " + val + " has been saved");
//                    updatePanelsProtection();
                });
        updatePanelsProtection(radTypeDataDto, radTypeViewModel);
    }

    private void updatePanelsProtection(RadTypeDataDto dto, RadTypeDataViewModel radTypeViewModel) {
        if (radTypeViewModel.filled()) {
            EventManager.post(new RadiationTypeEvent(dto));
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
