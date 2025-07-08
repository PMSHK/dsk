package com.xrc.dsk.handlers.material;

import com.google.common.eventbus.Subscribe;
import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.dto.medicine.MatCharacteristicsDataDto;
import com.xrc.dsk.events.MatThicknessLeadEvent;

public class ThicknessLeadChangeHandler {
    private final ConnectionService connectionService = new ConnectionService();

    public ThicknessLeadChangeHandler() {
    }

    @Subscribe
    public void handle(MatThicknessLeadEvent event) {

        MatCharacteristicsDataDto dto = event.getDto();
        if (event.isThicknessBased()) {
            dto.setLeadEquivalent(calculate(dto, event.getVoltage()));
        } else {
            dto.setThickness(calculate(dto, event.getVoltage()));
        }
    }

    private double calculate(MatCharacteristicsDataDto dto, double voltage) {
        return connectionService.getMaterialCharacteristics(
                dto.getInfo().getName(), dto.getInfo().getDensity(),
                voltage, dto.getThickness(), dto.getLeadEquivalent());
    }
}
