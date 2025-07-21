package com.xrc.dsk.controllers;

import com.xrc.dsk.panels.CalculationPanel;
import com.xrc.dsk.panels.MaterialPanel;
import com.xrc.dsk.services.MaterialPanelDataService;
import com.xrc.dsk.viewModels.medicine.MedicineDataViewModel;
import com.xrc.dsk.windows.MaterialManagerWindow;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;

@Getter
public class MaterialController {

    private MaterialPanelDataService service;
    @Setter
    private MedicineDataViewModel viewModel;

    @Setter
    private MaterialPanel materialPanel;
    @FXML
    private FontAwesomeIconView addButton;

    @FXML
    private FontAwesomeIconView deleteButton;

    @FXML
    private ComboBox<String> materialBox;

    @FXML
    private VBox materialPlate;

    @FXML
    private TextField thicknessField;

    @FXML
    private Label matEqvLabel;

    @FXML
    void addMatLayer(MouseEvent event) {
        CalculationPanel parentPanel = materialPanel.getParentPanel();
        MaterialPanel newMaterialLayer = new MaterialPanel(parentPanel, parentPanel.getDataViewModel());
        newMaterialLayer.addToParentNode();
    }

    @FXML
    void deleteMatLayer(MouseEvent event) {
        materialPanel.getParentPanel().getMaterialBase().getChildren().remove(materialPlate);
        materialPanel.deletePanel();
        System.out.println("deleting material layer");
    }

    @FXML
    void getLeadEquivalent(KeyEvent event) {

    }

    @FXML
    void selectMaterial(MouseEvent event) {
        if(event.getButton().equals(MouseButton.SECONDARY)){
            String name = materialBox.getSelectionModel().getSelectedItem();
            if (name != null) {
                MaterialManagerWindow window = new MaterialManagerWindow();
                window.show();
                window.getController().getMatNameField().setText(name);

            }
        }
    }
}
