package com.xrc.dsk.data;

import com.xrc.dsk.connection.ConnectionService;
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
    private ComboBox<String> matBox;
    private TextField thicknessField;
    private Label label;
    private final ConnectionService connectionService;
    private int panelId;
    private Long voltage;
    private boolean basedOnThickness;
    private ChangeListener<String> additionalListener;
    private MedicineDataViewModel viewModel;
    private MatCharacteristicsDataViewModel matVm;
    private PanelDataViewModel panelDataVM;

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
        MatCharacteristicsDataDto materialCharacteristicsDto = new MatCharacteristicsDataDto(materialInfoDataDto, 0D, 0D);
        this.matVm = new MatCharacteristicsDataViewModel(materialCharacteristicsDto);
        binder = new Binder();
        connectionService = new ConnectionService();
        this.basedOnThickness = false;
    }

    @Override
    public void bind() {
        MaterialPanelUpdateService service = new MaterialPanelUpdateService(matBox, thicknessField, label, basedOnThickness, viewModel);
        initializeBindings(basedOnThickness);
    }

    private void createEvent(String newVal, PanelDataViewModel panelDataVM) {
        if (!label.getText().isEmpty()) {
            if (newVal != null) {
                this.voltage = viewModel.getRadiationTypeViewModel().getVoltage();
                String[] matParameters = newVal.split(" ");
                String name = matParameters[0];
                double density = Double.parseDouble(matParameters[1]);

                MaterialInfoDataDto infoDto = new MaterialInfoDataDto(name, (float) density);
                MatCharacteristicsDataDto dto = new MatCharacteristicsDataDto(infoDto, 0D, panelDataVM.toDto().getDoubleValueAdditionalLead());
                log.info("matVm hash before: {}", matVm.hashCode());
                matVm.fromDto(dto);
                log.info("matVm hash after: {}", matVm.hashCode());

                if (voltage != null && voltage >= 50 && !dto.getInfo().getName().isEmpty()) {
                    UpdateMatEvent event = new UpdateMatEvent(matVm);
                    EventManager.post(event);
                }
            }
        }
    }

    private void setupThicknessBasedBindings() {
        setupThicknessFieldBinding();
        setupMaterialSelectionListener();
        setupLeadEquivalentLabel();
    }

    private void setupLeadBasedBindings() {
        setupAdditionalLeadListener();
        setupThicknessLabel();
        setupMaterialSelectionListener();
    }

    public void initializeBindings(boolean basedOnThickness) {
        initVoltage();
        panelDataVM = viewModel.getPanelDataProperty().get(panelId);

        if (basedOnThickness) {
            setupThicknessBasedBindings();
        } else {
            setupLeadBasedBindings();
        }
    }

    private void setupThicknessFieldBinding() {
        binder.bindDoublePropertyToString(
                thicknessField.textProperty(),
                matVm.getThicknessProperty(),
                matVm.getThickness(),
                this::handleThicknessChange
        );
    }

    private void handleThicknessChange(Double newThickness) {
        matVm.getThicknessProperty().set(newThickness);
        matVm.getLeadEquivalentProperty().set(0D);

        if (shouldTriggerEvent()) {
            EventManager.post(new MaterialEvent(matVm));
        }
    }

    private void setupMaterialSelectionListener() {
        matBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                updateMaterialInfo(newVal);
                if (shouldTriggerEvent()) {
                    EventManager.post(new MaterialEvent(matVm));
                }
            }
        });
    }

    private void updateMaterialInfo(String materialData) {
        String[] parts = materialData.split(" ");
        MaterialInfoDataDto info = new MaterialInfoDataDto(
                parts[0],
                Float.parseFloat(parts[1])
        );

        MatCharacteristicsDataDto dto = new MatCharacteristicsDataDto(
                info,
                basedOnThickness ? Double.parseDouble(thicknessField.getText()) : 0D,
                basedOnThickness ? 0D : panelDataVM.toDto().getDoubleValueAdditionalLead()
        );

        matVm.fromDto(dto);
    }

    private void setupLeadEquivalentLabel() {
        label.textProperty().bind(Bindings.createStringBinding(
                () -> String.format("%.2f", matVm.getLeadEquivalent()),
                matVm.getLeadEquivalentProperty()
        ));
    }

    private void setupAdditionalLeadListener() {
        panelDataVM.getAdditionalLeadViewModelProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && !newVal.equals(oldVal)) {
                createEvent(matBox.getSelectionModel().getSelectedItem(), panelDataVM);
            }
        });


        panelDataVM.getAdditionalLeadViewModelProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && !newVal.equals(oldVal)) {
                String selectedMaterial = matBox.getSelectionModel().getSelectedItem();
                if (selectedMaterial != null) {
                    updateMaterialInfo(selectedMaterial);
                }

                if (shouldTriggerEvent()) {
                    EventManager.post(new MaterialEvent(matVm));
                }
            }
        });
    }

    private void setupThicknessLabel() {
        label.textProperty().bind(Bindings.createStringBinding(
                () -> String.format("%.2f", matVm.getThickness()),
                matVm.getThicknessProperty(),
                panelDataVM.getAdditionalLeadViewModelProperty()
        ));
    }

    private boolean shouldTriggerEvent() {
        initVoltage();
        return voltage != null &&
                voltage >= 50 &&
                !matVm.getInfo().getName().isEmpty() &&
                (basedOnThickness ? matVm.getThickness() > 0 : true);
    }

    private void initVoltage() {
        this.voltage = viewModel.getRadiationTypeViewModel().getVoltage();
    }
}
