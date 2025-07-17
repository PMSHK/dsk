package com.xrc.dsk.services.openings;

import com.xrc.dsk.viewModels.medicine.MedicineDataViewModel;
import com.xrc.dsk.viewModels.medicine.OpeningsViewModel;
import com.xrc.dsk.viewModels.medicine.PanelDataViewModel;
import lombok.Getter;

@Getter
public class OpeningsVMManager {
    private final OpeningsViewModel panelVM;
    private final PanelDataViewModel generalVM;
    private final MedicineDataViewModel medicineVM;

    public OpeningsVMManager(
            PanelDataViewModel generalVM,
            OpeningsViewModel panelVM,
            MedicineDataViewModel medicineVM) {
        this.generalVM = generalVM;
        this.panelVM = panelVM;
        this.medicineVM = medicineVM;
    }

    public double getVoltage() {
        return medicineVM.getRadiationTypeViewModel().getVoltage();
    }

}
