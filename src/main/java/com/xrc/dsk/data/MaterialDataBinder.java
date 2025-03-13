package com.xrc.dsk.data;

import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.dto.MaterialCharacteristicsDto;
import com.xrc.dsk.dto.MaterialInfoDto;
import com.xrc.dsk.dto.MedWindowDto;
import com.xrc.dsk.dto.PanelDataDto;
import com.xrc.dsk.dto.WindowDto;
import com.xrc.dsk.events.EventManager;
import com.xrc.dsk.events.MaterialEvent;
import com.xrc.dsk.events.UpdateMatEvent;
import com.xrc.dsk.listeners.MaterialPanelUpdateService;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MaterialDataBinder implements Bindable {
    private final Binder binder;
    private MedWindowDto medWindowDto;
    private ComboBox<String> matBox;
    private TextField thicknessField;
    private Label label;
    private MaterialCharacteristicsDto materialCharacteristicsDto;
    private final ConnectionService connectionService;
    private int panelId;
    private Long voltage;
    private boolean basedOnThickness;
    private ChangeListener<String> additionalListener;

    public MaterialDataBinder(ComboBox<String> matBox,
                              TextField thicknessField,
                              Label leadEquivalendLabel,
                              MaterialCharacteristicsDto dto,
                              int panelId
    ) {
        this.thicknessField = thicknessField;
        this.label = leadEquivalendLabel;
        this.materialCharacteristicsDto = dto;
        this.matBox = matBox;
        this.panelId = panelId;
        binder = new Binder();
        connectionService = new ConnectionService();
        this.basedOnThickness = true;
    }

    public MaterialDataBinder(ComboBox<String> matBox,
                              Label thicknessLabel,
                              int panelId
    ) {
        this.matBox = matBox;
        this.label = thicknessLabel;
        this.panelId = panelId;
        MaterialInfoDto materialInfoDto = new MaterialInfoDto();
        this.materialCharacteristicsDto = new MaterialCharacteristicsDto();
        materialCharacteristicsDto.setInfo(materialInfoDto);
        binder = new Binder();
        connectionService = new ConnectionService();
        this.basedOnThickness = false;
    }

    @Override
    public void bind(WindowDto dto) {
        log.info("Starting binding for material data");
        this.medWindowDto = (MedWindowDto) dto;
        PanelDataDto panelDataDto = medWindowDto.getPanelData().get(panelId);
        voltage = medWindowDto.getRadiationType().getVoltage();
        log.info("got voltage: {} kV", voltage);
        MaterialPanelUpdateService service = new MaterialPanelUpdateService(panelDataDto, matBox, thicknessField, label, basedOnThickness);

        if (basedOnThickness) {
            binder.bindDoublePropertyToString(thicknessField.textProperty(), materialCharacteristicsDto.thicknessProperty(), materialCharacteristicsDto.getThickness(),
                    (val) -> {
                        this.voltage = medWindowDto.getRadiationType().getVoltage();
                        materialCharacteristicsDto.setThickness(val);
                        materialCharacteristicsDto.setLeadEquivalent(0);
                        System.out.println("thickness: " + val + " has been saved");
                        if (voltage != null && voltage >= 50 && !materialCharacteristicsDto.getInfo().getName().isEmpty()) {
                            MaterialEvent event = new MaterialEvent(materialCharacteristicsDto, voltage, basedOnThickness);
                            EventManager.post(event);
                            log.info("Material event posted");
                        }
                    });
            label.textProperty().bind(Bindings.createStringBinding(
                    () -> materialCharacteristicsDto != null ? String.format("%.2f", materialCharacteristicsDto.leadEquivalentProperty().get()) : "0.0",
                    materialCharacteristicsDto.leadEquivalentProperty()
            ));

            matBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal != null) {
                    if (!thicknessField.getText().isEmpty()) {
                        this.voltage = medWindowDto.getRadiationType().getVoltage();
                        String[] matParameters = newVal.split(" ");
                        String name = matParameters[0];
                        double density = Double.parseDouble(matParameters[1]);
                        materialCharacteristicsDto.getInfo().setName(name);
                        materialCharacteristicsDto.getInfo().setDensity((float) density);
                        materialCharacteristicsDto.setThickness(Double.parseDouble(thicknessField.getText()));
                        materialCharacteristicsDto.setLeadEquivalent(0);
                        if (voltage != null && voltage >= 50 && !materialCharacteristicsDto.getInfo().getName().isEmpty()) {
                            MaterialEvent event = new MaterialEvent(materialCharacteristicsDto, voltage, basedOnThickness);
                            EventManager.post(event);
                        }
                    }
                }
            });
        } else {
            panelDataDto.getAdditionalLead().addListener((obs, oldVal, newVal) -> {
                if (newVal != null && !newVal.equals(oldVal)) {
                    createEvent(matBox.getSelectionModel().getSelectedItem(), panelDataDto);
                }
            });

            label.textProperty().bind(Bindings.createStringBinding(
                    () -> {
                        if (materialCharacteristicsDto != null) {
                            double thickness = materialCharacteristicsDto.thicknessProperty().get();
                            return String.format("%.2f", thickness);
                        }
                        return "0.0";
                    },
                    panelDataDto.getAdditionalLead(), // Триггер для пересчёта
                    materialCharacteristicsDto.thicknessProperty() // Новое значение из базы
            ));

            matBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal != null) {
                    createEvent(newVal, panelDataDto);
                }
            });
        }
    }

    private void createEvent(String newVal, PanelDataDto panelDataDto) {
        if (!label.getText().isEmpty()) {
            if (newVal != null) {
                this.voltage = medWindowDto.getRadiationType().getVoltage();
                String[] matParameters = newVal.split(" ");
                String name = matParameters[0];
                double density = Double.parseDouble(matParameters[1]);
                materialCharacteristicsDto.getInfo().setName(name);
                materialCharacteristicsDto.getInfo().setDensity((float) density);
                materialCharacteristicsDto.setThickness(0);
                materialCharacteristicsDto.setLeadEquivalent(panelDataDto.getDoubleValueAdditionalLead());
                if (voltage != null && voltage >= 50 && !materialCharacteristicsDto.getInfo().getName().isEmpty()) {
                    UpdateMatEvent event = new UpdateMatEvent(materialCharacteristicsDto, voltage, basedOnThickness);
                    EventManager.post(event);
                }
            }
        }
    }
}
