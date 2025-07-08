package com.xrc.dsk.services.material;

import com.xrc.dsk.viewModels.medicine.MatCharacteristicsDataViewModel;
import javafx.beans.property.DoubleProperty;

public class MaterialViewModelManager {
    private final MatCharacteristicsDataViewModel viewModel;
    public MaterialViewModelManager(MatCharacteristicsDataViewModel viewModel) {
        this.viewModel = viewModel;
    }
    public void updateThickness(double newThickness){
        viewModel.getThicknessProperty().set(newThickness);
    }
    public DoubleProperty getThicknessProperty(){
        return viewModel.getThicknessProperty();
    }
}
