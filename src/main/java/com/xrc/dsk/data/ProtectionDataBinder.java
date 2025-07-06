package com.xrc.dsk.data;

import com.xrc.dsk.viewModels.medicine.MedicineDataViewModel;
import com.xrc.dsk.viewModels.medicine.PanelDataViewModel;
import com.xrc.dsk.viewModels.medicine.ProtectionDataViewModel;
import javafx.scene.control.Label;

public class ProtectionDataBinder implements Bindable {
    private final Binder binder;
    private Integer panelId;
    private Label attenuationLabel;
    private Label leadLabel;
    private MedicineDataViewModel viewModel;

    public ProtectionDataBinder() {
        this.binder = new Binder();
    }

    public ProtectionDataBinder(Label attenuationFrequency, Label leadEquivalent, int panelId, MedicineDataViewModel viewModel) {
        this.binder = new Binder();
        this.panelId = panelId;
        this.attenuationLabel = attenuationFrequency;
        this.leadLabel = leadEquivalent;
        this.viewModel = viewModel;
    }

    @Override
    public void bind() {
        PanelDataViewModel panelDataViewModel = viewModel.getPanelDataProperty().get(panelId);
        ProtectionDataViewModel vm = panelDataViewModel.getProtectionViewModel();

        binder.bindDoublePropertyToString(leadLabel.idProperty(),vm.getLeadEqvProperty(), vm.getLeadEqv(),
                (val)->{
                    leadLabel.setText(String.valueOf(val));
                }
                );
        binder.bindDoublePropertyToString(attenuationLabel.idProperty(),vm.getWeaknessCoefficientProperty(), vm.getWeaknessCoefficient(),
                (val)->{
                    attenuationLabel.setText(String.valueOf(val));
                }
        );

//        leadLabel.textProperty().bind(Bindings.createStringBinding(
//                () -> vm.toDto() != null ? String.format("%.2f", vm.getLeadEqvProperty().get()) : "0.0",
//                panelDataViewModel.getProtectionViewModelProperty()
//        ));
//
//        attenuationLabel.textProperty().bind(Bindings.createStringBinding(
//                () -> vm.toDto() != null ? String.format("%.2f", vm.getWeaknessCoefficientProperty().get()) : "0.0",
//                panelDataViewModel.getProtectionViewModelProperty()
//        ));

    }
}
