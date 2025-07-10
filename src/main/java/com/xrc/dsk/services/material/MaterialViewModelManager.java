package com.xrc.dsk.services.material;

import com.xrc.dsk.viewModels.medicine.MatCharacteristicsDataViewModel;
import com.xrc.dsk.viewModels.medicine.PanelDataViewModel;
import javafx.beans.property.DoubleProperty;
import lombok.Getter;

@Getter
public class MaterialViewModelManager {
    private final MatCharacteristicsDataViewModel matVM;
    private final PanelDataViewModel panelVM;

    public MaterialViewModelManager(PanelDataViewModel panelVM, MatCharacteristicsDataViewModel matVM) {
        this.panelVM = panelVM;
        this.matVM = matVM;
    }

    public void updateThickness(double newThickness) {
        matVM.getThicknessProperty().set(newThickness);
    }

    public DoubleProperty getThicknessProperty() {
        return matVM.getThicknessProperty();
    }

}
