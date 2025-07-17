package com.xrc.dsk.services.openings;

import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.dto.medicine.OpeningsDataDto;

import static com.xrc.dsk.converters.StringConverter.convertToNumber;

public class OpeningsCalcService {
    private ConnectionService service = new ConnectionService();
    private OpeningsVMManager manager;
    private double voltage;

    public OpeningsCalcService(OpeningsVMManager manager) {
        this.manager = manager;
        this.voltage = manager.getVoltage();
    }

    public void updateOpeningInfo(String openingName) {
        updateVoltage();
        OpeningsDataDto dto = new OpeningsDataDto(openingName);
        if (isValid()) {
            if (!openingName.equals("Нет")) {
                double value = convertToNumber(manager.getGeneralVM().getAdditionalLead());
                String leadEquivalent = service.getOpeningsProtection(openingName, value, 0.25);
                dto.setThickness(leadEquivalent);
            } else {
                manager.getPanelVM().getThicknessProperty().set("Pb = 0.0 мм");
            }
        }
        manager.getPanelVM().fromDto(dto);
    }

    private boolean isValid() {
        return manager.getMedicineVM().getRadiationTypeViewModel().filled() &&
                manager.getGeneralVM().getSourceDataViewModel().filled();
    }

    private void updateVoltage() {
        voltage = manager.getVoltage();
    }

}
