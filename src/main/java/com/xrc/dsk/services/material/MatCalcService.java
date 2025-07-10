package com.xrc.dsk.services.material;

import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.converters.NumbersFormatter;
import com.xrc.dsk.dto.medicine.MatCharacteristicsDataDto;
import com.xrc.dsk.dto.medicine.MatParamDto;
import com.xrc.dsk.dto.medicine.MaterialInfoDataDto;
import com.xrc.dsk.events.EventManager;
import com.xrc.dsk.events.MatThicknessLeadEvent;
import com.xrc.dsk.handlers.material.ThicknessLeadChangeHandler;
import com.xrc.dsk.viewModels.medicine.MatInfoViewModel;
import com.xrc.dsk.viewModels.medicine.MedicineDataViewModel;
import lombok.extern.slf4j.Slf4j;

import static com.xrc.dsk.converters.StringConverter.convertToNumber;
@Slf4j
public class MatCalcService {
    private final MaterialViewModelManager vmManager;
    private final MedicineDataViewModel medVM;
    private double voltage;
    private boolean basedOnThickness;
    private final ThicknessLeadChangeHandler handler = new ThicknessLeadChangeHandler();
    private final ConnectionService connectionService = new ConnectionService();

    private boolean suppressEvents = false;

    public MatCalcService(
            MaterialViewModelManager vmManager,
            MedicineDataViewModel medVM, boolean basedOnThickness) {
        this.vmManager = vmManager;
        this.medVM = medVM;
        this.basedOnThickness = basedOnThickness;
        EventManager.register(handler);
        updateVoltage();

    }

    private MatCharacteristicsDataDto updateMaterialCharacteristics(String materialData) {
        updateVoltage();
        String[] parts = materialData.split(" ");
        String name = parts[0];
        float density = NumbersFormatter.formatWithPrecision(Float.parseFloat(parts[1]), 2).floatValue();
        MatParamDto dto = new MatParamDto();
        if (basedOnThickness) {
            dto.setThickness(vmManager.getMatVM().getThickness());
        } else {
            dto.setLeadEquivalent(convertToNumber(vmManager.getPanelVM().getAdditionalLead()));
        }

        return calculateThicknessOrLeadValues(dto, name, density);
    }

    private MatCharacteristicsDataDto calculateThicknessOrLeadValues(MatParamDto dto, String name, float density) {
        updateVoltage();
        MatCharacteristicsDataDto matDto = new MatCharacteristicsDataDto();
        MaterialInfoDataDto infoDto = new MaterialInfoDataDto(name, density);
        vmManager.getMatVM().getInfo().fromDto(infoDto);
        matDto.setInfo(infoDto);
        matDto.setThickness(dto.getThickness());
        matDto.setLeadEquivalent(dto.getLeadEquivalent());
        if (isValidData() && (!basedOnThickness || vmManager.getMatVM().getThickness() > 0)) {
            if((dto.getThickness() > 0) ^ (dto.getLeadEquivalent() > 0)) {
                EventManager.post(new MatThicknessLeadEvent(matDto, voltage, dto.isThicknessBased()));
            }
//            MatInfoViewModel info = vmManager.getMatVM().getInfo();
//            double thickness = 0.0;
//            if (isValidData()) {
//                thickness = connectionService.getMaterialCharacteristics(
//                        info.getName(), info.getDensity(),
//                        voltage, 0.0, matDto.getLeadEquivalent());
//            }
//            matDto.setThickness(thickness);
        }
        return matDto;
    }

    public boolean isValidData() {
        return voltage >= 50 && vmManager.getMatVM().getInfo().isFilled();
    }

    public void handleThicknessChange(Double newThickness) {
        updateVoltage();
        MatInfoViewModel info = vmManager.getMatVM().getInfo();
        double leadEq = 0.0;
        if (isValidData()) {
            leadEq = connectionService.getMaterialCharacteristics(
                    info.getName(), info.getDensity(),
                    voltage, newThickness, 0D);
        } else {
            log.warn("Invalid data: voltage = {}, name = {}, density = {}",
                    voltage, info.getName(), info.getDensity());
            return;
        }
        vmManager.getMatVM().getLeadEquivalentProperty().set(leadEq);
    }

    public double updateMaterialInfo(String materialData) {
        updateVoltage();
        if (materialData == null || materialData.isBlank()) return 0.0;
        MatCharacteristicsDataDto dto = updateMaterialCharacteristics(materialData);
        suppressEvents = true;
        try {
        vmManager.getMatVM().fromDto(dto);
        if (basedOnThickness) {
            return dto.getThickness();
        }
        return 0.0;
        } finally {
            suppressEvents = false;
        }
    }

    private void updateVoltage(){
        this.voltage = medVM.getRadiationTypeViewModel().getVoltage();
    }

}
