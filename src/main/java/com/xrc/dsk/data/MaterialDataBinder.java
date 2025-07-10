package com.xrc.dsk.data;

import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.converters.NumbersFormatter;
import com.xrc.dsk.dto.medicine.MatCharacteristicsDataDto;
import com.xrc.dsk.dto.medicine.MatParamDto;
import com.xrc.dsk.dto.medicine.MaterialInfoDataDto;
import com.xrc.dsk.listeners.MaterialPanelUpdateService;
import com.xrc.dsk.services.material.MatBindingManager;
import com.xrc.dsk.services.material.MatCalcService;
import com.xrc.dsk.services.material.MaterialViewModelManager;
import com.xrc.dsk.viewModels.medicine.MatCharacteristicsDataViewModel;
import com.xrc.dsk.viewModels.medicine.MatInfoViewModel;
import com.xrc.dsk.viewModels.medicine.MedicineDataViewModel;
import com.xrc.dsk.viewModels.medicine.PanelDataViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;

import static com.xrc.dsk.converters.StringConverter.convertToNumber;

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
    private boolean suppressEvents = false;

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

    public void initializeBindings(boolean basedOnThickness) {
        initVoltage();
        panelDataVM = viewModel.getPanelDataProperty().get(panelId);

        if (basedOnThickness) {
            setupThicknessBasedBindings();
        } else {
            setupLeadBasedBindings();
        }
    }

    private void setupThicknessBasedBindings() {
        setupThicknessFieldBinding();
        setupMaterialSelectionListener();
        setupLeadEquivalentLabel();
    }

    private void setupLeadBasedBindings() {
        setupThicknessLabel();
        setupMaterialSelectionListener();
    }


    private void setupThicknessFieldBinding() {
        thicknessField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.isEmpty() && !newValue.equals(oldValue)) {
                matVm.getThicknessProperty().set(Double.parseDouble(newValue));
            }
        });

        matVm.getThicknessProperty().addListener((obs, oldVal, newVal) -> {
            if (suppressEvents) return;
            suppressEvents = true;
            try {
                handleThicknessChange(newVal.doubleValue());
            } finally {
                suppressEvents = false;
            }
        });
    }

    private void handleThicknessChange(Double newThickness) {
        MatInfoViewModel info = matVm.getInfo();
        if (info == null || voltage == null || voltage <= 0) return;
        double leadEq = 0.0;
        if (validData()) {
            leadEq = connectionService.getMaterialCharacteristics(
                    info.getName(), info.getDensity(),
                    voltage, newThickness, 0D);
        }
        matVm.getLeadEquivalentProperty().set(leadEq);
    }

    private void setupMaterialSelectionListener() {
        matBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && !newVal.equals(oldVal)) {
                updateMaterialInfo(newVal);
            }
        });
    }

    private void updateMaterialInfo(String materialData) {
        if (materialData == null || materialData.isBlank()) return;
        MatCharacteristicsDataDto dto = calculateThicknessLeadValues(materialData);
        suppressEvents = true;
        try {
            matVm.fromDto(dto);
            if (basedOnThickness) {
                thicknessField.setText(String.format("%.2f", dto.getThickness()));
            }
        } finally {
            suppressEvents = false;
        }
    }

    private MatCharacteristicsDataDto calculateThicknessLeadValues(String materialData) {
        String[] parts = materialData.split(" ");
        String name = parts[0];
        float density = NumbersFormatter.formatWithPrecision(Float.parseFloat(parts[1]), 2).floatValue();
        MatParamDto dto = new MatParamDto();
        if (basedOnThickness) {
            dto.setThickness(matVm.getThickness());
        } else {
            dto.setLeadEquivalent(convertToNumber(panelDataVM.getAdditionalLead()));
        }

        return calculateThicknessOrLeadValues(dto, name, density);
    }

    private MatCharacteristicsDataDto calculateThicknessOrLeadValues(MatParamDto dto, String name, float density) {
        MatCharacteristicsDataDto matDto = new MatCharacteristicsDataDto();
        MaterialInfoDataDto infoDto = new MaterialInfoDataDto(name, density);
        matVm.getInfo().fromDto(infoDto);
        matDto.setInfo(infoDto);
        matDto.setThickness(dto.getThickness());
        matDto.setLeadEquivalent(dto.getLeadEquivalent());
        if (shouldTriggerEvent()) {
            if (dto.isThicknessBased()) {
                matDto.setLeadEquivalent(calculate(matDto));
            } else {
                matDto.setThickness(calculate(matDto));
            }
        }
        return matDto;
    }

    private double calculate(MatCharacteristicsDataDto dto) {
        return connectionService.getMaterialCharacteristics(
                dto.getInfo().getName(), dto.getInfo().getDensity(),
                voltage, dto.getThickness(), dto.getLeadEquivalent());
    }

    private void setupLeadEquivalentLabel() {
        label.textProperty().bind(Bindings.createStringBinding(
                () -> String.format("%.2f", matVm.getLeadEquivalent()),
                matVm.getLeadEquivalentProperty()
        ));
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

    private boolean validData() {
        return voltage != null &&
                voltage >= 50 &&
                !matVm.getInfo().getName().isEmpty() &&
                (basedOnThickness ? matVm.getThickness() > 0 : true);
    }

    private void initVoltage() {
        this.voltage = viewModel.getRadiationTypeViewModel().getVoltage();
    }
}
