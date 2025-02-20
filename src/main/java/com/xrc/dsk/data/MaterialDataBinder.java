package com.xrc.dsk.data;

import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.dto.MaterialCharacteristicsDto;
import com.xrc.dsk.dto.MaterialDto;
import com.xrc.dsk.dto.MedWindowDto;
import com.xrc.dsk.dto.WindowDto;
import javafx.beans.property.LongProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MaterialDataBinder implements Bindable{
    private final Binder binder;
    private MedWindowDto medWindowDto;
    private TextField thicknessField;
    private Label leadEquivalendLabel;
    private MaterialDto materialDtoDto;
    private final ConnectionService connectionService;

    public MaterialDataBinder(TextField thicknessField, Label leadEquivalendLabel, MaterialDto dto) {
        this.thicknessField = thicknessField;
        this.leadEquivalendLabel = leadEquivalendLabel;
        this.materialDtoDto = dto;
        binder = new Binder();
        connectionService = new ConnectionService();
    }

    @Override
    public void bind(WindowDto dto) {
        this.medWindowDto = (MedWindowDto) dto;
        LongProperty voltageProperty = medWindowDto
                .getRadiationType()
                .voltageProperty();

        binder.bindDoublePropertyToString(thicknessField.textProperty(), materialDtoDto.thicknessProperty(), Double.parseDouble(thicknessField.getText()),
                (val) -> {
                    materialDtoDto.setThickness(val);
                    System.out.println("thickness: " + val + " has been saved");
                    MaterialCharacteristicsDto materialInfoDto = connectionService.getMaterialCharacteristics(
                            materialDtoDto.getInfo().getName(), materialDtoDto.getInfo().getDensity(),
                            medWindowDto.getRadiationType().getVoltage(), materialDtoDto.getThickness(), 0
                    );
                    double leadEquivalent = materialInfoDto.getLeadEquivalent();
                    materialDtoDto.setLeadEquivalent(leadEquivalent);
                    leadEquivalendLabel.setText(String.valueOf(leadEquivalent));
                    System.out.println("lead equivalent: " + leadEquivalent + " has been saved");
//                    updatePanelsProtection();
                });

    }
}
