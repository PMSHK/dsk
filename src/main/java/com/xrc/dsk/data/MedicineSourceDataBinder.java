package com.xrc.dsk.data;

import com.xrc.dsk.dto.MedWindowDto;
import com.xrc.dsk.dto.PanelDataDto;
import com.xrc.dsk.dto.WindowDto;
import com.xrc.dsk.listeners.PanelProtectionUpdateService;
import javafx.beans.property.DoubleProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MedicineSourceDataBinder implements Bindable{
    private final Binder binder;
    private MedWindowDto medWindowDto;
    private Integer panelId;
    private PanelDataDto panelDataDto;
    private PanelProtectionUpdateService panelProtectionUpdateService;
    private Label dmdLabel;
    private ComboBox<Double> directionCoefficientBox;
    private TextField distanceField;

    public MedicineSourceDataBinder() {
        binder = new Binder();
    }

    public MedicineSourceDataBinder(Label dmdLabel, ComboBox<Double> directionCoefficientBox, TextField distanceField, int panelId) {
        this.dmdLabel = dmdLabel;
        this.directionCoefficientBox = directionCoefficientBox;
        this.distanceField = distanceField;
        this.panelId = panelId;
        binder = new Binder();
    }

    @Override
    public void bind(WindowDto dto) {
        medWindowDto = (MedWindowDto) dto;
        this.panelDataDto = medWindowDto.getPanelDataProperty().get(panelId);
        this.panelProtectionUpdateService = new PanelProtectionUpdateService(panelDataDto, medWindowDto.getRadiationType());

        DoubleProperty dmdProperty = panelDataDto.getSourceDataDto().dmdProperty();
        DoubleProperty directionCoefficientProperty = panelDataDto.getSourceDataDto().directionCoefficientProperty();
        DoubleProperty distanceProperty = panelDataDto.getSourceDataDto().distanceProperty();

        binder.bindDoublePropertyToString(dmdLabel.textProperty(), dmdProperty, Double.parseDouble(dmdLabel.getText()),
                (val) -> {
                    panelDataDto.getSourceDataDto().setDmd(val);
                    System.out.println("dmd: " + val + " has been saved");
                });

        directionCoefficientBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                directionCoefficientProperty.set((Double) newValue);
                System.out.println("direction coefficient: " + newValue + " has been saved");
            }
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
