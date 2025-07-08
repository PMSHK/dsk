package com.xrc.dsk.services.material;

import com.google.common.eventbus.EventBus;
import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.converters.NumbersFormatter;
import com.xrc.dsk.dto.medicine.MatCharacteristicsDataDto;
import com.xrc.dsk.dto.medicine.MatParamDto;
import com.xrc.dsk.dto.medicine.MaterialInfoDataDto;
import com.xrc.dsk.events.EventManager;
import com.xrc.dsk.events.MatThicknessLeadEvent;
import com.xrc.dsk.handlers.material.ThicknessLeadChangeHandler;
import com.xrc.dsk.viewModels.medicine.MatCharacteristicsDataViewModel;
import com.xrc.dsk.viewModels.medicine.MatInfoViewModel;
import com.xrc.dsk.viewModels.medicine.PanelDataViewModel;

import static com.xrc.dsk.converters.StringConverter.convertToNumber;

public class MatCalcService {
    private PanelDataViewModel panelDataVM;
    private final MatCharacteristicsDataViewModel viewModel;
    private double voltage;
    private boolean basedOnThickness;
    private final ThicknessLeadChangeHandler handler = new ThicknessLeadChangeHandler();
    private final ConnectionService connectionService = new ConnectionService();
    private final MatValidationService validator;

    public MatCalcService(final PanelDataViewModel panelDataVM,
                          final MatCharacteristicsDataViewModel viewModel,
                          double voltage, boolean basedOnThickness) {
        this.panelDataVM = panelDataVM;
        this.viewModel = viewModel;
        this.voltage = voltage;
        this.basedOnThickness = basedOnThickness;
        this.validator = new MatValidationService(voltage,viewModel);
    }

    private MatCharacteristicsDataDto updateMaterialCharacteristics(String materialData) {
        String[] parts = materialData.split(" ");
        String name = parts[0];
        float density = NumbersFormatter.formatWithPrecision(Float.parseFloat(parts[1]), 2).floatValue();
        MatParamDto dto = new MatParamDto();
        if (basedOnThickness) {
            dto.setThickness(viewModel.getThickness());
        } else {
            dto.setLeadEquivalent(convertToNumber(panelDataVM.getAdditionalLead()));
        }

        return calculateThicknessOrLeadValues(dto, name, density);
    }

    private MatCharacteristicsDataDto calculateThicknessOrLeadValues(MatParamDto dto, String name, float density) {
        MatCharacteristicsDataDto matDto = new MatCharacteristicsDataDto();
        MaterialInfoDataDto infoDto = new MaterialInfoDataDto(name, density);
        viewModel.getInfo().fromDto(infoDto);
        matDto.setInfo(infoDto);
        matDto.setThickness(dto.getThickness());
        matDto.setLeadEquivalent(dto.getLeadEquivalent());
        if (isValidData() && (!basedOnThickness || viewModel.getThickness() > 0)) {
            EventManager.post(new MatThicknessLeadEvent(matDto, voltage, dto.isThicknessBased()));
        }
        return matDto;
    }

    public boolean isValidData() {
        return voltage >= 50 && viewModel.getInfo().isFilled();
    }

    public void handleThicknessChange(Double newThickness) {
        MatInfoViewModel info = viewModel.getInfo();
        double leadEq = 0.0;
        if (isValidData()) {
            leadEq = connectionService.getMaterialCharacteristics(
                    info.getName(), info.getDensity(),
                    voltage, newThickness, 0D);
        } else {
            return;
        }
        viewModel.getLeadEquivalentProperty().set(leadEq);
    }

}
