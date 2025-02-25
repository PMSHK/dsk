package com.xrc.dsk.services;

import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.data.DataStorage;
import com.xrc.dsk.data.MaterialDataBinder;
import com.xrc.dsk.dto.MaterialCharacteristicsDto;
import com.xrc.dsk.dto.MedWindowDto;
import com.xrc.dsk.dto.PanelDataDto;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MaterialPanelDataService {
    private MaterialCharacteristicsDto materialCharacteristicsDto;
    private ComboBox<String> materialComboBox;
    private TextField thicknessTextField;
    private Label leadEquivalentLabel;
    private DataStorage dataStorage = DataStorage.getInstance();
    private final ConnectionService connectionService;
    private int panelId;

    public MaterialPanelDataService(ComboBox<String> materialComboBox,
                                    TextField thicknessTextField,
                                    Label leadEquivalentLabel,
                                    MaterialCharacteristicsDto dto,
                                    int panelId
    ) {
        this.connectionService = new ConnectionService();
        this.materialComboBox = materialComboBox;
        this.thicknessTextField = thicknessTextField;
        this.leadEquivalentLabel = leadEquivalentLabel;
        this.materialCharacteristicsDto = dto;
        this.panelId = panelId;
    }

    public void bind() {
        log.info("Binding to material panel data from service");
        MedWindowDto dto = getMedWindowDto();

        MaterialDataBinder binder = new MaterialDataBinder(
                materialComboBox,
                thicknessTextField,
                leadEquivalentLabel,
                materialCharacteristicsDto, panelId
        );
        log.info("Created new Material Binder");
        binder.bind(dto);
        log.info("Bindings for material panel data from service has accomplished");
    }


//    public void bind() {
//        MedWindowDto dto = getMedWindowDto();
//        String[] matParameters = materialComboBox.getSelectionModel().getSelectedItem().split(" ");
//        String name = matParameters[0];
//        double density = Double.parseDouble(matParameters[1]);
//        double thickness = Double.parseDouble(thicknessTextField.getText());
//        MaterialCharacteristicsDto materialInfoDto = connectionService.getMaterialCharacteristics(
//                name, density, dto.getRadiationType().getVoltage().doubleValue(), thickness, 0
//        );
//        materialDto.setThickness(materialInfoDto.getThickness());
//        materialDto.setLeadEquivalent(materialInfoDto.getLeadEquivalent());
//        materialDto.getInfo().setDensity((float) density);
//        materialDto.getInfo().setName(name);
//        addNewMaterial(materialDto);
//        MaterialDataBinder binder = new MaterialDataBinder(thicknessTextField, leadEquivalentLabel, materialDto);
//        binder.bind(dto);
//    }

    public void addNewMaterial(MaterialCharacteristicsDto newMaterialCharacteristicsDto) {
        log.info("Adding new material to material panel data from service");
        MedWindowDto dto = getMedWindowDto();
        dto.getPanelData().get(panelId).getExistedMaterialCharacteristicsDtoList().add(newMaterialCharacteristicsDto);
        log.info("Added new material to material panel data from service");
    }

    public void deleteMaterial(MaterialCharacteristicsDto materialCharacteristicsDto) {
        MedWindowDto dto = getMedWindowDto();
        dto.getPanelData().get(panelId).getExistedMaterialCharacteristicsDtoList().remove(materialCharacteristicsDto);
    }

    private MedWindowDto getMedWindowDto() {
        MedWindowDto dto = (MedWindowDto) dataStorage.getWindowDto();
        if (dto == null) {
            dto = new MedWindowDto();
            dataStorage.setWindowDto(dto);
        }
        return dto;
    }
    public void addNewPanel() {
        MedWindowDto dto = getMedWindowDto();
        dto.getPanelData().add(new PanelDataDto());
    }
}
