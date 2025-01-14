package com.xrc.dsk.services;

import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.data.DataStorage;
import com.xrc.dsk.data.MedicineMainWindowBinder;
import com.xrc.dsk.dto.MedWindowDto;
import com.xrc.dsk.dto.RadiationTypeDto;
import com.xrc.dsk.dto.WindowDto;
import com.xrc.dsk.handlers.ComboBoxHandler;
import javafx.event.ActionEvent;
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
    private DataStorage dataStorage;
    private WindowDto windowDto;
    private String dtoClassName;
    private Map<String, WindowDto> dtoHandlerMap;
    private ConnectionService connectionService;
    private Map<String, Object[]> elements;

    public DataService(WindowDto dto) {
        this.dataStorage = DataStorage.getInstance();
        this.windowDto = dto;
        this.dtoClassName = dto.getClass().getSimpleName();
    }

    public void bindMedicineMainWindow(
            TextField voltageField, TextField workloadField, Label radExitLabel, Label type, ComboBox<String> equipmentType) {
        if (connectionService == null) {
            connectionService = new ConnectionService();
        }
        if (elements == null) {
            elements = new HashMap<>();
        }
        ComboBoxHandler<String> handler = new ComboBoxHandler<>(equipmentType);
        RadiationTypeDto radiationTypeDto = connectionService.getEquipTypeParameters(handler.getElement(), "MED");
        MedWindowDto windowDto = getMedicineMainWindow();
//        dataStorage.setWindowDto(windowDto);
        MedicineMainWindowBinder medicineBinder = new MedicineMainWindowBinder();
        elements.put("voltage", new Object[]{voltageField, radiationTypeDto.getVoltage()});
        elements.put("workLoad", new Object[]{workloadField, radiationTypeDto.getWorkload()});
        elements.put("radExit", new Object[]{radExitLabel, connectionService.getRadExit(radiationTypeDto.getVoltage())});
        elements.put("type", new Object[]{type});
        elements.put("equipmentType", new Object[]{equipmentType});
        medicineBinder.bind(windowDto, elements);

    }

    private MedWindowDto getMedicineMainWindow() {
        MedWindowDto medWindowDto = (MedWindowDto) dataStorage.getWindowDto();
        if (medWindowDto == null) {
            medWindowDto = new MedWindowDto();
            dataStorage.setWindowDto(medWindowDto);
        }
        return medWindowDto;
    }
}
