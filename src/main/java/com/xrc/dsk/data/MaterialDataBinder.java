package com.xrc.dsk.data;

import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.dto.MaterialCharacteristicsDto;
import com.xrc.dsk.dto.MaterialInfoDto;
import com.xrc.dsk.dto.MedWindowDto;
import com.xrc.dsk.dto.medicine.PanelDataDto;
import com.xrc.dsk.dto.WindowDto;
import com.xrc.dsk.dto.medicine.MatCharacteristicsDataDto;
import com.xrc.dsk.dto.medicine.MaterialInfoDataDto;
import com.xrc.dsk.events.EventManager;
import com.xrc.dsk.events.MaterialEvent;
import com.xrc.dsk.events.UpdateMatEvent;
import com.xrc.dsk.listeners.MaterialPanelUpdateService;
import com.xrc.dsk.viewModels.medicine.MatCharacteristicsDataViewModel;
import com.xrc.dsk.viewModels.medicine.MedicineDataViewModel;
import com.xrc.dsk.viewModels.medicine.PanelDataViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MaterialDataBinder implements Bindable {
    private final Binder binder;
//    private MedWindowDto medWindowDto;
    private ComboBox<String> matBox;
    private TextField thicknessField;
    private Label label;
//    private MatCharacteristicsDataDto materialCharacteristicsDto;
    private final ConnectionService connectionService;
    private int panelId;
    private Long voltage;
    private boolean basedOnThickness;
    private ChangeListener<String> additionalListener;
    private MedicineDataViewModel viewModel;
    private MatCharacteristicsDataViewModel matVm;

    public MaterialDataBinder(ComboBox<String> matBox,
                              TextField thicknessField,
                              Label leadEquivalendLabel,
                              MatCharacteristicsDataViewModel matVm,
                              MedicineDataViewModel viewModel,
                              int panelId
    ) {
        this.thicknessField = thicknessField;
        this.label = leadEquivalendLabel;
        this.matBox = matBox;
        this.panelId = panelId;
//        this.materialCharacteristicsDto = materialCharacteristicsDto;
        this.viewModel = viewModel;
        this.matVm = matVm;
        binder = new Binder();
        connectionService = new ConnectionService();
        this.basedOnThickness = true;
    }

    public MaterialDataBinder(ComboBox<String> matBox,
                              Label thicknessLabel,
                              int panelId, MedicineDataViewModel viewModel
    ) {
        this.matBox = matBox;
        this.label = thicknessLabel;
        this.panelId = panelId;
        this.viewModel = viewModel;
        MaterialInfoDataDto materialInfoDataDto = new MaterialInfoDataDto();
        MatCharacteristicsDataDto materialCharacteristicsDto = new MatCharacteristicsDataDto(materialInfoDataDto,0D,0D);
        this.matVm = new MatCharacteristicsDataViewModel(materialCharacteristicsDto);
        binder = new Binder();
        connectionService = new ConnectionService();
        this.basedOnThickness = false;
    }

    @Override
    public void bind() {
        log.info("Starting binding for material data");
        PanelDataViewModel panelDataVM = viewModel.getPanelDataProperty().get(panelId);
        voltage = viewModel.getRadiationTypeViewModel().getVoltage();
        log.info("got voltage: {} kV", voltage);
        MaterialPanelUpdateService service = new MaterialPanelUpdateService(panelDataVM.toDto(), matBox, thicknessField, label, basedOnThickness);

        if (basedOnThickness) {
            binder.bindDoublePropertyToString(thicknessField.textProperty(), matVm.getThicknessProperty(), matVm.getThickness(),
                    (val) -> {
                MatCharacteristicsDataDto dto = matVm.toDto();
                        this.voltage = viewModel.getRadiationTypeViewModel().getVoltage();
                        dto.setThickness(val);
                        dto.setLeadEquivalent(0D);
                        matVm.fromDto(dto);
                        System.out.println("thickness: " + val + " has been saved");
                        if (voltage != null && voltage >= 50 && !dto.getInfo().getName().isEmpty()) {
                            MaterialEvent event = new MaterialEvent(dto, voltage, basedOnThickness);
                            EventManager.post(event);
                            log.info("Material event posted");
                        }
                    });
            label.textProperty().bind(Bindings.createStringBinding(
                    () -> matVm.toDto() != null ? String.format("%.2f", matVm.getLeadEquivalentProperty().get()) : "0.0",
                    matVm.getLeadEquivalentProperty()
            ));

            matBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal != null) {
                    if (!thicknessField.getText().isEmpty()) {
                        this.voltage = viewModel.getRadiationTypeViewModel().getVoltage();
                        String[] matParameters = newVal.split(" ");
                        String name = matParameters[0];
                        double density = Double.parseDouble(matParameters[1]);
                        MatCharacteristicsDataDto dto = matVm.toDto();
                        dto.getInfo().setName(name);
                        dto.getInfo().setDensity((float) density);
                        dto.setThickness(Double.parseDouble(thicknessField.getText()));
                        dto.setLeadEquivalent(0D);
                        matVm.fromDto(dto);
                        if (voltage != null && voltage >= 50 && !dto.getInfo().getName().isEmpty()) {
                            MaterialEvent event = new MaterialEvent(dto, voltage, basedOnThickness);
                            EventManager.post(event);
                        }
                    }
                }
            });
        } else {
            panelDataVM.getAdditionalLeadViewModelProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal != null && !newVal.equals(oldVal)) {
                    createEvent(matBox.getSelectionModel().getSelectedItem(), panelDataVM);
                }
            });

            label.textProperty().bind(Bindings.createStringBinding(
                    () -> {
                        MatCharacteristicsDataDto dto = matVm.toDto();
                        if (dto != null) {
                            double thickness = matVm.getThicknessProperty().get();
                            return String.format("%.2f", thickness);
                        }
                        return "0.0";
                    },
                    panelDataVM.getAdditionalLeadViewModelProperty(), // Триггер для пересчёта
                    matVm.getThicknessProperty() // Новое значение из базы
            ));

            matBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal != null) {
                    createEvent(newVal, panelDataVM);
                }
            });
        }
    }

    private void createEvent(String newVal, PanelDataViewModel panelDataVM) {
        if (!label.getText().isEmpty()) {
            if (newVal != null) {
                this.voltage = viewModel.getRadiationTypeViewModel().getVoltage();
                String[] matParameters = newVal.split(" ");
                String name = matParameters[0];
                double density = Double.parseDouble(matParameters[1]);
                MatCharacteristicsDataDto dto = matVm.toDto();
                dto.getInfo().setName(name);
                dto.getInfo().setDensity((float) density);
                dto.setThickness(0D);
                dto.setLeadEquivalent(panelDataVM.toDto().getDoubleValueAdditionalLead());
                if (voltage != null && voltage >= 50 && !dto.getInfo().getName().isEmpty()) {
                    UpdateMatEvent event = new UpdateMatEvent(dto, voltage, basedOnThickness);
                    EventManager.post(event);
                }
            }
        }
    }
}
