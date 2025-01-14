package com.xrc.dsk.data;

import com.xrc.dsk.dto.MedWindowDto;
import com.xrc.dsk.dto.PanelDataDto;
import com.xrc.dsk.dto.WindowDto;
import com.xrc.dsk.handlers.ComboBoxHandler;
import javafx.beans.property.DoubleProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Map;

import static com.xrc.dsk.data.AppParameters.DIRECTION_COEFFICIENT;
import static com.xrc.dsk.data.AppParameters.DISTANCE;
import static com.xrc.dsk.data.AppParameters.DMD;

public class MedicineSourceDataBinder implements Bindable {
    private final Binder binder;
    private MedWindowDto medWindowDto;
    private Integer panelId;
    private PanelDataDto panelDataDto;

    public MedicineSourceDataBinder() {
        binder = new Binder();
    }

    @Override
    public void bind(WindowDto dto, Map<String, Object[]> components) {
        medWindowDto = (MedWindowDto) dto;
        this.panelId = (Integer) components.get(DMD)[1];
        this.panelDataDto = medWindowDto.getPanelDataProperty().get(panelId);
        Label dmdLabel = (Label) components.get(DMD)[0];
        ComboBox<Double> directionCoefficientBox = (ComboBox<Double>) components.get(DIRECTION_COEFFICIENT)[0];
        TextField distanceField = (TextField) components.get(DISTANCE)[0];

        DoubleProperty dmdProperty = panelDataDto.getSourceDataDto().dmdProperty();
        DoubleProperty directionCoefficientProperty = panelDataDto.getSourceDataDto().directionCoefficientProperty();
        DoubleProperty distanceProperty = panelDataDto.getSourceDataDto().distanceProperty();

        binder.bindDoublePropertyToString(dmdLabel.textProperty(), dmdProperty, Double.parseDouble(dmdLabel.getText()),
                (val) -> {
                    panelDataDto.getSourceDataDto().setDmd(val);
                    System.out.println("dmd: " + val + " has been saved");
                });

        binder.bindDoublePropertyToDouble(directionCoefficientBox.valueProperty(), directionCoefficientProperty, directionCoefficientBox.getValue(),
                (val) -> {
                    panelDataDto.getSourceDataDto().setDirectionCoefficient(val);
                    System.out.println("direction coefficient: " + val + " has been saved");
                });

        Double distance = 0d;
        if (!distanceField.getText().isEmpty()) {
            distance = Double.parseDouble(distanceField.getText());
        }
        binder.bindDoublePropertyToString(distanceField.textProperty(), distanceProperty, distance,
                (val) -> {
                    panelDataDto.getSourceDataDto().setDistance(val);
                    System.out.println("distance: " + val + " has been saved");
                });


    }
}
