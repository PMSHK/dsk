package com.xrc.dsk.services;

import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.data.DataStorage;
import com.xrc.dsk.data.MaterialDataBinder;
import com.xrc.dsk.dto.MaterialCharacteristicsDto;
import com.xrc.dsk.dto.MedWindowDto;
import com.xrc.dsk.dto.PanelDataDto;
import com.xrc.dsk.dto.medicine.MatCharacteristicsDataDto;
import com.xrc.dsk.viewModels.medicine.MatCharacteristicsDataViewModel;
import com.xrc.dsk.viewModels.medicine.MedicineDataViewModel;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MaterialPanelDataService {
    private MatCharacteristicsDataDto materialCharacteristicsDto;
    private ComboBox<String> materialComboBox;
    private TextField thicknessTextField;
    private Label leadEquivalentLabel;
    private DataStorage dataStorage = DataStorage.getInstance();
    private final ConnectionService connectionService;
    private int panelId;
    private MedicineDataViewModel viewModel;
    private MatCharacteristicsDataViewModel matVm;

    public MaterialPanelDataService(ComboBox<String> materialComboBox,
                                    TextField thicknessTextField,
                                    Label leadEquivalentLabel,
                                    MatCharacteristicsDataViewModel matVm,
                                    int panelId,
                                    MedicineDataViewModel viewModel
    ) {
        this.connectionService = new ConnectionService();
        this.materialComboBox = materialComboBox;
        this.thicknessTextField = thicknessTextField;
        this.leadEquivalentLabel = leadEquivalentLabel;
        this.matVm = matVm;
        this.panelId = panelId;
        this.viewModel = viewModel;
    }

    public void bind() {
        log.info("Binding to material panel data from service");

        MaterialDataBinder binder = new MaterialDataBinder(
                materialComboBox,
                thicknessTextField,
                leadEquivalentLabel,
                matVm,viewModel, panelId
        );
        log.info("Created new Material Binder");
        binder.bind();
        log.info("Bindings for material panel data from service has accomplished");
    }

    public void addNewMaterial(MatCharacteristicsDataDto newMaterialCharacteristicsDto) {
        log.info("Adding new material to material panel data from service");
        viewModel.getPanelDataProperty().get(panelId).getExistedMatCharacteristicsViewModelList().add(new MatCharacteristicsDataViewModel(newMaterialCharacteristicsDto));
        log.info("Added new material to material panel data from service");
    }

    public void deleteMaterial(MatCharacteristicsDataDto materialCharacteristicsDto) {
        log.info("Deleting existing material to material panel data from service");
        viewModel.getPanelDataProperty().get(panelId).getExistedMatCharacteristicsViewModelList().removeIf(vm->vm.toDto().equals(materialCharacteristicsDto));
    }

//    private MedWindowDto getMedWindowDto() {
//        MedWindowDto dto = (MedWindowDto) dataStorage.getWindowDto();
//        if (dto == null) {
//            dto = new MedWindowDto();
//            dataStorage.setWindowDto(dto);
//        }
//        return dto;
//    }
//    public void addNewPanel() {
//        MedWindowDto dto = getMedWindowDto();
//        dto.getPanelData().add(new PanelDataDto());
//    }
}
