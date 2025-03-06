package com.xrc.dsk.data;

import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.dto.MaterialCharacteristicsDto;
import com.xrc.dsk.dto.MedWindowDto;
import com.xrc.dsk.dto.PanelDataDto;
import com.xrc.dsk.dto.WindowDto;
import com.xrc.dsk.events.EventManager;
import com.xrc.dsk.events.MaterialEvent;
import com.xrc.dsk.listeners.MaterialPanelUpdateService;
import javafx.beans.binding.Bindings;
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
    private Label leadEquivalendLabel;
    private MaterialCharacteristicsDto materialCharacteristicsDto;
    private final ConnectionService connectionService;
    private int panelId;
    private Long voltage;

    public MaterialDataBinder(ComboBox<String> matBox,
                              TextField thicknessField,
                              Label leadEquivalendLabel,
                              MaterialCharacteristicsDto dto,
                              int panelId
    ) {
        this.thicknessField = thicknessField;
        this.leadEquivalendLabel = leadEquivalendLabel;
        this.materialCharacteristicsDto = dto;
        this.matBox = matBox;
        this.panelId = panelId;
        binder = new Binder();
        connectionService = new ConnectionService();
    }

    @Override
    public void bind(WindowDto dto) {
        log.info("Starting binding for material data");
        this.medWindowDto = (MedWindowDto) dto;
        PanelDataDto panelDataDto = medWindowDto.getPanelData().get(panelId);
        voltage = medWindowDto.getRadiationType().getVoltage();
        log.info("got voltage: {} kV", voltage);
        MaterialPanelUpdateService service = new MaterialPanelUpdateService(panelDataDto, matBox, thicknessField, leadEquivalendLabel);
        binder.bindDoublePropertyToString(thicknessField.textProperty(), materialCharacteristicsDto.thicknessProperty(), materialCharacteristicsDto.getThickness(),
                (val) -> {
                    this.voltage = medWindowDto.getRadiationType().getVoltage();
                    materialCharacteristicsDto.setThickness(val);
                    System.out.println("thickness: " + val + " has been saved");
                    if (voltage != null && voltage >= 50 && !materialCharacteristicsDto.getInfo().getName().isEmpty()) {
                        MaterialEvent event = new MaterialEvent(materialCharacteristicsDto, voltage);
                        EventManager.post(event);
                        log.info("Material event posted");
                    }
                });

        leadEquivalendLabel.textProperty().bind(Bindings.createStringBinding(
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
                    if (voltage != null && voltage >= 50 && !materialCharacteristicsDto.getInfo().getName().isEmpty()) {
                        MaterialEvent event = new MaterialEvent(materialCharacteristicsDto, voltage);
                        EventManager.post(event);
                    }
                }
            }
        });
    }

}
