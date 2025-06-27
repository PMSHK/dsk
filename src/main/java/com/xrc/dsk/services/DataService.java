package com.xrc.dsk.services;

import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.data.DataStorage;
import com.xrc.dsk.data.MedicineMainWindowBinder;
import com.xrc.dsk.dto.MedWindowDto;
import com.xrc.dsk.dto.RadiationTypeDto;
import com.xrc.dsk.dto.WindowDto;
import com.xrc.dsk.dto.medicine.RadTypeDataDto;
import com.xrc.dsk.handlers.ComboBoxHandler;
import com.xrc.dsk.viewModels.medicine.MedicineDataViewModel;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class DataService {
    private ConnectionService connectionService;
    private MedicineDataViewModel viewModel;

    public DataService(MedicineDataViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void bindMedicineMainWindow(
            TextField voltageField, TextField workloadField, Label radExitLabel, Label type, ComboBox<String> equipmentType) {
        if (connectionService == null) {
            connectionService = new ConnectionService();
        }

        ComboBoxHandler<String> handler = new ComboBoxHandler<>(equipmentType);
        RadTypeDataDto radiationTypeDto = connectionService.getEquipTypeParameters(handler.getElement(), "MED");
        viewModel.getRadiationTypeViewModel().fromDto(radiationTypeDto);
        MedicineMainWindowBinder medicineBinder = new MedicineMainWindowBinder(voltageField,
                workloadField,
                radExitLabel,
                type,
                equipmentType,
                viewModel);
        medicineBinder.bind();

    }
}
