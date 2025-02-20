package com.xrc.dsk.services;

import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.data.DataStorage;
import com.xrc.dsk.data.MaterialDataBinder;
import com.xrc.dsk.dto.MaterialCharacteristicsDto;
import com.xrc.dsk.dto.MaterialDto;
import com.xrc.dsk.dto.MaterialInfoDto;
import com.xrc.dsk.dto.MedWindowDto;
import com.xrc.dsk.panels.CalculationPanel;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class MaterialPanelDataService {
    private MaterialDto materialDto;
    private ComboBox<String> materialComboBox;
    private TextField thicknessTextField;
    private Label leadEquivalentLabel;
    private DataStorage dataStorage = DataStorage.getInstance();
    private final ConnectionService connectionService;
    private int panelId;

    public MaterialPanelDataService(ComboBox<String> materialComboBox,
                                    TextField thicknessTextField,
                                    Label leadEquivalentLabel,
                                    MaterialDto dto,
                                    int panelId
    ) {
        this.connectionService = new ConnectionService();
        this.materialComboBox = materialComboBox;
        this.thicknessTextField = thicknessTextField;
        this.leadEquivalentLabel = leadEquivalentLabel;
        this.materialDto = dto;
        this.panelId=panelId;
    }


    public void bind() {
        MedWindowDto dto = getMedWindowDto();
        String[] matParameters = materialComboBox.getSelectionModel().getSelectedItem().split(" ");
        String name = matParameters[0];
        double density = Double.parseDouble(matParameters[1]);
        double thickness = Double.parseDouble(thicknessTextField.getText());
        MaterialCharacteristicsDto materialInfoDto = connectionService.getMaterialCharacteristics(
                name, density, dto.getRadiationType().getVoltage().doubleValue(), thickness, 0
        );
        materialDto.setThickness(materialInfoDto.getThickness());
        materialDto.setLeadEquivalent(materialInfoDto.getLeadEquivalent());
        materialDto.getInfo().setDensity((float) density);
        materialDto.getInfo().setName(name);
        addNewMaterial(materialDto);
        MaterialDataBinder binder = new MaterialDataBinder(thicknessTextField, leadEquivalentLabel, materialDto);
        binder.bind(dto);
    }

    public void addNewMaterial(MaterialDto newMaterialDto) {
        MedWindowDto dto = getMedWindowDto();
        dto.getPanelData().get(panelId).getExistedMaterialDtoList().add(newMaterialDto);
    }

    private MedWindowDto getMedWindowDto() {
        MedWindowDto dto = (MedWindowDto) dataStorage.getWindowDto();
        if (dto == null) {
            dto = new MedWindowDto();
            dataStorage.setWindowDto(dto);
        }
        return dto;
    }
}
